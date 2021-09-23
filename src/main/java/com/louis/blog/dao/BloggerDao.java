package com.louis.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.louis.blog.bean.Blogger;

@Repository
public interface BloggerDao {

    Blogger getBloggerByUsername(@Param("username") String username);


}
