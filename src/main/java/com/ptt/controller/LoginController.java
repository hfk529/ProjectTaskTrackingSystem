package com.ptt.controller;

import com.ptt.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/goLogin")
    public String login(@RequestParam String username, @RequestParam String password, @RequestParam String flag, HttpSession session) {
        if (username == null || password == null || username.length() <= 0 || password.length() <= 0) {
            session.setAttribute("login_msg", "用户名为空");
            return "login";
        }
        int verifyMessage = loginService.verify(username, password, flag);
        switch (verifyMessage) {
            case 0:
                session.setAttribute("login_msg", "账号或密码输入错误");
                return "login";
            case 1:
                if (flag.equals("1")) {
                    session.setAttribute("loginName", username);
                    return "managerMain";
                } else if (flag.equals("2")) {
                    session.setAttribute("loginName", username);
                    return "deptMain";
                } else if (flag.equals("3")) {
                    session.setAttribute("loginName", username);
                    return "staffMain";
                }
            case 2:
                session.setAttribute("login_msg", "身份错误");
                return "login";
        }
        return null;
    }

    @RequestMapping("/AdminLogin")
    public String toAdminLogin() {
        return "loginAdmin";
    }

    @RequestMapping("/goAdminLogin")
    public String adminLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (username == null || password == null || username.length() <= 0 || password.length() <= 0) {
            session.setAttribute("login_msg", "用户名为空");
            return "loginAdmin";
        }
        int verifyMessage = loginService.verify(username, password);
        switch (verifyMessage) {
            case 0:
                session.setAttribute("login_msg", "账号或密码输入错误");
                return "loginAdmin";
            case 1:
                session.setAttribute("loginName", username);
                return "adminMain";
        }
        return null;
    }
}
