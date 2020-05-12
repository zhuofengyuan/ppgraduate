package com.fengtoos.ppgraduate.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fengtoos.ppgraduate.auth.entity.Role;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengtoos
 * @since 2020-04-07
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    Role findById(Serializable id);
}
