package com.qtds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qtds.entity.Customer;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 客户信息表 Mapper 接口
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
public interface CustomerMapper extends BaseMapper<Customer> {
    int updateStateByCid(@Param("state") Integer state, @Param("cid") Integer cid);
}
