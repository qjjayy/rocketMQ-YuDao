package com.example.rocketmq.message;

public class Demo03Message {

    public static final String TOPIC = "DEMO_03";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public Demo03Message setId(Integer id) {
        this.id = id;
        return this;
    }
}
