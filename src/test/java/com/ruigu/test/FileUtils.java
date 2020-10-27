package com.ruigu.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hugangquan
 * @date 2020/10/20 18:10
 */
public class FileUtils {

    public static List<String> get(String filename) throws Exception {
        FileInputStream fis = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        List<String> list = new ArrayList<>();
        String str = null;
        while ((str=br.readLine() )!= null){
            list.add(str.trim());
        }
        return list;
    }

}
