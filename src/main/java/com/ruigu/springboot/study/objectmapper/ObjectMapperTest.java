package com.ruigu.springboot.study.objectmapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ObjectMapperTest {

    static ObjectMapper formatter = new ObjectMapper();

    static {
        // 序列化时，格式化输出的Json串
        //formatter.enable(SerializationFeature.INDENT_OUTPUT);
        // 序列化对象的所有属性
        //objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 只序列化不为null的字段
        //formatter.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // NULL、空字符串、空集合都不会被序列化
        //formatter.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        // 序列化时，对象为空不抛异常
        //formatter.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 反序列化的时候，Json串多了其他属性,不抛出异常
        //formatter.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 取消时间的转化格式（默认是时间戳），同时需要自行指定时间格式
        //formatter.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //formatter.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        //formatter.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,false);
        formatter.setConfig(formatter.getSerializationConfig().with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        formatter.setConfig(formatter.getDeserializationConfig().with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));//反序列化扩展日期格式支持

    }

    // 仅含基本类型属性的对象
    public static void ordinaryObjectTest() throws IOException {
        Student student = new Student("zhangsan", 19, new Date());
        System.out.println(student);

        //ObjectMapper objectMapper = new ObjectMapper();

        // 序列化
        String json = formatter.writeValueAsString(student);
        //String json = formatter.writeValueAsString(null);
        System.out.println(json);

        // 反序列化
        student = formatter.readValue(json, Student.class);
        System.out.println(student);
    }

    // 含List<Object>属性的对象，操作一样
    public static void hybridObjectTest() throws IOException {
        Student student1 = new Student("zhangsan", 19, new Date());
        Student student2 = new Student("lisi", 20, new Date());
        List<Student> studentList = Arrays.asList(student1, student2);
        ClassRoom classRoom = new ClassRoom();
        classRoom.setGrade("one");
        classRoom.setStudents(studentList);
        System.out.println(classRoom);

        ObjectMapper objectMapper = new ObjectMapper();

        // 序列化
        String json = objectMapper.writeValueAsString(classRoom);
        System.out.println(json);

        // 反序列化
        classRoom = objectMapper.readValue(json, ClassRoom.class);
        System.out.println(classRoom);
    }

    // List<String>
    public static void StringListTest() throws IOException {
        List<String> nameList = new ArrayList<>();
        nameList.add("zhangsan");
        nameList.add("lisi");
        System.out.println(nameList.toString());

        ObjectMapper objectMapper = new ObjectMapper();

        // 序列化
        String json = objectMapper.writeValueAsString(nameList);
        System.out.println(json);

        nameList = objectMapper.readValue(json, List.class);
        System.out.println(nameList.toString());
    }


    // List<Student>
    public static void ObjectListTest() throws IOException {
        Student student1 = new Student("zhangsan", 19, new Date());
        Student student2 = new Student("lisi", 20, new Date());
        List<Student> studentList = Arrays.asList(student1, student2);
        System.out.println(studentList.toString());

        ObjectMapper objectMapper = new ObjectMapper();

// 序列化
        String json = objectMapper.writeValueAsString(studentList);
        System.out.println(json);

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, Student.class);
        studentList = objectMapper.readValue(json, javaType);
        System.out.println(studentList);
    }

    // Map<String, String>
    public static void StringStringMapTest() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("name", "zhangsan");
        System.out.println(map);

        ObjectMapper objectMapper = new ObjectMapper();

        // 序列化
        String json = objectMapper.writeValueAsString(map);
        System.out.println(json);

        // 反序列化
        map = objectMapper.readValue(json, Map.class);
        System.out.println(map);
    }

    // Map<String, Student>
    public static void StringObjectMapTest() throws IOException {
        Map<String, Student> map = new HashMap<>();
        map.put("student", new Student("zhangsan", 19, new Date()));
        System.out.println(map);

        ObjectMapper objectMapper = new ObjectMapper();

        // 序列化
        String json = objectMapper.writeValueAsString(map);
        System.out.println(json);

        // 反序列化
        JavaType javaType
                = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Student.class);
        map = objectMapper.readValue(json, javaType);
        System.out.println(map);
    }




    public static void main(String[] args) throws Exception {
        ordinaryObjectTest();
        //hybridObjectTest();
        //StringListTest();
        //ObjectListTest();
        //StringStringMapTest();
        //StringObjectMapTest();
    }

}
