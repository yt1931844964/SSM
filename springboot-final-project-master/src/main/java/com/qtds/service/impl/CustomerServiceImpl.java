package com.qtds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.qtds.entity.Customer;
import com.qtds.mapper.CustomerMapper;
import com.qtds.service.CustomerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户信息表 服务实现类
 * </p>
 *
 * @author 姚廷
 * @since 2023-07-01
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Customer> findPage(Customer condition, int pageNum, int pageSize) {

        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            QueryWrapper<Customer> wrapper = new QueryWrapper<>();
            if (condition != null) {
                String cname = condition.getCname();
                String telPhone = condition.getTelPhone();
                String identityNum = condition.getIdentityNum();

                if (StringUtil.isNotEmpty(cname))
                    wrapper.like("cname", cname);

                if (StringUtil.isNotEmpty(telPhone))
                    wrapper.eq("tel_phone", telPhone);

                if (StringUtil.isNotEmpty(identityNum))
                    wrapper.eq("identity_num", identityNum);
            }
            wrapper.eq("deleted", 0);
            baseMapper.selectList(wrapper);
        });
    }
}
