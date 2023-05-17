package com.ptt.service.impl;

import com.ptt.dao.DeptMapper;
import com.ptt.service.IDeptService;
import com.ptt.vo.Emp;
import com.ptt.vo.Plan;
import com.ptt.vo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeptServiceImpl implements IDeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Emp> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public List<Emp> findUserBySuperId(String super_id) {
        return deptMapper.findUserBySuperId(super_id);
    }

    @Override
    public List<Task> findAllTask() {
        return deptMapper.findAllTask();
    }

    @Override
    public List<Plan> dimLook(Map<String, String> map) {
        return deptMapper.dimLook(map);
    }

    @Override
    public Emp findUserById(String username) {
        return deptMapper.findUserById(username);
    }

    @Override
    public List<Map<String, String>> findAdjustList() {
        return deptMapper.findAdjustList();
    }

    @Override
    public void adjustSave(Map<String, String> map) {
        deptMapper.adjustSave(map);
    }

    @Override
    public void addTask(Map<String, String> map) {
        deptMapper.addTask(map);
    }

    @Override
    public Emp findUserByName(String name) {
        return deptMapper.findUserByName(name);
    }

    @Override
    public List<Map<String, Object>> findTaskEmp() {
        return deptMapper.findTaskEmp();
    }

    @Override
    public List<Plan> findPlanById(String id) {
        return deptMapper.findPlanById(id);
    }

    @Override
    public Plan findPlanByPlanId(String id) {
        return deptMapper.findPlanByPlanId(id);
    }

    @Override
    public Task findTaskByPlanId(String task_id) {
        return deptMapper.findTaskByPlanId(task_id);
    }

    @Override
    public void updateTaskById(String id, String task_state) {
        deptMapper.updateTaskById(id, task_state);
    }

    @Override
    public void deleteTaskByTaskName(String task_name) {
        deptMapper.deleteTaskByTaskName(task_name);
    }

    @Override
    public void addPlan(Plan plan, String id) {
        deptMapper.addPlan(plan, id);
    }

    @Override
    public List<Plan> findAllPlan() {
        return deptMapper.findAllPlan();
    }
}
