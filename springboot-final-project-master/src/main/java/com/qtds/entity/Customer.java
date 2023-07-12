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
 * 客户信息表
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Customer  {

    private static final long serialVersionUID = 1L;

    /**
     * 顾客编号
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    /**
     * 顾客姓名
     */
    private String cname;

    /**
     * 顾客身份证号
     */
    private String identityNum;

    /**
     * 顾客联系电话
     */
    private String telPhone;

    /**
     * 顾客状态（ 0：未入住 1： 在住4：已删除）
     */
    private Integer state;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    @TableField(select = false)
    private Integer deleted;

}
