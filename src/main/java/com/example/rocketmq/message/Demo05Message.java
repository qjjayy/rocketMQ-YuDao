package com.example.rocketmq.message;

public class Demo05Message {

    public static final String TOPIC = "DEMO_05";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public Demo05Message setId(Integer id) {
        this.id = id;
        return this;
    }
}
