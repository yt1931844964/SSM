package com.qtds.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class StayInfo  {

    private static final long serialVersionUID = 1L;

    /**
     * 住宿订单编号
     */
    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;

    /**
     * 顾客编号
     */
    private Integer cid;

    /**
     * 客房编号
     */
    private Integer rid;

    /**
     * 入住时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime inTime;

    /**
     * 退房时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime outTime;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    @TableField(select = false)
    private Integer deleted;

    @TableField(exist = false)
    private Customer customer;

    @TableField(exist = false)
    private Rom rom;

}
