package com.ptt.controller;

import com.ptt.service.IDeptService;
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
public class DeptController {
    @Autowired
    private IDeptService deptService;

    @RequestMapping("/toDeptMain")
    public String toDeptMain() {
        return "deptMain";
    }

    @RequestMapping("/deptTaskList")
    public String getDeptTaskList(HttpServletRequest request) {
        List<Map<String, Object>> taskList = deptService.findTaskEmp();
        request.setAttribute("taskList", taskList);
        return "deptTaskList";
    }

    @RequestMapping("/deptTaskShow")
    public String showDeptTask(String id, HttpServletRequest request) {
        List<Map<String, Object>> taskEmpList = deptService.findTaskEmp();
        //遍历找到符合id的任务
        Map<String, Object> currentTask = new HashMap<>();
        for (Map<String, Object> task : taskEmpList) {
            if (id.equals(task.get("id").toString())) {
                currentTask = task;
                break;
            }
        }

        //将当前任务信息存入request域中
        request.setAttribute("currentTask", currentTask);
        //通过外键查找关联的计划
        List<Plan> planList = deptService.findPlanById(id);

        //将计划存入request域中
        request.setAttribute("planList", planList);
        return "deptTaskShow";
    }

    @RequestMapping(value = "/deptPlanShow", params = "id")
    public String showDeptPlan(String id, HttpServletRequest request) {
        Plan plan = deptService.findPlanByPlanId(id);
        //将plan存入request域中
        request.setAttribute("plan", plan);

        //根据id获取所属任务
        Task task = deptService.findTaskByPlanId(plan.getTask_id());
        request.setAttribute("task", task);
        return "deptPlanShow";
    }

    @RequestMapping("/toDeptTaskAdd")
    public String toDeptTaskAdd(HttpServletRequest request) {
        List<Emp> staffNameList = deptService.findAll();
        request.setAttribute("staffNameList", staffNameList);
        return "deptTaskAdd";
    }

    @RequestMapping("deptTaskAdd")
    public String deptTaskAdd(HttpServletRequest request, HttpSession session) {
        //从jsp页面获取数据
        String task_name = request.getParameter("task_name");
        String name = request.getParameter("name");
        String task_end_time = request.getParameter("task_end_time");
        String task_description = request.getParameter("task_description");
        String task_state = request.getParameter("task_state");
        String task_begin_time = request.getParameter("task_begin_time");
        //获取员工编号
        String staff_id=session.getAttribute("loginName").toString();
        Emp emp = deptService.findUserByName(name);
        String emp_id = emp.getUsername();
        //将获取的值封装为map集合
        Map<String, String> map = new HashMap<>();
        map.put("task_name", task_name);
        map.put("task_end_time", task_end_time);
        map.put("task_description", task_description);
        map.put("task_state", task_state);
        map.put("emp_id", emp_id);
        map.put("task_begin_time", task_begin_time);
        map.put("staff_id", staff_id);
        //调用service中的方法进行添加
        deptService.addTask(map);
        List<Map<String, Object>> taskList = deptService.findTaskEmp();
        request.setAttribute("taskList", taskList);
        return "deptTaskList";
    }

    @RequestMapping("/deptTaskAdjustList")
    public String getDeptTaskAdjustList(HttpServletRequest request) {
        List<Map<String, String>> mapList = deptService.findAdjustList();
        //存入request域中
        request.setAttribute("mapList", mapList);
        return "deptTaskAdjustList";
    }

    @RequestMapping(value = "deptDeleteTask", params = "task_name")
    public String deptDeleteTask(String task_name, HttpServletRequest request) {
        deptService.deleteTaskByTaskName(task_name);
        List<Map<String, Object>> taskList = deptService.findTaskEmp();
        request.setAttribute("taskList", taskList);
        return "deptTaskList";
    }

    @RequestMapping(value = "/toDeptAdjust", params = "task_name")
    public String toDeptAdjust(String task_name, HttpServletRequest request) {
        List<Map<String, String>> mapList = deptService.findAdjustList();
        //遍历，找到与任务名称相同的map,并存入request域中
        for (Map<String, String> map : mapList) {
            if (task_name.equals(map.get("task_name"))) {
                request.setAttribute("map", map);
                break;
            }
        }

        //将所有实施人的信息存入request域中
        List<Emp> empList = deptService.findAll();
        request.setAttribute("emps", empList);
        return "deptTaskAdjust";
    }

