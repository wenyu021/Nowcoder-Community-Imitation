package com.newcoder.community;

import com.newcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author Wenyu Wang
 * @project community
 * @date 2022-07
 * @Description
 */

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTest {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;


    @Test
    public void testTextMail(){
        mailClient.sendMail("2820208247@qq.com","zhangjunshizhu","ceshi");
    }

    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username","sunday");

        String content = templateEngine.process("/mail/DemoMail",context);
        System.out.println(content);

        mailClient.sendMail("2820208247@qq.com","zjsz",content);
    }
}
