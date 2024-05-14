package com.itheima.wemedia;

import com.alibaba.fastjson.JSONArray;
import com.heima.common.aliyun.GreenImageScan;
import com.heima.common.aliyun.GreenTextScan;
import com.heima.file.service.FileStorageService;
import com.heima.wemedia.WemediaApplication;
import com.heima.wemedia.service.WmNewsAutoScanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Map;

/**
 * ClassName: test
 * Package: com.itheima.wemedia
 * Description:
 *
 * @Author R
 * @Create 2024/5/14 16:42
 * @Version 1.0
 */

@SpringBootTest(classes = WemediaApplication.class)
public class test {
    @Autowired
    private GreenTextScan greenTextScan;

    @Autowired
    private GreenImageScan greenImageScan;

    @Autowired
    private FileStorageService fileStorageService;
    /**
     * 测试文本内容审核
     */
    @Test
    public void testTest() throws Exception {
        Map map = greenTextScan.greeTextScan("我是一个好人,冰毒");
        System.out.println(map);
    }
    /**
     * 测试图片内容审核
     */
    @Test
    public void testImages() throws Exception {
        byte[] bytes = fileStorageService.downLoadFile("http://192.168.200.130:9000/leadnews/2021/04/26/ef3cbe458db249f7bd6fb4339e593e55.jpg");
        Map map = greenImageScan.imageScan(Arrays.asList(bytes));
        System.out.println(map);
    }

    @Autowired
    private WmNewsAutoScanService wmNewsAutoScanService;

    @Test
    public void autoScanWmNews() {

//        wmNewsAutoScanService.autoScanWmNews(6232);
        String jsonString = "[{\"name\":\"Alice\", \"age\":25}, {\"name\":\"Bob\", \"age\":30}]";

        // 解析JSON数组字符串
        JSONArray jsonArray = JSONArray.parseArray(jsonString);

        // 输出JSONArray对象
        System.out.println(jsonArray);

    }

}
