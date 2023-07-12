package com.qtds.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qtds.entity.Rom;
import com.qtds.entity.RomType;
import com.qtds.entity.StayInfo;
import com.qtds.entity.VO.R;
import com.qtds.entity.VO.StayPrice;
import com.qtds.service.CustomerService;
import com.qtds.service.RomService;
import com.qtds.service.RomTypeService;
import com.qtds.service.StayInfoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * <p>
 * 住宿信息管理 前端控制器
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
@Controller
@RequestMapping("/stayInfo")
public class StayInfoController {

    @Resource
    private StayInfoService stayInfoService;
    @Resource
    private CustomerService customerService;
    @Resource
    private RomService romService;
    @Resource
    private RomTypeService romTypeService;

    /**
     * 跳到列表页面
     */
    @RequestMapping("/list")
    public String list() {
        return "stayInfo/list";
    }

    /**
     * 列表信息
     *
     * @param stayInfo 居住信息搜索条件
     * @param pageNum  页码
     * @param pageSize 页面长度
     * @return 分页列表信息
     */
    @RequestMapping("/pageData")
    @ResponseBody
    public R pageData(StayInfo stayInfo,
                      @RequestParam(defaultValue = "1") Integer pageNum,
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<StayInfo> page = new Page<>(pageNum, pageSize);
        stayInfoService.page(page);
        return R.ok(page.getTotal(), page.getRecords());
    }

    /**
     * 根据时间条件搜索信息
     *
     * @param condition 参数(inTime/outTime)
     * @param start     开始时间
     * @param end       结束时间
     * @param pageNum   页码
     * @param pageSize  页面长度
     * @return 分页列表信息
     */
    @RequestMapping("/selectPage")
    @ResponseBody
    public R selectPage(String condition,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                        @RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<StayInfo> page = new Page<>(pageNum, pageSize);

        QueryWrapper<StayInfo> wrapper = new QueryWrapper<>();
        if ("inTime".equals(condition)) {
            condition = "in_time";
        }
        if ("outTime".equals(condition)) {
            condition = "out_time";
        }
        if (start != null) {
            wrapper.gt(condition, start);
        }
        if (end != null) {
            wrapper.lt(condition, end);
        }
        stayInfoService.page(page, wrapper);

        return R.ok(page.getTotal(), page.getRecords());
    }

    /**
     * 客户入住页面
     */
    @RequestMapping("/customerIn")
    public String customerIn() {
        return "stayInfo/customerIn";
    }

    /**
     * 客户入住(一个客户一个房间)
     */
    @PostMapping("/in")
    @ResponseBody
    public R in(Integer cid, Integer rid) {
        stayInfoService.customerIn(cid, rid);
        return R.ok("入住成功！");
    }

    /**
     * 客户退房页面
     */
    @RequestMapping("/customerOut")
    public String customerOut() {
        return "stayInfo/customerOut";
    }

    /**
     * 计算并返回退房价格
     */
    @RequestMapping("/outPrice/{sid}")
    @ResponseBody
    public R outPrice(@PathVariable("sid") Integer sid) {
        // todo: 需要sql优化连表查询
        StayInfo stayInfo = stayInfoService.getById(sid);
        Rom rom = romService.getById(stayInfo.getRid());
        RomType romType = romTypeService.getById(rom.getTid());
        Double price = romType.getPrice();

        Duration duration = Duration.between(stayInfo.getInTime(), LocalDateTime.now());
        // 已居住的小时数
        Integer hours = Math.toIntExact(duration.toHours());
        // 价格
        double outPrice = hours * price / 24;
        String format = String.format("%.2f", outPrice);
        outPrice = Double.parseDouble(format);

        return R.ok(new StayPrice(hours, price, outPrice));
    }

    /**
     * 退房
     */
    @PostMapping("/out")
    @ResponseBody
    public R out(Integer sid, Integer cid, Integer rid) {
        boolean b = stayInfoService.customerOut(sid, cid, rid);
        return b ? R.ok("退房成功！") : R.error("退房失败！");
    }

    /**
     * 跳到添加页面
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "stayInfo/add";
    }

    /**
     * 添加
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    public R doAdd(StayInfo stayInfo) {
        boolean save = stayInfoService.save(stayInfo);
        return save ? R.ok("添加成功！") : R.error("添加失败！");
    }

    /**
     * 跳到修改页面
     */
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        StayInfo stayInfo = stayInfoService.getById(id);
        model.addAttribute("stayInfo", stayInfo);
        return "stayInfo/edit";
    }

    @RequestMapping("/doUpdate")
    @ResponseBody
    public R doUpdate(StayInfo stayInfo) {
        boolean b = stayInfoService.updateById(stayInfo);
        return b ? R.ok("修改成功！") : R.error("修改失败！");
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        boolean b = stayInfoService.removeById(id);
        return b ? R.ok("删除成功！") : R.error("删除失败！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    @ResponseBody
    public R deleteBatch(@RequestParam("ids[]") ArrayList<Integer> ids) {
        boolean b = stayInfoService.removeByIds(ids);
        return b ? R.ok("删除成功！") : R.error("删除失败！");
    }

}

