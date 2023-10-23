package com.keti.iam.idthub.util;

import com.keti.iam.idthub.util.response.Response;
import com.keti.iam.idthub.util.stringBuilder.StringBuilderUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StringBuilderTest {

    @Test
    public void stringBuilderTest(){
        String test = StringBuilderUtil.build("test", " test", " yes");
        System.out.println("test = " + test);
    }

    @Test
    public void stringBuilderIterator(){
        TestDTO test1 = new TestDTO("test1");
        TestDTO test2 = new TestDTO("test2");
        TestDTO test3 = new TestDTO("test3");

        List<TestDTO> testList = new ArrayList<>();
        testList.add(test1);
        testList.add(test2);
        testList.add(test3);
        testList.add(test1);

        StringBuilderUtilList stringBuilderUtilList = new StringBuilderUtilList();
        for (TestDTO testDTO : testList) {
            stringBuilderUtilList.stringBuilderAdd(testDTO.getName());
        }
        String testString = stringBuilderUtilList.resultString();
        System.out.println("testString = " + testString);


        testList.remove(0);
        for (TestDTO testDTO : testList) {
            stringBuilderUtilList.stringBuilderAdd(testDTO.getName());
        }
        String testString1 = stringBuilderUtilList.resultString();
        System.out.println("testString1 = " + testString1);
    }

    @Data
    public class TestDTO{
        private String name;

        public TestDTO(String name) {
            this.name = name;
        }
    }
    private class StringBuilderUtilList {
        private final List<String> stringList = new ArrayList<>();

        public List<String> stringBuilderAdd(String value) {
            stringList.add(value);
            return stringList;
        }
        public String resultString(){
            StringBuilder stringBuilder = new StringBuilder();
            for (String value : stringList) {
                stringBuilder.append(value);
            }
            stringList.clear();
            return stringBuilder.toString();
        }
    }
}