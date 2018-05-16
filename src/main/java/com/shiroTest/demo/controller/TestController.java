package com.shiroTest.demo.controller;

import com.shiroTest.demo.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
public class TestController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/userLogin")
    public String loginUser(@RequestParam(value = "username",required = false)String username,
                            @RequestParam(value = "password",required = false)String password,
                            HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
    try {
        subject.login(token);
        User user = (User) subject.getPrincipal();
        System.out.println();
        session.setAttribute("user", user);
        return "index";
    }catch (Exception e){
        return "login";
    }
    }
}
