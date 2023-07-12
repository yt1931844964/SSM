package com.qtds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.qtds.entity.StayInfo;
import com.qtds.mapper.CustomerMapper;
import com.qtds.mapper.RomMapper;
import com.qtds.mapper.StayInfoMapper;
import com.qtds.service.StayInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
@Service
public class StayInfoServiceImpl extends ServiceImpl<StayInfoMapper, StayInfo> implements StayInfoService {

    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private RomMapper romMapper;

    /**
     * 用户入住
     *
     * @param cid 客户表主键
     * @param rid 房间表主键
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean customerIn(Integer cid, Integer rid) {
        int i = customerMapper.updateStateByCid(1, cid);
        int i1 = romMapper.updateStateByRid(1, rid);

        StayInfo stayInfo = new StayInfo();
        stayInfo.setCid(cid);
        stayInfo.setRid(rid);
        stayInfo.setInTime(LocalDateTime.now());
        int i2 = baseMapper.insert(stayInfo);

        return i + i1 + i2 >= 1;
    }

    /**
     * 用户退房(房间状态改为维护中)
     *
     * @param sid 状态表主键
     * @param cid 客户表主键
     * @param rid 房间表主键
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean customerOut(Integer sid, Integer cid, Integer rid) {
        StayInfo stayInfo = new StayInfo();
        stayInfo.setSid(sid);
        stayInfo.setOutTime(LocalDateTime.now());

        int i = customerMapper.updateStateByCid(0, cid);
        int i1 = romMapper.updateStateByRid(2, rid);
        int i2 = baseMapper.updateById(stayInfo);
        return i + i1 + i2 >= 1;
    }
}
