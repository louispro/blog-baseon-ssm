package com.louis.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.louis.blog.bean.Blogger;
import com.louis.blog.util.CryptographyUtil;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

    @RequestMapping("/login")
    public String login(Blogger blogger, HttpServletRequest request){
        String username = blogger.getUsername();
        String password = blogger.getPassword();
        String pw = CryptographyUtil.md5(password,username);
        //get subject
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,pw);
        try{
            //传递token给shiro的realm
            subject.login(token);
            return "redirect:/admin/main.jsp";
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("blogger",blogger);
            request.setAttribute("errorInfo","用户名/密码错误");
        }
        return "login";
    }
}
