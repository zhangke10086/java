package com.example.common.login.service;

import com.example.common.login.entity.LoginUser;
import com.example.common.login.entity.User;
import com.example.common.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;

    public User findBycode(String code){
        return userRepository.findByCode(code);
    }
    public void  register(User user){
        userRepository.save(user);
    }
    public void updateUserInfo(User user){
        userRepository.saveAndFlush(user);
    }
}
