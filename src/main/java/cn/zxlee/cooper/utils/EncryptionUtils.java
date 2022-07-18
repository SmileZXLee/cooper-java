package cn.zxlee.cooper.utils;

import cn.zxlee.cooper.exception.CooperDecryptFailedException;
import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @program: cooper
 * @description: 加密工具类
 * @author: zxlee
 * @create: 2022-07-16 19:18
 **/
public class EncryptionUtils {
    public static String md5(String str) {
        try {
            return DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String sha1(String data){
        try {
            MessageDigest md = null;
            md = MessageDigest.getInstance("SHA1");
            byte[] b = data.getBytes();
            md.update(b);
            byte[] b2 = md.digest();
            int len = b2.length;
            String str = "0123456789abcdef";
            char[] ch = str.toCharArray();
            char[] chs = new char[len * 2];
            for(int i=0,k=0;i<len;i++) {
                byte b3 = b2[i];
                chs[k++] = ch[b3 >>> 4 & 0xf];
                chs[k++] = ch[b3 & 0xf];
            }
            return new String(chs);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }

    public static String wxDecrypt(String sessionKey, String encryptedData, String iv){
        try {
            byte[] encData = new byte[0];
            encData = Base64.decode(encryptedData);
            byte[] ivBytes = Base64.decode(iv);
            byte[] keyBytes = Base64.decode(sessionKey);
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            return new String(cipher.doFinal(encData), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new CooperDecryptFailedException("微信解密失败，请检查encryptedData，vi，sessionKey是否正确");
        }
    }

    public static Map<String,Object> wxDecryptMap(String sessionKey, String encryptedData, String iv){
        String result = wxDecrypt(sessionKey, encryptedData, iv);
        if (StringUtils.hasText(result)) {
            return JSON.parseObject(result).getInnerMap();
        } else {
            return null;
        }
    }

    public static String map2OrderedStr(Map<String,Object> map) {
        StringBuilder result = new StringBuilder();
        Set set = map.keySet();
        Object[] arr = set.toArray();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++){
            String key = (String)arr[i];
            result.append(key).append("=").append(map.get(key).toString());
            if (i < arr.length - 1) {
                result.append("&");
            }
        }
        return result.toString();
    }

}
