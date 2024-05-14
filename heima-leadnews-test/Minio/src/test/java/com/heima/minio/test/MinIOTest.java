package com.heima.minio.test;

import com.heima.file.service.FileStorageService;
import com.heima.minio.MinIOApplication;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName: MinIOTest
 * Package: com.heima.minio.test
 * Description:
 *
 * @Author R
 * @Create 2024/5/13 15:44
 * @Version 1.0
 */
@SpringBootTest(classes = MinIOApplication.class)
public class MinIOTest {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream =  new FileInputStream("D:\\lists.html");
            MinioClient minioClient = MinioClient.builder().credentials("minio", "minio123")
                    .endpoint("http://192.168.200.130:9000").build();
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("lists.html")//文件名
                    .contentType("text/html")//文件类型
                    .bucket("leadnews")//桶名词  与minio创建的名词一致
                    .stream(fileInputStream, fileInputStream.available(), -1)
                    .build();
            minioClient.putObject(putObjectArgs);
            System.out.println("http://192.168.200.130:9000/leadnews/lists.html");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    @Autowired
    private FileStorageService fileStorageService;
    @Test
    public void test2() {
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\lists.html");
            String filePath = fileStorageService.uploadHtmlFile("", "lists.html", fileInputStream);
            System.out.println(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
