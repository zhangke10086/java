package com.example.common.login.config;



import com.example.common.login.entity.LoginUser;
import com.example.common.login.entity.Role;
import com.example.common.login.entity.Token;
import com.example.common.login.entity.User;
import com.example.common.login.filter.AuthenticationFilter;
import com.example.common.login.service.CustomUserDetailsService;
import com.example.common.login.service.TokenService;
import com.example.common.resformat.JsonResult;
import com.example.common.resformat.JwtHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private TokenService tokenService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    ObjectMapper objectMapper;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//配置权限
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/login",
                        "/logon",
                        "/swagger*/**",
                        "/doc*/**",
                        "/mis/Import",
                        "/mis/Download",
                        "/**/api-docs/**"
                ).permitAll()
        .anyRequest().authenticated()
        .and()
                .formLogin().loginProcessingUrl("/login").successHandler(successHandler()).failureHandler(failureHandler()).permitAll()
        .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler()).permitAll()
        .and()
               .csrf().disable().headers().frameOptions().disable();
        http.headers().cacheControl();
        http.cors().configurationSource(CorsConfigurationSource());
        http
                .addFilter(new AuthenticationFilter(authenticationManager()));



    }
    private CorsConfigurationSource CorsConfigurationSource() {
        UrlBasedCorsConfigurationSource source =   new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");	//同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedMethod("*");	//允许的请求方法，PSOT、GET等
        source.registerCorsConfiguration("/**",corsConfiguration); //配置允许跨域访问的url
        return source;
    }

    /**
     *自定义登录成功处理器，成功返回一个带有成功信息的Json数据包装类
     */
    private AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie");
            LoginUser loginUser= (LoginUser) authentication.getPrincipal();
            User user =loginUser.getUser();
            tokenService.deleteAll(user.getId());
            List<Role> roleList = user.getRoleList();
            String token = JwtHelper.createJWT(loginUser);
            Token token_ =new Token();
            token_.setToken(token);
            token_.setUser(user);
            tokenService.update(token_);
            JsonResult ok = JsonResult.ok(token,user,roleList);
            System.out.println("------------------------------登陆成功！");
            out.write(objectMapper.writeValueAsString(ok));
            out.flush();
            out.close();
        };
    }
    /**
     *自定义登录失败处理器，成功返回一个带有失败信息的Json数据包装类
     */
    private AuthenticationFailureHandler failureHandler() {
        return (request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            JsonResult error = JsonResult.error("账号或密码错误");
            System.out.println("------------------------------登陆失败！");
            out.write(objectMapper.writeValueAsString(error));
            out.flush();
            out.close();
        };
    }
    /**
     *自定义登出成功处理器，清除登录信息且成功返回一个带有登出信息的Json数据包装类
     */
    private LogoutSuccessHandler logoutSuccessHandler(){
        return (request, response, authentication) -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);//清除登录认证信息
            }
            response.setContentType("application/json;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie");
            PrintWriter out = response.getWriter();
            JsonResult ok = JsonResult.ok("注销成功");
            System.out.println("-----------------------------注销成功！!！");
            out.write(objectMapper.writeValueAsString(ok));
            out.flush();
            out.close();

        };
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();//向spring注册一个密码加密器
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.customUserDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(authenticationProvider());

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new Provider();
    }

}
