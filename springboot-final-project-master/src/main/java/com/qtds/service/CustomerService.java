package com.qtds.service;

import com.github.pagehelper.PageInfo;
import com.qtds.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 客户信息表 服务类
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @return
     */
    PageInfo<Customer> findPage(Customer condition, int pageNum, int pageSize);


}
