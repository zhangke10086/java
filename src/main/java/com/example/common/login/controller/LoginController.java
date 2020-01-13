package com.example.common.login.controller;

import com.example.common.login.entity.User;
import com.example.common.login.service.UserService;
import com.example.common.resformat.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;

@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    public UserService userService;

    @PostMapping(value = "/login")
    @ApiOperation("用户登录")
    @CrossOrigin
    public void userlogin(@RequestParam("username") String username, @RequestParam("password") String password){
    }

    @PostMapping(value = "/logon")
    @ApiOperation("用户注册")
    @CrossOrigin
    public CommonResult userlogon(@RequestParam("username") String username, @RequestParam("password") String password
    , @RequestParam("name")String name){
        CommonResult result = new CommonResult();
        try {
            if(null != userService.findBycode(username)){
                result.setState(501);
                log.warn("用户名已被注册！");
                return result;
            } else {
                User user = new User();
                user.setCode(username);
                user.setName(name);
                user.setPassword(password);
                user.setId(UUID.randomUUID().toString());
                userService.register(user);
                log.info("注册成功！用户：{}",user);
                return result;
            }
        } catch (Exception e){
            e.printStackTrace();
            result.setState(500);
            result.setMsg("保存失败");
            return result;
        }

    }
    @PostMapping(value = "/updateUserInfo")
    @ApiOperation("更新用户信息")
    @CrossOrigin
    public CommonResult updateUserInfo(@RequestBody User user){
        CommonResult result =new CommonResult();
        try {
            userService.updateUserInfo(user);
            result.setData(user);
            log.info("更新用户信息成功！{}",user);
            return result;
        }catch (Exception e){
            result.setState(500);
            return result;
        }
    }
}
