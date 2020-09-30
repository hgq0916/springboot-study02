package com.ruigu.test;

import com.google.common.annotations.VisibleForTesting;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author hugangquan
 * @date 2020/09/16 09:18
 */
public class TestInteger {

    @Test
    public void swapTest(){
        Integer a = 2;
        Integer b = 4;
        System.out.printf("a=%s,b=%s\n",a,b);
        swap(a,b);
        System.out.printf("a=%s,b=%s\n",a,b);
    }

    private static  void swap(Integer a, Integer b) {

        try {

            /*Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            int aInt = field.getInt(a);
            Integer tmp = new Integer(aInt);
            field.setInt(a,field.getInt(b));
            field.setInt(b,tmp);*/

            int c = a.intValue();
            Field value = Integer.class.getDeclaredField("value");
            value.setAccessible(true);
            value.setInt(a,b.intValue());
            value.setInt(b,c);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
