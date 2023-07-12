package com.qtds.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qtds.entity.SysAdmin;
import com.qtds.entity.VO.R;
import com.qtds.service.SysAdminService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * <p>
 *  管理员管理 前端控制器
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
@Controller
@RequestMapping("/sysAdmin")
public class SysAdminController {

    @Resource
    private SysAdminService sysAdminService;
    /** 跳到列表页面 */
    @RequestMapping("/list")
    public String list() {
        return "sysAdmin/list";
    }

    @RequestMapping("/pageData")
    @ResponseBody
    public R pageData(SysAdmin sysAdmin,
                      @RequestParam(defaultValue = "1") Integer pageNum,
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<SysAdmin> page = new Page<>(pageNum, pageSize);
        sysAdminService.page(page);
        return R.ok(page.getTotal(), page.getRecords());
    }

    /** 跳到添加页面 */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "sysAdmin/add";
    }

    /** 添加 */
    @RequestMapping("/doAdd")
    @ResponseBody
    public R doAdd(SysAdmin sysAdmin) {
        boolean save = sysAdminService.save(sysAdmin);
        return save ? R.ok("添加成功！") : R.error("添加失败！");
    }

    /** 跳到修改页面 */
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        SysAdmin sysAdmin = sysAdminService.getById(id);
        model.addAttribute("sysAdmin", sysAdmin);
        return "sysAdmin/edit";
    }

    @RequestMapping("/doUpdate")
    @ResponseBody
    public R doUpdate(SysAdmin sysAdmin) {
        boolean b = sysAdminService.updateById(sysAdmin);
        return b ? R.ok("修改成功！") : R.error("修改失败！");
    }

    /** 删除 */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        boolean b = sysAdminService.removeById(id);
        return b ? R.ok("删除成功！") : R.error("删除失败！");
    }

    /** 批量删除 */
    @PostMapping("/delete")
    @ResponseBody
    public R deleteBatch(@RequestParam("ids[]") ArrayList<Integer> ids) {
        boolean b = sysAdminService.removeByIds(ids);
        return b ? R.ok("删除成功！") : R.error("删除失败！");
    }
}

