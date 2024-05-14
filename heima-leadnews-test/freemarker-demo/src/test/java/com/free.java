package com;

import com.heima.freemarker.FreemarkerDemotApplication;
import com.heima.freemarker.entity.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * ClassName: free
 * Package: com
 * Description:
 *
 * @Author R
 * @Create 2024/5/13 15:22
 * @Version 1.0
 */
@SpringBootTest(classes = FreemarkerDemotApplication.class)
public class free {
    @Autowired
    private Configuration configuration;

    @Test
    public void test() throws IOException, TemplateException {
        Template template = configuration.getTemplate("02-list.ftl");
        /**
         * 合成方法
         * 模型数据
         * 输出
         */
        template.process(getData(),new FileWriter("d:/lists.html"));
    }

    private Map getData() {

        Map<String,Object> map = new HashMap<>();
        Student stu1 = new Student();
        stu1.setName("小强");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());

        //小红对象模型数据
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);

        //将两个对象模型数据存放到List集合中
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);

        //向model中存放List集合数据
        map.put("stus",stus);

        //------------------------------------

        //创建Map数据
        HashMap<String,Student> stuMap = new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
        // 3.1 向model中存放Map数据
        map.put("stuMap", stuMap);
        //常数
        map.put("point", 102920122);
        return map;
    }
}
