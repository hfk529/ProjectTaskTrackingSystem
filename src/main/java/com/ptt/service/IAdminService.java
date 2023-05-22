package com.ptt.service;

import com.ptt.vo.Emp;

import java.util.List;

public interface IAdminService {
    void addUser(Emp emp);

    List<Emp> findAll();

    Emp findUserById(String username);

    void deleteUserById(String username);

    void updateUser(String username, String super_id);

    void updateEmp(Emp emp);
}
