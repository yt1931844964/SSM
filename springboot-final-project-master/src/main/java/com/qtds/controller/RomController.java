package com.qtds.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qtds.entity.Rom;
import com.qtds.entity.RomType;
import com.qtds.entity.VO.R;
import com.qtds.entity.enums.UniqueKey;
import com.qtds.exception.UniqueException;
import com.qtds.service.RomService;
import com.qtds.service.RomTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 客房信息表 前端控制器
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
@Controller
@RequestMapping("/rom")
public class RomController {
    @Resource
    private RomService romService;

    @Resource
    private RomTypeService romTypeService;

    /**
     * 跳到列表页面
     */
    @RequestMapping("/list")
    public String list() {
        return "rom/list";
    }

    @RequestMapping("/pageData")
    @ResponseBody
    public R pageData(Rom rom,
                      @RequestParam(defaultValue = "1") Integer pageNum,
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Rom> page = new Page<>(pageNum, pageSize);
        romService.findPage(rom, page);
        return R.ok(page.getTotal(), page.getRecords());
    }

    /**
     * 跳到添加页面
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        List<RomType> romTypes = romTypeService.list();
        model.addAttribute("romTypes", romTypes);
        return "rom/add";
    }

    /**
     * 添加
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    public R doAdd(Rom rom) {
        try {
            boolean save = romService.save(rom);
            return save ? R.ok("添加成功！") : R.error("添加失败！");

        } catch (RuntimeException e) {
            if (e.getMessage().endsWith("for key 'rom.rom_rnum_uindex'")) {
                throw new UniqueException("已有重复房间号！");
            }
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 跳到修改页面
     */
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        Rom rom = romService.getById(id);
        model.addAttribute("rom", rom);
        List<RomType> romTypes = romTypeService.list();
        model.addAttribute("romTypes", romTypes);
        return "rom/edit";
    }

    @RequestMapping("/doUpdate")
    @ResponseBody
    public R doUpdate(Rom rom) {
        try {
            boolean b = romService.updateById(rom);
            return b ? R.ok("修改成功！") : R.error("修改失败！");
        } catch (RuntimeException e) {
            if (e.getMessage().endsWith(UniqueKey.ROM_ROMNUM)) {
                throw new UniqueException("已有重复房间号！");
            }
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        boolean b = romService.removeById(id);
        return b ? R.ok("删除成功！") : R.error("删除失败！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    @ResponseBody
    public R deleteBatch(@RequestParam("ids[]") ArrayList<Integer> ids) {
        boolean b = romService.removeByIds(ids);
        return b ? R.ok("删除成功！") : R.error("删除失败！");
    }

}

