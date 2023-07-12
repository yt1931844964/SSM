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
 * 客房信息表
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Rom  {

    private static final long serialVersionUID = 1L;

    /**
     * 客房编号
     */
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    /**
     * 房间号
     */
    private Integer rnum;

    /**
     * 房间名
     */
    private String rname;

    /**
     * 房间类型编号
     */
    private Integer tid;

    /**
     * 客房状态（0：空房间 1：有人居住 2：维护中 4：已删除）
     */
    private Integer state;

    /**
     * 逻辑删除字段
     */
    @TableLogic(delval = "rid")
    @TableField(select = false)
    private Integer deleted;

    /**
     * 房间类型对象
     */
    @TableField(exist = false)
    private RomType romType;
}
