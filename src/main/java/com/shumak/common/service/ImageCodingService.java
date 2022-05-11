package com.shumak.common.service;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageCodingService {

    public static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.encodeBase64(bytes), "UTF-8");
    }

    public static File decodeFileFromBase64Binary(String data, long id, HttpServletRequest request) throws IOException {
        BufferedImage image;
        byte[] imageByte;
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        imageByte = Base64.decodeBase64(data);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();
        String userDirectory = System.getProperty("user.dir");
        File dir = new File(userDirectory + "\\src\\main\\resources\\images");
        dir.mkdirs();
        File outputFile = new File(dir.getPath() + "\\image" + id +".png");
        ImageIO.write(image, "png", outputFile);

        return outputFile;
    }
}
