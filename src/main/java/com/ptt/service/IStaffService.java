package com.ptt.service;

import com.ptt.vo.Emp;
import com.ptt.vo.Plan;
import com.ptt.vo.Task;

import java.util.List;
import java.util.Map;

public interface IStaffService {
    //根据员工编号获取到任务id
    Task findTaskIdByUserName(String currentUser, int id);

    List<Plan> findPlanByTaskId(String task_id);

    List<Map<String, Object>> findTaskById(String currentUser);

    Emp findUserById(String currentUser);

    void deletePlanById(String id);

    void addPlan(Plan plan, String id);

    List<Plan> findAllPlan();

    Plan findPlanByPlanId(String id);

    void updatePlanById(String id,String plan_state,String feedback);

    List<Task> findAllTask();

    List<Plan> dimLook(Map<String, String> map);

    void updateEmp(Emp emp);
}
