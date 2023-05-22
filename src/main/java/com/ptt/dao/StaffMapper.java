package com.ptt.dao;

import com.ptt.vo.Emp;
import com.ptt.vo.Plan;
import com.ptt.vo.Task;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StaffMapper {
    //根据员工编号获取到任务id
    Task findTaskIdByUserName(@Param("staff_id") String currentUser, @Param("id") int id);

    List<Plan> findPlanByTaskId(String task_id);

    List<Task> findTaskById(@Param("staff_id") String currentUser);

    void deletePlanById(String id);

    void addPlan(@Param("plan") Plan plan, @Param("id") String id);

    List<Plan> findAllPlan();

    Plan findPlanByPlanId(String id);

    void updatePlanById(@Param("id") String id, @Param("plan_state") String plan_state, @Param("feedback") String feedback);

    List<Task> findAllTask();

    List<Plan> dimLook(Map<String, String> map);

    void updateEmp(Emp emp);
}
