package com.example.common.login.config;


import com.example.common.login.entity.LoginUser;
import com.example.common.login.entity.Role;
import com.example.common.login.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class Provider implements AuthenticationProvider {
    @Autowired
    private CustomUserDetailsService userService;
    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("-------------验证开始------------");
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        System.out.println("前端传过来的明文密码:" + password);
        UserDetails user = userService.loadUserByUsername(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        //加密过程在这里体现
//        System.out.println(encodedPassword);
        System.out.println("结果CustomUserDetailsService后，已经查询出来的数据库存储密码:" + passwordEncoder.encode(user.getPassword()));
        if (!password.equals(user.getPassword())){
            System.out.println("Wrong password");
            throw new DisabledException("Wrong password.");

        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = ((LoginUser)user).getUser().getRoleList();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        System.out.println("-------------验证结束------------");
        return new UsernamePasswordAuthenticationToken(user, password,authorities);
//        return new UsernamePasswordAuthenticationToken(username);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}

