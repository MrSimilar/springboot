package com.xinyet.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinyet.mybatis.entity.OsUser;
import com.xinyet.mybatis.mapper.OsUserMapper;
import com.xinyet.mybatis.service.IOsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author via
 * @since 2022-07-31
 */
@Service
public class OsUserServiceImpl extends ServiceImpl<OsUserMapper, OsUser> implements IOsUserService {

    @Autowired
    private OsUserMapper userMapper;

    /**
     * 获取所有的user
     *
     * @return
     */
    @Override
    public List<OsUser> getUserList() {
        return userMapper.selectList(new QueryWrapper<>());
    }

    /**
     * 添加单个用户
     *
     * @param user
     * @return
     */
    @Override
    public Integer addUser(OsUser user) {
        return userMapper.insert(user);
    }
}
