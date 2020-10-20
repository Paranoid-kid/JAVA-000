package com.huawei.demo.week01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class XlassLoader extends ClassLoader {

    Class findClass(String name, String filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        int len = bytes.length;
        for (int i = 0; i < len; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return this.defineClass(name, bytes, 0, len);
    }
}
