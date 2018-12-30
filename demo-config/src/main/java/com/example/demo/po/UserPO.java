package com.example.demo.po;

import java.io.Serializable;

public class UserPO implements Serializable {



    private Long uid;
    private String name;
    private Integer age;

    public UserPO(){}

    public UserPO(Long uid, String name, Integer age) {
        this.uid = uid;
        this.name = name;
        this.age = age;
    }

    public UserPO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserPO{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
