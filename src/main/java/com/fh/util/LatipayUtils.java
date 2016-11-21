package com.fh.util;

import org.apache.commons.codec.digest.DigestUtils;
public class LatipayUtils {
    /**
     * 对字符串进行MD5签名
     * 
     * @param text
     *            明文
     * 
     * @return 密文
     */
    public static String md5(String text) {
    	 try {
    		 return DigestUtils.md5Hex(text.getBytes("utf-8"));
    	 } catch (Exception e) {
             throw new RuntimeException("MD5签名过程中出现错误!");
         }
    }
}
