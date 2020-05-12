package com.fengtoos.ppgraduate.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fengtoos.ppgraduate.auth.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fengtoos
 * @since 2019-06-14
 */
public interface User2Mapper extends BaseMapper<User> {

    /**
     * 根据角色ID获取用户
     * @param roleId
     * @return
     */
    List<User> findByRoleId(String roleId);
}
