package com.skt.finaltask.microservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_tbl")
public class UserRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private int age;
    public UserRecord() {
    }

    public UserRecord(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "UserRecord{" +
                ", name='" + name + '\'' +
                ", Age=" + age +
                '}';
    }
}
