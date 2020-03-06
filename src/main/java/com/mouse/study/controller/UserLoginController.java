package com.mouse.study.controller;

import com.mouse.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/*
 *   @ Author     :  mouse
 *   @ Date       :  Created in 19:02 2020/3/5
 *   @ Description:
 */
@Controller
public class UserLoginController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String CheckedInfo(@RequestParam String username,
                              @RequestParam String password,
                              RedirectAttributes attributes){
        System.out.println(username+":"+password);
        // String username = user.getUserName();
        Map<String,Object> map = userService.AccountIsLocked(username);
        if ((boolean)map.get("Flag")) {
            //  被锁定
            attributes.addFlashAttribute("message", username+"账户已经被锁定，在"+ map.get("leftTime") +"分钟将被解锁");
            System.out.println(username+"账户已经被锁定，在"+ map.get("leftTime") +"分钟将被解锁");
        }
        //  未被锁定


        //  先判断验证码是否正确


        //  检验登录是否正确
        if (userService.CheckRight(username,password)){
            //  账号密码正确
            return "success";   //  去到正确登录页面
        }else{
            //  账号密码不正确
            String result = userService.checkErrorKey(username);
            attributes.addFlashAttribute("message", result);
            System.out.println(result);
        }
        return "redirect:/login";
    }

}
