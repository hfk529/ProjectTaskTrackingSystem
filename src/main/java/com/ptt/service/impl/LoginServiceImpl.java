package com.ptt.service.impl;

import com.ptt.dao.LoginMapper;
import com.ptt.service.ILoginService;
import com.ptt.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public int verify(String username, String password, String flag) {
        if (username == null) {
            return 0;//此处判断防止空指针异常
        }
        Emp emp = loginMapper.verify(username);
        if (username.equals(emp.getUsername()) && password.equals(emp.getPassword())) {
            //账号密码正确，判断登录身份
            if (flag.equals(emp.getFlag())) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }

    @Override
    public int verify(String username, String password) {
        if (username == null) {
            return 0;//此处判断防止空指针异常
        }
        Emp emp = loginMapper.verify(username);
        if (username.equals(emp.getUsername()) && password.equals(emp.getPassword())) {
            //账号密码正确，判断登录身份
            if ("0".equals(emp.getFlag())) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
