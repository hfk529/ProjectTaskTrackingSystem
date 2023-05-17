package com.ptt.service;

import com.ptt.vo.Emp;
import com.ptt.vo.Plan;
import com.ptt.vo.Task;

import java.util.List;
import java.util.Map;

public interface IDeptService {
    //查询所有员工
    List<Emp> findAll();

    List<Emp> findUserBySuperId(String super_id);

    List<Task> findAllTask();

    List<Plan> dimLook(Map<String, String> map);

    //根据员工编号查询员工信息
    Emp findUserById(String username);

    //多表查询未实施任务信息列表，调整任务
    List<Map<String, String>> findAdjustList();

    //将调整后的任务信息存入
    void adjustSave(Map<String, String> map);

    //制订任务
    void addTask(Map<String, String> map);

    //根据员工名称获取员工信息
    Emp findUserByName(String name);

    //查看任务和实施人
    List<Map<String, Object>> findTaskEmp();

    //通过外键查找关联的计划
    List<Plan> findPlanById(String id);

    //通过计划的id查找计划的详细信息
    Plan findPlanByPlanId(String id);

    //根据id获取所属任务
    Task findTaskByPlanId(String task_id);

    //根据id修改任务信息
    void updateTaskById(String id, String task_state);

    void deleteTaskByTaskName(String task_name);

    void addPlan(Plan plan, String id);

    List<Plan> findAllPlan();
}
