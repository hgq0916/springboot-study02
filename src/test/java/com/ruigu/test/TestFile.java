package com.ruigu.test;

import org.junit.Test;
import org.nlpcn.commons.lang.util.IOUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author hugangquan
 * @date 2020/09/17 14:33
 */
public class TestFile {

    @Test
    public void fun1() throws IOException {
        Resource resource = new ClassPathResource("dict/default.dic");
        List<String> strings = IOUtil.readFile2List(resource.getInputStream(), StandardCharsets.UTF_8.name());
        System.out.println(strings);
    }

    @Test
    public void fun2() throws IOException {
        Resource resource = new ClassPathResource("dict/ambiguity.dic");
        List<String> terms = IOUtil.readFile2List(resource.getInputStream(), StandardCharsets.UTF_8.name());
        for (String term : terms) {
            String[] i = term.split("\\s+");
            if (i.length == 2) {
                System.out.println(term);
            }
        }
    }

}
