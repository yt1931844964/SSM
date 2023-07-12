package com.qtds.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import com.qtds.entity.Customer;
import com.qtds.entity.VO.R;
import com.qtds.entity.enums.UniqueKey;
import com.qtds.exception.UniqueException;
import com.qtds.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * <p>
 * 客户信息表 前端控制器
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    /**
     * 跳到列表页面
     */
    @RequestMapping("/list")
    public String list() {
        return "customer/list";
    }

    /**
     * 根据身份证号搜索数据
     */
    @RequestMapping("/select/{idNum}")
    @ResponseBody
    public R selectByIdNum(@PathVariable("idNum") String idNum) {

        Customer customer = customerService.getOne(Wrappers.<Customer>query()
                .eq("identity_num", idNum));
        if (customer != null && customer.getCid() != null) {
            return R.ok(customer);
        }
        return R.error("无此用户！");
    }

    @RequestMapping("/pageData")
    @ResponseBody
    public R pageData(Customer customer,
                      @RequestParam(defaultValue = "1") Integer pageNum,
                      @RequestParam(defaultValue = "10") Integer pageSize) {

        PageInfo<Customer> page = customerService.findPage(customer, pageNum, pageSize);

        return R.ok(page.getTotal(), page.getList());
    }

    /**
     * 跳到添加页面
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "customer/add";
    }

    /**
     * 添加
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    public R doAdd(Customer customer) {
        try {
            boolean save = customerService.save(customer);
            return save ? R.ok("添加成功！") : R.error("添加失败！");
        } catch (RuntimeException e) {

            if (e.getMessage().endsWith("for key 'customer.table_name_identityNum_uindex'")) {
                throw new UniqueException("已有重复身份证号！");
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
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @RequestMapping("/doUpdate")
    @ResponseBody
    public R doUpdate(Customer customer) {
        try {
            boolean b = customerService.updateById(customer);
            return b ? R.ok("修改成功！") : R.error("修改失败！");
        } catch (RuntimeException e) {
            if (e.getMessage().endsWith(UniqueKey.CUSTOMER_IDENTITY)) {
                throw new UniqueException("已有重复身份证号！");
            } else {
                e.printStackTrace();
                throw e;
            }
        }
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        boolean b = customerService.removeById(id);
        return b ? R.ok("删除成功！") : R.error("删除失败！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    @ResponseBody
    public R deleteBatch(@RequestParam("ids[]") ArrayList<Integer> ids) {
        boolean b = customerService.removeByIds(ids);
        return b ? R.ok("删除成功！") : R.error("删除失败！");
    }
}

