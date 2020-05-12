package com.fengtoos.ppgraduate.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fengtoos.ppgraduate.auth.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengtoos
 * @since 2019-06-14
 */
public interface IUser2Service extends IService<User> {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    Serializable findWxUser(User user);

    /**
     * 禁用和启用
     * @param id
     * @param status
     * @return
     */
    boolean action(Serializable id, Integer status);

    /**
     * 根据角色ID获取已绑定的用户
     * @param roleId
     * @return
     */
    List<User> findByRoleId(String roleId);
}
