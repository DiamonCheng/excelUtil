package com.ed.excel.util;

import com.dc.excel.util.ExcelHead;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/19.
 */
@ExcelHead("用户")
public class User {
    @ExcelHead("账号")
    private String name;
    @ExcelHead("密码")
    private String password;
    
    public String getPassword() {
        return password;
    }
    
    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    
    public String getName() {
    
        return name;
    }
    
    public User setName(String name) {
        this.name = name;
        return this;
    }
}
