package com.heima.article.test;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.article.ArticleApplication;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.service.ApArticleService;
import com.heima.file.service.FileStorageService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ArticleFreemarkerTest
 * Package: com.heima.article.test
 * Description:
 *
 * @Author R
 * @Create 2024/5/13 16:38
 * @Version 1.0
 */
@SpringBootTest(classes = ArticleApplication.class)
public class ArticleFreemarkerTest {
    @Autowired
    private Configuration configuration;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Autowired
    private ApArticleService apArticleService;
    @Test
    public  void test() throws IOException, TemplateException {
        //获取文章内容 1302862387124125698
        ApArticleContent apArticleContent = apArticleContentMapper.selectOne(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId, 1383827787629252610L));
        //2.文章内容通过freemarker生成html文件
        if(apArticleContent != null && StringUtils.isNotBlank(apArticleContent.getContent())){
            Template template = configuration.getTemplate("article.ftl");
            Map<String, Object> params = new HashMap<>();
            params.put("content", JSONArray.parseArray(apArticleContent.getContent()));
            StringWriter out = new StringWriter();
            template.process(params,out);
            //3.把html文件上传到minio中
            InputStream is = new ByteArrayInputStream(out.toString().getBytes());
            String path = fileStorageService.uploadHtmlFile("", apArticleContent.getArticleId() + ".html", is);
            //4.修改ap_article表，保存static_url字段
            ApArticle apArticle = new ApArticle();
            apArticle.setId(apArticleContent.getArticleId());
            apArticle.setStaticUrl(path);
            apArticleService.updateById(apArticle);
        }


    }
}
