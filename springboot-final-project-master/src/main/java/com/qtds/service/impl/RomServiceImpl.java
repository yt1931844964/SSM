package com.qtds.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtds.entity.Rom;
import com.qtds.mapper.RomMapper;
import com.qtds.service.RomService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客房信息表 服务实现类
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
@Service
public class RomServiceImpl extends ServiceImpl<RomMapper, Rom> implements RomService {

    /**
     * 分页查询
     *
     * @param condition
     * @param page
     * @return
     */
    @Override
    public Page<Rom> findPage(Rom condition, Page<Rom> page) {
        page.addOrder(OrderItem.asc("rid"));

        return baseMapper.findPage(condition, page);
    }
}
