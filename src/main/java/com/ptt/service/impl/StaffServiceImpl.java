package com.ptt.service.impl;

import com.ptt.dao.DeptMapper;
import com.ptt.dao.StaffMapper;
import com.ptt.service.IStaffService;
import com.ptt.vo.Emp;
import com.ptt.vo.Plan;
import com.ptt.vo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Task findTaskIdByUserName(String currentUser,int id) {
        return staffMapper.findTaskIdByUserName(currentUser,id);
    }

    @Override
    public List<Plan> findPlanByTaskId(String task_id) {
        return staffMapper.findPlanByTaskId(task_id);
    }

    @Override
    public List<Map<String, Object>> findTaskById(String currentUser) {
        return deptMapper.findTaskEmp();
    }

    @Override
    public Emp findUserById(String currentUser) {
        return deptMapper.findUserById(currentUser);
    }

    @Override
    public void deletePlanById(String id) {
        staffMapper.deletePlanById(id);
    }

    @Override
    public void addPlan(Plan plan, String id) {
        staffMapper.addPlan(plan, id);
    }

    @Override
    public List<Plan> findAllPlan() {
        return staffMapper.findAllPlan();
    }

    @Override
    public Plan findPlanByPlanId(String id) {
        return staffMapper.findPlanByPlanId(id);
    }

    @Override
    public void updatePlanById(String id, String plan_state, String feedback) {
        staffMapper.updatePlanById(id, plan_state, feedback);
    }

    @Override
    public List<Task> findAllTask() {
        return staffMapper.findAllTask();
    }

    @Override
    public List<Plan> dimLook(Map<String, String> map) {
        return staffMapper.dimLook(map);
    }
}
