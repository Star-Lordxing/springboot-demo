package com.starter.demo;

import java.util.Date;

public class GeneraterIdService {
    public String generaterId(){
        return new Date().getTime()+"";
    }
}
