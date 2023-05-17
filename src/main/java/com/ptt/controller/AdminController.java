package com.ptt.controller;

import com.ptt.service.IAdminService;
import com.ptt.vo.Emp;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @RequestMapping("/toAdminMain")
    public String toAdminMain() {
        return "adminMain";
    }

    @RequestMapping("/toAddUser")
    public String toAddUser() {
        return "adminUserAdd";
    }

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request) {
        Emp emp = new Emp();

        //注册转换器，将string类型数据转换为date类型
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class aClass, Object o) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    return formatter.parse(o.toString());
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                return new Date();
            }
        }, Date.class);

        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装数据
        try {
            BeanUtils.populate(emp, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        adminService.addUser(emp);

        return "adminMain";
    }

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request) {
        List<Emp> empList = adminService.findAll();
        request.setAttribute("empList", empList);
        return "adminUserList";
    }

    @RequestMapping(value = "showUser", params = "username")
    public String findUserById(String username, HttpServletRequest request) {
        Emp emp = adminService.findUserById(username);
        request.setAttribute("emp", emp);
        return "adminUserShow";
    }

    @RequestMapping(value = "deleteUser", params = "username")
    public String deleteUserById(String username) {
        adminService.deleteUserById(username);
        return "adminUserList";
    }

    @RequestMapping("getNoAssignList")
    public String getNoAssignList(HttpServletRequest request) {
        List<Emp> noAssignList = new ArrayList<Emp>();
        List<Emp> empList = adminService.findAll();
        for (Emp emp : empList) {
            if (null == emp.getSuper_id()) {
                noAssignList.add(emp);
            }
        }
        request.setAttribute("noAssignList", noAssignList);
        return "adminUserAssignList";
    }

    @RequestMapping(value = "toSetAssign", params = "username")
    public String toSetAssign(String username, HttpServletRequest request) {
        Emp emp = adminService.findUserById(username);
        //查询主管信息
        List<Emp> deptList = new ArrayList<>();
        List<Emp> empList = adminService.findAll();
        for (Emp emp1 : empList) {
            if (emp1.getFlag().equals("2")) {

                deptList.add(emp1);
            }
        }
        request.setAttribute("deptList", deptList);
        request.setAttribute("emp", emp);
        return "adminUserAssign";
    }

    @RequestMapping("setAssign")
    public String setNoAssignList(String username, String super_id) {
        adminService.updateUser(username, super_id);
        return "forward:/getNoAssignList";
    }

    @RequestMapping("toAdminUpdateAdmin")
    public String toAdminUpdateAdmin(HttpSession session,HttpServletRequest request){
        String username = session.getAttribute("loginName").toString();
        Emp emp = adminService.findUserById(username);
        request.setAttribute("emp",emp);
        return "adminUpdateAdmin";
    }

//    @RequestMapping("updateAdmin")
//    public String updateAdmin(){
//        return "";
//    }

    @RequestMapping("showAdmin")
    public String showAdmin(HttpSession session,HttpServletRequest request){
        String username = session.getAttribute("loginName").toString();
        Emp emp = adminService.findUserById(username);
        request.setAttribute("emp",emp);
        return "adminShowAdmin";
    }
}
