package com.louis.blog.util;

import java.awt.dnd.MouseDragGestureRecognizer;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class CryptographyUtil {

    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }

    public static void main(String[] args) {
        System.out.println(md5("123456","admin"));
    }
}
