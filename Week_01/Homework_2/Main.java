package com.huawei.demo.week01;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = "/Users/jack/git/JAVA-000/Week_01/Homework_2/hello.xlass";
            XlassLoader XlassLoader = new XlassLoader();
            Class helloClass = XlassLoader.findClass("Hello", filePath);
            Method helloMethod = helloClass.getMethod("hello");
            helloMethod.invoke(helloClass.newInstance());
        } catch (IOException | IllegalAccessException | InstantiationException
                | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
