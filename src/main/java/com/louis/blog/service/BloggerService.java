package com.louis.blog.service;

import org.springframework.stereotype.Service;

import com.louis.blog.bean.Blogger;

@Service
public interface BloggerService {

    Blogger getBloggerByUsername(String username);
}
