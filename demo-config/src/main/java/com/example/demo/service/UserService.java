package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<UserPO> findAll() {
        return userDao.findAll();
    }


    @Transactional
    public void addUser(){
        UserPO userPO1 = new UserPO("王小飞",24);
        userDao.save(userPO1);
        addUser1();
    }

    public void addUser1(){
        UserPO userPO1 = new UserPO("王小飞1",24);
        userDao.save(userPO1);
        //throw new RuntimeException();
    }
}
