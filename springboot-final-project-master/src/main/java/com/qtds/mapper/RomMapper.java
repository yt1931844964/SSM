package com.qtds.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qtds.entity.Rom;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 客房信息表 Mapper 接口
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
public interface RomMapper extends BaseMapper<Rom> {

    Rom findByIdWithType(int id);

    Page<Rom> findPage(@Param("rom") Rom rom, IPage<Rom> iPage);

    int updateStateByRid(@Param("state") Integer state, @Param("rid") Integer rid);
}
