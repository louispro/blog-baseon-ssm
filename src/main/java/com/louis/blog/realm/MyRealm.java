package com.louis.blog.realm;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.louis.blog.bean.Blogger;
import com.louis.blog.service.BloggerService;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private BloggerService bloggerService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //权限验证
    //token:令牌，基于用户名密码的令牌
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        Blogger blogger = bloggerService.getBloggerByUsername(username);
        if(blogger != null){
            SecurityUtils.getSubject().getSession().setAttribute("currentUser",blogger);
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(blogger.getUsername(),
                    blogger.getPassword(),getName());
            return authenticationInfo;
        }
        return null;
    }
}
