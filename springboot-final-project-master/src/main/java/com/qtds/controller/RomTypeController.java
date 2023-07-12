package com.qtds.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qtds.entity.RomType;
import com.qtds.entity.VO.R;
import com.qtds.exception.UniqueException;
import com.qtds.service.RomTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 客房类型表 前端控制器
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
@Controller
@RequestMapping("/romType")
public class RomTypeController {

    @Resource
    private RomTypeService romTypeService;

    /**
     * 跳到列表页面
     */
    @RequestMapping("/list")
    public String list() {
        return "romType/list";
    }

    @RequestMapping("/all")
    @ResponseBody
    public R all() {
        List<RomType> list = romTypeService.list();
        return R.ok(list);
    }

    @RequestMapping("/pageData")
    @ResponseBody
    public R pageData(RomType romType,
                      @RequestParam(defaultValue = "1") Integer pageNum,
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<RomType> page = new Page<>(pageNum, pageSize);
        romTypeService.page(page);
        return R.ok(page.getTotal(), page.getRecords());
    }

    /**
     * 跳到添加页面
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "romType/add";
    }

    /**
     * 添加
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    public R doAdd(RomType romType) {
        boolean save = romTypeService.save(romType);
        return save ? R.ok("添加成功！") : R.error("添加失败！");
    }

    /**
     * 跳到修改页面
     */
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        RomType romType = romTypeService.getById(id);
        model.addAttribute("romType", romType);
        return "romType/edit";
    }

    @RequestMapping("/doUpdate")
    @ResponseBody
    public R doUpdate(RomType romType) {
        try {
            boolean b = romTypeService.updateById(romType);
            return b ? R.ok("修改成功！") : R.error("修改失败！");
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new UniqueException("已有重复房间号！");
        }
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        boolean b = romTypeService.removeById(id);
        return b ? R.ok("删除成功！") : R.error("删除失败！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    @ResponseBody
    public R deleteBatch(@RequestParam("ids[]") ArrayList<Integer> ids) {
        boolean b = romTypeService.removeByIds(ids);
        return b ? R.ok("删除成功！") : R.error("删除失败！");
    }
}

