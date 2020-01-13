package com.example.common.auditorinfo;


import com.example.common.login.entity.LoginUser;
import com.example.common.login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<String> {

    //使用security
    @Override
    public Optional<String> getCurrentAuditor() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if(null != user)
        {
            return Optional.of(user.getName());
        }
        else
        {
            return Optional.of("9999");
        }
    }
    public static User getCurrentGspUser() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if(null != user)
        {
            return user;
        }
        else
        {
            User user_9999 = new User();
            user_9999.setName("9999");
            return user_9999;
        }
    }
}
