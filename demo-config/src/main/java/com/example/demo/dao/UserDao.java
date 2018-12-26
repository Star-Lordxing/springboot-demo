package com.example.demo.dao;

import com.example.demo.po.UserPO;
import java.util.List;

public interface UserDao {
    public List<UserPO> findAll() ;

    public int save(UserPO userPO);
}
