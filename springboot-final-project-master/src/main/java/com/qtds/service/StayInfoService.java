package com.qtds.service;

import com.qtds.entity.StayInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
public interface StayInfoService extends IService<StayInfo> {

    /**
     * 用户入住
     *
     * @param cid 客户表主键
     * @param rid 房间表主键
     * @return
     */
    boolean customerIn(Integer cid, Integer rid);

    /**
     * 用户退房(房间状态改为维护中)
     *
     * @param sid 状态表主键
     * @param cid 客户表主键
     * @param rid 房间表主键
     * @return
     */
    boolean customerOut(Integer sid, Integer cid, Integer rid);


}
