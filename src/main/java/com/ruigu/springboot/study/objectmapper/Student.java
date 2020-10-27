package com.ruigu.springboot.study.objectmapper;

import lombok.Data;
import java.util.Date;

@Data
public class Student {
    String name;
    int age;
    Date birth;

    public Student(){

    }
    public Student(String name, int age, Date birth) {
        this.name = name;
        this.age = age;
        this.birth = birth;
    }
}

