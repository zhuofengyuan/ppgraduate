package com.fengtoos.ppgraduate.auth.security;

import com.fengtoos.ppgraduate.auth.entity.Authorization;
import com.fengtoos.ppgraduate.auth.entity.User;
import com.fengtoos.ppgraduate.auth.service.IAuthorizationService;
import com.fengtoos.ppgraduate.auth.service.IUser2Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Order(1)
@Component
public class FengtoosUserDetailsService implements UserDetailsService {

    @Autowired
    IUser2Service userService;
    @Autowired
    IAuthorizationService authorizationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User entity = this.userService.findByUsername(username);
        if(entity == null){
            throw new UsernameNotFoundException("用户名认证失败");
        }
        return this.createSecurityUser(entity);
    }

    private FengtoosSecurityUser createSecurityUser(User entity) {
        String id = entity.getId();
        List<Authorization> auths = this.authorizationService.selectByUserId(id);

        List<GrantedAuthority> authorizations = auths.stream().filter(a -> StringUtils.isNotEmpty(a.getCode()))
                .map(a -> new FengtoosGrantedAuthority(a.getCode(), a.getPath(), a.getId())).collect(Collectors.toList());
        return new FengtoosSecurityUser(
                authorizations,
                entity.getId(),
                entity.getScreenName(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getLogo(),
                ""
        );
    }
}
