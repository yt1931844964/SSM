package com.qtds.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.qtds.entity.Rom;

/**
 * <p>
 * 客房信息表 服务类
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
public interface RomService extends IService<Rom> {

    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @return
     */
    Page<Rom> findPage(Rom condition, Page<Rom> page);


}
