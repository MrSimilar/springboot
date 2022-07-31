package com.xinyet.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinyet.mybatis.entity.OsUser;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author via
 * @since 2022-07-31
 */
public interface IOsUserService extends IService<OsUser> {

    /**
     * 获取所有的user
     *
     * @return
     */
    List<OsUser> getUserList();

    /**
     * 添加单个用户
     *
     * @param user
     * @return
     */
    Integer addUser(OsUser user);
}
