package com.qtds.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RomType {

    private static final long serialVersionUID = 1L;

    /**
     * 客房类型编号
     */
    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;

    /**
     * 客房类型名称
     */
    private String tname;

    /**
     * 类型价格(单位：每小时)
     */
    private Double price;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    @TableField(select = false)
    private Integer deleted;
}