    @RequestMapping("/deptAdjustSave")
    public String deptAdjustSave(HttpServletRequest request) {
        String id = request.getParameter("id");
        String task_name = request.getParameter("task_name");
        String name = request.getParameter("name");
        String task_begin_time = request.getParameter("task_begin_time");
        String task_end_time = request.getParameter("task_end_time");
        String task_description = request.getParameter("task_description");
        String task_state = request.getParameter("task_state");

        //获取实施人的编号
        //将获取的值封装为map集合
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("task_name", task_name);
        map.put("name", name);
        map.put("task_begin_time", task_begin_time);
        map.put("task_end_time", task_end_time);
        map.put("task_description", task_description);
        map.put("task_state", task_state);

        //调用service中的方法，将数据存储进去
        deptService.adjustSave(map);

        return "deptTaskAdjustList";
    }

    @RequestMapping(value = "/deptTaskFollowShow", params = "id")
    public String deptTaskFollowShow(String id, HttpServletRequest request) {
        Task task = deptService.findTaskByPlanId(id);
        //根据任务的外键查询任务所对应的计划信息
        String task_id = String.valueOf(task.getId());
        List<Plan> planList = deptService.findPlanById(task_id);
        //将任务信息和计划信息存入request域中
        request.setAttribute("task", task);
        request.setAttribute("planList", planList);
        return "deptFollowShow";
    }

    @RequestMapping("/deptTaskFollowList")
    public String getDeptTaskFollowList(HttpServletRequest request) {
        List<Map<String, Object>> taskEmpList = deptService.findTaskEmp();
        //创建集合存储实施中的任务
        List<Map<String, Object>> following = new ArrayList<>();
        //循环筛选出未实施的任务
        for (Map<String, Object> map : taskEmpList) {

            if (map.get("task_state").equals("实施中")) {
                following.add(map);
            }
        }
        //将实施中的任务存到request域中
        request.setAttribute("following", following);
        return "deptTaskFollow";
    }

    @RequestMapping("/deptUserList")
    public String getDeptUserList(HttpSession session,HttpServletRequest request) {
        String username = session.getAttribute("loginName").toString();
        Emp emp = deptService.findUserById(username);
        String super_id = emp.getSuper_id();
        List<Emp> empList = deptService.findUserBySuperId(super_id);
        //将查询结果存入request域中
        request.setAttribute("empList", empList);
        return "deptUserList";
    }

    @RequestMapping(value = "deptFindUserById",params = "username")
    public String deptFindUserById(String username,HttpServletRequest request){
        Emp emp = deptService.findUserById(username);
        //将员工信息存入request域中
        request.setAttribute("emp",emp);
        return "deptUserShow";
    }

    @RequestMapping("toDeptUpdateDept")
    public String toDeptUpdateDept(HttpSession session,HttpServletRequest request){
        String username = session.getAttribute("loginName").toString();
        Emp emp = deptService.findUserById(username);
        request.setAttribute("emp",emp);
        return "deptUpdateDept";
    }

    @RequestMapping("showDept")
    public String showDept(HttpSession session,HttpServletRequest request){
        String username = session.getAttribute("loginName").toString();
        Emp emp = deptService.findUserById(username);
        request.setAttribute("emp",emp);
        return "deptShowDept";
    }

    @RequestMapping("/deptLookBefore")
    public String lookDeptPlanBefore(HttpServletRequest request) {
        List<Task> taskList = deptService.findAllTask();
        request.setAttribute("taskList", taskList);
        return "deptPlanLook";
    }

    @RequestMapping("/deptLook")
    public String lookDeptPlan(HttpServletRequest request) {
        String plan_name = request.getParameter("plan_name");
        String task_id = request.getParameter("task_id");
        String feedback = request.getParameter("feedback");

        Map<String, String> map = new HashMap<>();
        map.put("plan_name", plan_name);
        map.put("task_id", task_id);
        map.put("feedback", feedback);

        List<Plan> planList = deptService.dimLook(map);

        List<Task> taskList = deptService.findAllTask();
        request.setAttribute("taskList", taskList);

        request.setAttribute("planList", planList);
        return "deptPlanLook";
    }

    @RequestMapping(value = "/toAddDeptPlan", params = "id")
    public String toAddStaffPlan(String id, HttpServletRequest request) {
        request.setAttribute("id", id);
        return "deptPlanAdd";
    }

    @RequestMapping("/deptPlanAdd")
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

        deptService.addPlan(plan, id);
        List<Plan> planList = deptService.findAllPlan();
        request.setAttribute("planList", planList);
        return "deptPlanList";
    }
}
