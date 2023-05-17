package com.yst.controller;

import com.yst.pojo.User;
import com.yst.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private CourseService courseService;
    @PostMapping
    public Result login(String userName, String password){
        System.out.println("userName:"+userName+" password:"+password);
        User user = courseService.validateUser(userName,password);
        if(user!=null){
            return new Result(Code.LOGIN_SUCCESS,"登录成功");
        }else{
            return new Result(Code.LOGIN_FAIL,"登录失败");
        }
    }
}
