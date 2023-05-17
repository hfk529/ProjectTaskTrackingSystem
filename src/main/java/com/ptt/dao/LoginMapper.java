package com.ptt.dao;

import com.ptt.vo.Emp;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
    Emp verify(String username);
}
