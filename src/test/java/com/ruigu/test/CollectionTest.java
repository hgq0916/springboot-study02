package com.ruigu.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionTest {

    @Test
    public void test1(){
        List<String> list1 = Arrays.asList("zhangsan","lisi");
        List<String> list2 = new ArrayList<>();
        list1.iterator().forEachRemaining(list2::add);

        System.out.println(list2);
    }


}
