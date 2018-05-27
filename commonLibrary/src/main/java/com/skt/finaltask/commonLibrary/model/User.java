package com.skt.finaltask.commonLibrary.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Table( name = "user_tbl")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "getAllUsers",
                procedureName = "find_all_users", resultClasses = User.class),
        @NamedStoredProcedureQuery(name = "addUser",
                procedureName = "insert_new_user",
                parameters = {
                        @StoredProcedureParameter(name = "name", type = String.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "age", type = Integer.class, mode = ParameterMode.IN)
                })
})
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    public User() {
    }

    public User(String name, int age) {
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
        return "User{" +
                ", name='" + name + '\'' +
                ", Age=" + age +
                '}';
    }
}