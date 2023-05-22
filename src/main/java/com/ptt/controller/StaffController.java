package com.ptt.controller;

import com.ptt.service.IStaffService;
import com.ptt.vo.Emp;
import com.ptt.vo.Plan;
import com.ptt.vo.Task;
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
import java.util.*;

@Controller
public class StaffController {
    @Autowired
    private IStaffService staffService;

    @RequestMapping("/staffTaskList")
    public String getStaffTaskList(HttpSession session, HttpServletRequest request) {
        String currentUser = session.getAttribute("loginName").toString();
        List<Map<String, Object>> mapList1 = staffService.findTaskById(currentUser);

        //筛选出符合当前用户的任务
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Map<String, Object> map : mapList1) {
            if ((map.get("staff_id").toString()).equals(currentUser)) {
                mapList.add(map);
            }
        }
        //将任务信息存入request域中
        request.setAttribute("mapList", mapList);
        return "staffTaskList";
    }

    @RequestMapping(value = "/toAddStaffPlan", params = "id")
    public String toAddStaffPlan(String id, HttpServletRequest request) {
        request.setAttribute("id", id);
        return "staffPlanAdd";
    }

    @RequestMapping("/staffPlanAdd")
    public String staffPlanAdd(String id, HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();

        Plan plan = new Plan();
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

        try {
            BeanUtils.populate(plan, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        staffService.addPlan(plan, id);
        List<Plan> planList = staffService.findAllPlan();
        request.setAttribute("planList", planList);
        return "staffPlanList";
    }

    @RequestMapping(value = "/staffTaskPlan", params = "id")
    public String getStaffTaskPlan(String id, HttpSession session, HttpServletRequest request) {
        String currentUser = session.getAttribute("loginName").toString();
        //先根据员工编号获取到任务id
        int i = Integer.parseInt(id);
        Task task = staffService.findTaskIdByUserName(currentUser, i);
        //通过task_id查询全部计划
        List<Plan> planList = staffService.findPlanByTaskId(id);
        //查询出实施人
        Emp emp = staffService.findUserById(currentUser);
        request.setAttribute("emp", emp);
        //将planList存入request域中
        request.setAttribute("planList", planList);
        //将task存入request域中
        request.setAttribute("task", task);
        return "staffTaskPlanShow";
    }

    @RequestMapping(value = "/staffFeedbackBefore",params = "id")
    public String staffFeedbackBefore(String id,HttpServletRequest request){
        Plan plan = staffService.findPlanByPlanId(id);
        request.setAttribute("plan",plan);
        return "staffPlanFeedback";
    }

    @RequestMapping("/staffLookBefore")
    public String lookStaffPlanBefore(HttpServletRequest request) {
        List<Task> taskList = staffService.findAllTask();
        request.setAttribute("taskList", taskList);
        return "staffPlanLook";
    }

    @RequestMapping("/staffLook")
    public String lookStaffPlan(HttpServletRequest request) {
        String plan_name = request.getParameter("plan_name");
        String task_id = request.getParameter("task_id");
        String feedback = request.getParameter("feedback");

        Map<String, String> map = new HashMap<>();
        map.put("plan_name", plan_name);
        map.put("task_id", task_id);
        map.put("feedback", feedback);

        List<Plan> planList = staffService.dimLook(map);

        List<Task> taskList = staffService.findAllTask();
        request.setAttribute("taskList", taskList);

        request.setAttribute("planList", planList);
        return "staffPlanLook";
    }

    @RequestMapping("/staffPlanList")
    public String getStaffPlanList(HttpServletRequest request) {
        List<Plan> planList = staffService.findAllPlan();
        request.setAttribute("planList", planList);
        return "staffPlanList";
    }

    @RequestMapping("toStaffUpdateStaff")
    public String toStaffUpdateStaff(HttpSession session,HttpServletRequest request){
        String username = session.getAttribute("loginName").toString();
        Emp emp = staffService.findUserById(username);
        request.setAttribute("emp",emp);
        return "staffUpdateStaff";
    }

    @RequestMapping("updateStaff")
    public String updateStaff(HttpSession session, HttpServletRequest request) {
        String username = session.getAttribute("loginName").toString();
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
        staffService.updateEmp(emp);
        return "staffMain";
    }

    @RequestMapping("showStaff")
    public String showStaff(HttpSession session,HttpServletRequest request){
        String username = session.getAttribute("loginName").toString();
        Emp emp = staffService.findUserById(username);
        request.setAttribute("emp",emp);
        return "staffShowStaff";
    }

    @RequestMapping("staffFeedback")
    public String staffFeedback(HttpServletRequest request){
        String plan_state = request.getParameter("plan_state");
        String feedback = request.getParameter("feedback");
        String id = request.getParameter("id");

        staffService.updatePlanById(id,plan_state,feedback);

        List<Plan> planList = staffService.findAllPlan();
        request.setAttribute("planList",planList);
        return "staffPlanList";
    }
}
