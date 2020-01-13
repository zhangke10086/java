package com.example.common.login.service;

import com.example.common.login.entity.Token;
import com.example.common.login.entity.User;
import com.example.common.login.repository.TokenRepository;
import com.example.common.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public void add(Token token){
        tokenRepository.save(token);
    }
    public Token findToken(String userid){
        return tokenRepository.findToken(userid);
    }
    public void deleteAll(String userid){
        tokenRepository.deleteByUserid(userid);
    }
    public void update(Token token){
        tokenRepository.save(token);
    }
}
