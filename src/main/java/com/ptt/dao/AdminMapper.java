package com.ptt.dao;

import com.ptt.vo.Emp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminMapper {
    void addUser(Emp emp);

    List<Emp> findAll();

    Emp findUserById(String username);

    void deleteUserById(String username);

    void updateUser(@Param("username") String username,@Param("super_id") String super_id);
}
