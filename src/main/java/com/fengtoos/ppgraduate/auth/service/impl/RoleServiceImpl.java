package com.fengtoos.ppgraduate.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengtoos.ppgraduate.auth.entity.Authorization;
import com.fengtoos.ppgraduate.auth.entity.Role;
import com.fengtoos.ppgraduate.auth.entity.RoleAuthorization;
import com.fengtoos.ppgraduate.auth.mapper.RoleMapper;
import com.fengtoos.ppgraduate.auth.service.IRoleAuthorizationService;
import com.fengtoos.ppgraduate.auth.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengtoos
 * @since 2020-04-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    IRoleAuthorizationService roleAuthorizationService;

    @Override
    public boolean saveOrUpdate(Role entity) {

        //先保存角色
        super.saveOrUpdate(entity);
        //删除所有权限项再重新赋值
        this.roleAuthorizationService.remove(new QueryWrapper<RoleAuthorization>().eq("role_id", entity.getId()));
        //重新新增
        List<Authorization> auths = entity.getAuthorizations();
        List<RoleAuthorization> ras = new ArrayList<>();
        auths.forEach(auth -> {
            ras.add(new RoleAuthorization(entity.getId(), auth.getId()));
        });
        return this.roleAuthorizationService.saveBatch(ras);
    }

    @Override
    public Role findById(Serializable id) {
        return this.getBaseMapper().findById(id);
    }
}
