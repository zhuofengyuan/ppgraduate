package com.fengtoos.ppgraduate.auth.instance;

import com.fengtoos.ppgraduate.auth.security.FengtoosSecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminUserInstance implements UserInstance {

    @Override
    public SecurityUser getCurrentUser() {

        SecurityUser user = new FengtoosSecurityUser();
        BeanUtils.copyProperties(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                user
        );
        return user;
    }
}
