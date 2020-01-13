package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.common.formatdate.DateString;
import com.example.common.login.entity.LoginUser;
import com.example.common.login.service.CustomUserDetailsService;
import com.example.common.resformat.JwtHelper;
import com.example.mis.cro.entity.PlanType;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
@SpringBootTest
class 大四课设ApplicationTests {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Test
    void contextLoads() throws SQLException {
//     String url = "jdbc:mysql://localhost:3306/zk" +
//             "?useUnicode=true&characterEncoding=utf8" +
//             "&serverTimezone=Asia/Shanghai";
//     String username = "root";
//     String password ="root" ;
//     Connection connection = DriverManager.getConnection(url,username,password);
//     String delete_sql = "DELETE FROM misplace WHERE id=?";
        List<Object> list =new ArrayList<Object>();
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
//        list.add("1");
        System.out.println(list);
        if (isEnd(list)){
            System.out.println("true");
        } else {
            System.out.println("false");
        }


//        Map map = new HashMap();
//        map.put("K",0);
//        System.out.println(map.get("K").toString());
//        int a =Integer.parseInt(map.get("K").toString());
//        System.out.println("666"+a);

    }
    public boolean isEnd(List<Object> list){
        for (Object s : list) {
            if (s != null) {
                return false;
            }
        }
      return true;
    }
    @Test
    @ApiOperation("测试")
    protected void doPost()
            {
        for (int i =0; i<5;i++) {
            DateString date = new DateString();
            String code = "WO" + date.CurrentDateStrinbg();
            StringBuilder newWord = new StringBuilder(code);
            newWord.replace(newWord.length() - 4, newWord.length(), String.valueOf((int) ((Math.random() * 9 + 1) * 1000)));
            System.out.println(code);
            System.out.println(newWord.toString());
        }

    }

}
