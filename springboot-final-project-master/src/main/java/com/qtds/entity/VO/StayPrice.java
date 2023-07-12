package com.qtds.entity.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 居住信息VO
 * @author 姚廷
 * @since 2023-07-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StayPrice {
    /**
     * 居住的小时数
     */
    private Integer hours;
    /**
     * 客房单价
     */
    private Double price;
    /**
     * 计算的价格
     */
    private Double outPrice;

}
