package com.skt.finaltask.microservice.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_tbl")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "getAllUsers",
            procedureName = "find_all_users", resultClasses = UserRecord.class),
        @NamedStoredProcedureQuery(name = "addUser",
            procedureName = "insert_new_user",
        parameters = {
                @StoredProcedureParameter(name = "name", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "age", type = Integer.class, mode = ParameterMode.IN)
        })
})
public class UserRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    public UserRecord() {
    }

    public UserRecord(String name, Integer age) {
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
