package com.example.rocketmq.message;

public class Demo04Message {

    public static final String TOPIC = "DEMO_04";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public Demo04Message setId(Integer id) {
        this.id = id;
        return this;
    }
}
