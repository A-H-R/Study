package com.mouse.study.service;
/*
 *created by mouse on 2020/3/5
 */

import entity.User;

import java.util.Map;

public interface UserService {

    /*
     * @description: 检测查询的账户是否以备锁定
     * @param username: 用户名
     * @return boolean: true代表已经被锁定，false:代表未被锁定
     * @author: mouse
     * @date 2020/3/5
    */
    Map<String,Object> AccountIsLocked(String username);

    /*
     * @description: 判断账号、密码是否正确
     * @param username 账号
     * @param password 密码
     * @return boolean ：true =》正确，false =》错误
     * @author: mouse
     * @date 2020/3/5
    */
    boolean CheckRight(String username,String password);

    
    /*
     * @description: 判断用户是否输入信息错误过，并更新Redis中的信息
     * @param username ：用户名
     * @return java.lang.String ： 返回给用户的提示信息
     * @author: mouse
     * @date 2020/3/5
    */ 
    String checkErrorKey(String username);
}
