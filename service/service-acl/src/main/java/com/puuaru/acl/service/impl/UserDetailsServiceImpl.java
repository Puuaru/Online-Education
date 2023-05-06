package com.puuaru.acl.service.impl;

import com.puuaru.acl.service.PermissionService;
import com.puuaru.acl.service.UserService;
import com.puuaru.entity.SecurityUser;
import com.puuaru.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final PermissionService permissionService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, PermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<String> permissions = permissionService.getPermissionsByUserId(user.getId());
        return new SecurityUser(user, permissions);
    }
}
