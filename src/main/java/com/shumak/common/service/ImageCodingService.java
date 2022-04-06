package com.shumak.common.service;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;

public class ImageCodingService {

    public static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.encodeBase64(bytes), "UTF-8");
    }
}
