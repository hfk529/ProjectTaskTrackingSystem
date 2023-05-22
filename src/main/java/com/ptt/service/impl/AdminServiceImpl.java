package com.ptt.service.impl;

import com.ptt.dao.AdminMapper;
import com.ptt.service.IAdminService;
import com.ptt.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void addUser(Emp emp) {
        adminMapper.addUser(emp);
    }

    @Override
    public List<Emp> findAll() {
        return adminMapper.findAll();
    }

    @Override
    public Emp findUserById(String username) {
        return adminMapper.findUserById(username);
    }

    @Override
    public void deleteUserById(String username) {
        adminMapper.deleteUserById(username);
    }

    @Override
    public void updateUser(String username, String super_id) {
        adminMapper.updateUser(username, super_id);
    }

    @Override
    public void updateEmp(Emp emp) {
        adminMapper.updateEmp(emp);
    }
}
