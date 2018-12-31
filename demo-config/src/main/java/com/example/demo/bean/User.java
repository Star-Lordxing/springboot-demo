
package com.example.demo.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


public class User implements Serializable {
    @ApiModelProperty(value = "用户id" ,example = "11" ,required=false)
    private Long uid;
    @ApiModelProperty(value = "用户姓名",example = "小明",required=false)
    private String name;
    @ApiModelProperty(value = "用户年龄",example = "25",required=false)
    private Integer age;

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
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

