package com.example.demo.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel(description = "用户信息")
public class UserPO implements Serializable {
    @ApiModelProperty(value = "用户id" ,example = "11")
    private Long uid;
    @ApiModelProperty(value = "用户姓名",example = "小明")
    private String name;
    @ApiModelProperty(value = "用户年龄",example = "25")
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
