package com.louis.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.blog.bean.Blogger;
import com.louis.blog.dao.BloggerDao;
import com.louis.blog.service.BloggerService;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
@Service
public class BloggerServiceImpl implements BloggerService {

    @Autowired
    private BloggerDao bloggerDao;

    @Override
    public Blogger getBloggerByUsername(String username) {
        return bloggerDao.getBloggerByUsername(username);
    }
}
