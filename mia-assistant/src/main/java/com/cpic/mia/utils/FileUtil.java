package com.cpic.mia.utils;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

public class FileUtil {
    public static String loadConfig(String fileName) throws Exception {
        InputStream in = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
        String json =  IOUtils.toString(in, Charset.forName("UTF-8"));
        return json;
    }
}
