package com.ruigu.springboot.study.objectmapper;

import lombok.Data;
import java.util.List;

@Data
public class ClassRoom {
    String grade;
    List<Student> students;
}
