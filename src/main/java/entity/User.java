package entity;
/*
 *created by mouse on 2020/3/4
 */

import java.io.Serializable;
/*
* User实体类
* */

public class User implements Serializable {


    private Long id;
    private String username;
    private String password;

    //  获取用户登录错误的KEY
    public static String getErrorKey(String username){
        return "PwdError:" + username;
    }

    //  获取用户账户被锁定的KEY
    public static String getLockKey(String username) {
        return "LockAccount:" + username;
    }




    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
