package com.example.rocketmq.message;

public class Demo06Message {

    public static final String TOPIC = "DEMO_06";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public Demo06Message setId(Integer id) {
        this.id = id;
        return this;
    }
}
