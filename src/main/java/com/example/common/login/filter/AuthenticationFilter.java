package com.example.common.login.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.common.login.entity.LoginUser;
import com.example.common.login.entity.Role;
import com.example.common.login.entity.Token;
import com.example.common.login.entity.User;
import com.example.common.login.service.TokenService;
import com.example.common.resformat.JwtHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AuthenticationFilter extends BasicAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private TokenService tokenService;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        ServletContext sc = request.getSession().getServletContext();
        // 获取 spring 容器
        AbstractApplicationContext cxt = (AbstractApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
        if (cxt != null && cxt.getBean("tokenService") != null && tokenService == null) {
            // 取出 userInfoService
            tokenService = (TokenService) cxt.getBean("tokenService");
        }
        if (!"OPTIONS".equals(request.getMethod().toUpperCase()) && !request.getRequestURI().contains("login") && !request.getRequestURI().contains("logon")) {
            log.warn("token验证开始===========================>");
            String token = request.getHeader("Authorization");
            if (token == null) {
                log.warn("请求头未携带Token信息");
            } else {
                //验证Token
                LoginUser loginUser;
                try {
                    if (!JwtHelper.isExpiration(token)) {
                        loginUser = JwtHelper.getUserByToken(token);
                        User user = loginUser.getUser();
                        if(!tokenService.findToken(user.getId()).getToken().equals(token)){
                            log.info("当前账号已在其他地点登陆，您已被强迫下线！");
                            JSONObject j = new JSONObject();
                            j.put("state", "50002");
                            response.setHeader("Access-Control-Allow-Origin", "*");
                            response.setHeader("Access-Control-Allow-Credentials", "true");
                            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie");
                            response.getWriter().write(j.toString());
                            return;
                        }
                        log.info("用户信息：{}", loginUser.toString());
                        List<GrantedAuthority> authorities = new ArrayList<>();
                        List<Role> roles = user.roleList;
                        for (Role role : roles) {
                            authorities.add(new SimpleGrantedAuthority(role.getName()));
                        }
                        //token验证成功
                        SecurityContextHolder.getContext().setAuthentication(
                                new UsernamePasswordAuthenticationToken(loginUser,
                                        null, authorities));
                    }
                } catch (Exception e) {
                    log.info("当前token已失效！");
                    JSONObject j = new JSONObject();
                    j.put("state", "50001");
                    response.setHeader("Access-Control-Allow-Origin", "*");
                    response.setHeader("Access-Control-Allow-Credentials", "true");
                    response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie");
                    response.getWriter().write(j.toString());

                }
            }
        }
        log.warn("token验证结束=============================>");
        chain.doFilter(request, response);

    }
}
