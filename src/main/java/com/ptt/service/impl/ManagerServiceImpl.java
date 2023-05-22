package com.ptt.service.impl;

import com.ptt.dao.ManagerMapper;
import com.ptt.service.IManagerService;
import com.ptt.vo.Emp;
import com.ptt.vo.Plan;
import com.ptt.vo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Emp> findAll() {
        return managerMapper.findAll();
    }

    @Override
    public Emp findUserById(String username) {
        return managerMapper.findUserById(username);
    }

    @Override
    public List<Map<String, String>> findAdjustList() {
        return managerMapper.findAdjustList();
    }

    @Override
    public void adjustSave(Map<String, String> map) {
        managerMapper.adjustSave(map);
    }

    @Override
    public void addTask(Map<String, String> map) {
        managerMapper.addTask(map);
    }

    @Override
    public Emp findUserByName(String name) {
        return managerMapper.findUserByName(name);
    }

    @Override
    public List<Map<String, Object>> findTaskEmp() {
        return managerMapper.findTaskEmp();
    }

    @Override
    public List<Plan> findPlanById(String id) {
        return managerMapper.findPlanById(id);
    }

    @Override
    public Plan findPlanByPlanId(String id) {
        return managerMapper.findPlanByPlanId(id);
    }

    @Override
    public Task findTaskByPlanId(String task_id) {
        return managerMapper.findTaskByPlanId(task_id);
    }

    @Override
    public void updateTaskById(String id, String task_state) {
        managerMapper.updateTaskById(id, task_state);
    }

    @Override
    public void deleteTaskByTaskName(String task_name) {
        managerMapper.deleteTaskByTaskName(task_name);
    }

    @Override
    public void updateEmp(Emp emp) {
        managerMapper.updateEmp(emp);
    }
}
