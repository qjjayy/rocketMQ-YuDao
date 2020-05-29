package com.example.rocketmq.message;

public class Demo07Message {

    public static final String TOPIC = "DEMO_07";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public Demo07Message setId(Integer id) {
        this.id = id;
        return this;
    }
}
