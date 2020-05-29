package com.example.rocketmq.message;

public class Demo02Message {

    public static final String TOPIC = "DEMO_02";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public Demo02Message setId(Integer id) {
        this.id = id;
        return this;
    }
}
