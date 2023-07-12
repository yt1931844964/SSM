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
public class SysAdmin  {

    private static final long serialVersionUID = 1L;

    /**
     * 账户id
     */
      @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;

    /**
     * 账号
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    @TableField(select = false)
    private Integer deleted;

}
