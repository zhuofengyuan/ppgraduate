package com.fengtoos.ppgraduate.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengtoos.ppgraduate.auth.entity.User;
import com.fengtoos.ppgraduate.auth.exception.FengtoosException;
import com.fengtoos.ppgraduate.auth.mapper.User2Mapper;
import com.fengtoos.ppgraduate.auth.service.IUser2Service;
import com.fengtoos.ppgraduate.auth.util.FengtoosUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengtoos
 * @since 2019-06-14
 */
@Service
public class User2ServiceImpl extends ServiceImpl<User2Mapper, User> implements IUser2Service {

    @Override
    public User findByUsername(String username) {
        if(StringUtils.isEmpty(username)){
            return null;
        }
        return this.getBaseMapper().selectOne(new QueryWrapper<User>().eq("username", username));
    }

    /**
     * 根据openid获取用户id内容（若无用户则创建）
     * @param user
     * @return
     */
    @Override
    public Serializable findWxUser(User user) {
        String openid = user.getOpenid();
        String uid = null;
        if(StringUtils.isEmpty(openid)){
            throw new FengtoosException(500, "openid is empty");
        }

        User entity = this.getBaseMapper().selectOne(new QueryWrapper<User>().eq("openid", openid));
        if(entity == null){
            this.saveOrUpdate(user);
            uid = user.getId();
        } else {
            uid = entity.getId();
        }

        return uid;
    }

    @Override
    public boolean action(Serializable id, Integer status) {
        User entity = this.getById(id);
        FengtoosUtil.null2Entity(entity);

        int orgStatus = entity.getStatus();
        if(1 == status){
            if(2 != orgStatus){
                throw new FengtoosException("只有禁用状态的用户才允许启用");
            }
        } else if(2 == status){
            if(1 != orgStatus){
                throw new FengtoosException("只有启用状态的用户才允许禁用");
            }
        }

        User u = new User();
        u.setStatus(status);
        return this.update(u, new QueryWrapper<User>().eq("id", id));
    }

    @Override
    public List<User> findByRoleId(String roleId) {
        if(StringUtils.isEmpty(roleId)){
            throw new FengtoosException("参数错误！！");
        }
        return this.getBaseMapper().findByRoleId(roleId);
    }

    @Override
    public boolean removeById(Serializable id) {
        User entity = this.getById(id);
        FengtoosUtil.null2Entity(entity);

        if(entity.getStatus() == 1){
            throw new FengtoosException(500, "只有禁用状态的用户才允许删除");
        }
        return super.removeById(id);
    }

}
