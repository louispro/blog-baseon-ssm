package com.louis.blog.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.louis.blog.bean.Blogger;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BloggerDaoTest {

    @Autowired
    private BloggerDao bloggerDao;

    @Test
    public void getBloggerByUsername() {
        Blogger blogger = bloggerDao.getBloggerByUsername("admin");
        System.out.println(blogger);
    }
}
