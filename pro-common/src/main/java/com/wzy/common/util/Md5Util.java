package com.wzy.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5Util {

   //生成MD5  
   public static String getMD5(String message) {  
       String md5 = "";  
       try {  
           MessageDigest md = MessageDigest.getInstance("MD5");  // 创建一个md5算法对象  
           byte[] messageByte = message.getBytes();  
           byte[] md5Byte = md.digest(messageByte);              // 获得MD5字节数组,16*8=128位  
           md5 = bytesToHex(md5Byte);                            // 转换为16进制字符串  
       } catch (Exception e) {  
           e.printStackTrace();  
       }  
       return md5;  
   }

   public static String getMd5(byte[] data){
       String md5 = "";
       try {
           MessageDigest md = MessageDigest.getInstance("MD5");  // 创建一个md5算法对象
           byte[] md5Byte = md.digest(data);              // 获得MD5字节数组,16*8=128位
           md5 = bytesToHex(md5Byte);                            // 转换为16进制字符串
       } catch (Exception e) {
           e.printStackTrace();
       }
       return md5;
   }

    // 二进制转十六进制  
   public static String bytesToHex(byte[] bytes) {  
       StringBuffer hexStr = new StringBuffer();  
       int num;  
       for (int i = 0; i < bytes.length; i++) {  
           num = bytes[i];  
            if(num < 0) {  
                num += 256;  
           }  
           if(num < 16){  
               hexStr.append("0");  
           }  
           hexStr.append(Integer.toHexString(num));  
       }  
       return hexStr.toString().toLowerCase();  
   }

    /**
     * 获取上传文件的md5
     *
     * @param file
     * @return
     */
    public static String getMd5(MultipartFile file) {

        try {
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            String hashString = new BigInteger(1, digest).toString(16);
            return hashString;
        } catch (Exception e) {

        }
        return null;
    }
}
