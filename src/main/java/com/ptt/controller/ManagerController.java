package com.ptt.controller;

import com.ptt.service.IManagerService;
import com.ptt.vo.Emp;
import com.ptt.vo.Plan;
import com.ptt.vo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerController {
    @Autowired
    private IManagerService managerService;

    @RequestMapping("/toManagerMain")
    public String toDeptMain() {
        return "managerMain";
    }

    @RequestMapping("/managerTaskList")
    public String getDeptTaskList(HttpServletRequest request) {
        List<Map<String, Object>> taskList = managerService.findTaskEmp();
        request.setAttribute("taskList", taskList);
        return "managerTaskList";
    }

    @RequestMapping("/managerTaskShow")
    public String showDeptTask(String id, HttpServletRequest request) {
        List<Map<String, Object>> taskEmpList = managerService.findTaskEmp();
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
        List<Plan> planList = managerService.findPlanById(id);

        //将计划存入request域中
        request.setAttribute("planList", planList);
        return "managerTaskShow";
    }

    @RequestMapping(value = "/managerPlanShow", params = "id")
    public String showDeptPlan(String id, HttpServletRequest request) {
        Plan plan = managerService.findPlanByPlanId(id);
        //将plan存入request域中
        request.setAttribute("plan", plan);

        //根据id获取所属任务
        Task task = managerService.findTaskByPlanId(plan.getTask_id());
        request.setAttribute("task", task);
        return "managerPlanShow";
    }

    @RequestMapping("/toManagerTaskAdd")
    public String toDeptTaskAdd(HttpServletRequest request) {
        List<Emp> staffNameList = managerService.findAll();
        request.setAttribute("staffNameList", staffNameList);
        return "managerTaskAdd";
    }

    @RequestMapping("managerTaskAdd")
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
        Emp emp = managerService.findUserByName(name);
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
        managerService.addTask(map);
        List<Map<String, Object>> taskList = managerService.findTaskEmp();
        request.setAttribute("taskList", taskList);
        return "managerTaskList";
    }

    @RequestMapping("/managerTaskAdjustList")
    public String getDeptTaskAdjustList(HttpServletRequest request) {
        List<Map<String, String>> mapList = managerService.findAdjustList();
        //存入request域中
        request.setAttribute("mapList", mapList);
        return "managerTaskAdjustList";
    }

    @RequestMapping(value = "managerDeleteTask", params = "task_name")
    public String deptDeleteTask(String task_name, HttpServletRequest request) {
        managerService.deleteTaskByTaskName(task_name);
        List<Map<String, Object>> taskList = managerService.findTaskEmp();
        request.setAttribute("taskList", taskList);
        return "managerTaskList";
    }

    @RequestMapping(value = "/toManagerAdjust", params = "task_name")
    public String toDeptAdjust(String task_name, HttpServletRequest request) {
        List<Map<String, String>> mapList = managerService.findAdjustList();
        //遍历，找到与任务名称相同的map,并存入request域中
        for (Map<String, String> map : mapList) {
            if (task_name.equals(map.get("task_name"))) {
                request.setAttribute("map", map);
                break;
            }
        }

        //将所有实施人的信息存入request域中
        List<Emp> empList = managerService.findAll();
        request.setAttribute("emps", empList);
        return "managerTaskAdjust";
    }

    @RequestMapping("/managerAdjustSave")
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
        managerService.adjustSave(map);

        return "managerTaskAdjustList";
    }

    @RequestMapping(value = "/managerTaskFollowShow", params = "id")
    public String deptTaskFollowShow(String id, HttpServletRequest request) {
        Task task = managerService.findTaskByPlanId(id);
        //根据任务的外键查询任务所对应的计划信息
        String task_id = String.valueOf(task.getId());
        List<Plan> planList = managerService.findPlanById(task_id);
        //将任务信息和计划信息存入request域中
        request.setAttribute("task", task);
        request.setAttribute("planList", planList);
        return "managerFollowShow";
    }

    @RequestMapping("/managerTaskFollowList")
    public String getDeptTaskFollowList(HttpServletRequest request) {
        List<Map<String, Object>> taskEmpList = managerService.findTaskEmp();
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
        return "managerTaskFollow";
    }

    @RequestMapping("toManagerUpdateManager")
    public String toManagerUpdateManager(HttpSession session,HttpServletRequest request){
        String username = session.getAttribute("loginName").toString();
        Emp emp = managerService.findUserById(username);
        request.setAttribute("emp",emp);
        return "managerUpdateManager";
    }

    @RequestMapping("showManager")
    public String showManager(HttpSession session,HttpServletRequest request){
        String username = session.getAttribute("loginName").toString();
        Emp emp = managerService.findUserById(username);
        request.setAttribute("emp",emp);
        return "managerShowManager";
    }
}
