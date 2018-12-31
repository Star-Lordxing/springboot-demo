package com.example.demo.dao;

import com.example.demo.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserDao {
    public List<UserPO> findAll() ;

    public int save(UserPO userPO);
}
