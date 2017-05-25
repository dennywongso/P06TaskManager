package com.example.a15017096.p06taskmanager;

import java.io.Serializable;

/**
 * Created by 15017096 on 25/5/2017.
 */

public class Task implements Serializable {
    private String name;
    private String desc;
    private int id;

    public Task(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }
    public Task(String name, String desc){
        this.name = name;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
