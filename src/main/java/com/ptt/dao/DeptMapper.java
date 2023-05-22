package com.ptt.dao;

import com.ptt.vo.Emp;
import com.ptt.vo.Plan;
import com.ptt.vo.Task;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface DeptMapper {
    //查询所有员工
    List<Emp> findAll();

    List<Emp> findUserBySuperId(String super_id);

    //根据员工编号查询员工信息
    Emp findUserById(String username);

    //多表查询未实施项目信息列表，调整项目
    List<Map<String, String>> findAdjustList();

    //将调整后的项目信息存入
    void adjustSave(Map<String, String> map);

    //制订项目
    void addTask(Map<String, String> map);

    //根据员工姓名获取员工信息
    Emp findUserByName(String name);

    //查看项目和实施人
    List<Map<String, Object>> findTaskEmp();

    //通过外键查找关联的计划
    List<Plan> findPlanById(@Param("task_id") String id);

    //通过计划的id查找关联的计划
    Plan findPlanByPlanId(String id);

    //根据id获取所属项目
    Task findTaskByPlanId(String id);

    //根据id修改项目信息
    void updateTaskById(@Param("id") String id,@Param("task_state") String task_state);

    void deleteTaskByTaskName(String task_name);

    List<Task> findAllTask();

    List<Plan> dimLook(Map<String, String> map);

    void addPlan(@Param("plan") Plan plan, @Param("id") String id);

    List<Plan> findAllPlan();

    void updateEmp(Emp emp);
}
