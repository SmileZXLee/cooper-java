package cn.zxlee.cooper.utils;

import java.util.Random;

/**
 * @program: cooper
 * @description: 随机数生成工具类
 * @author: zxlee
 * @create: 2022-07-17 20:53
 **/
public class RandomUtils {
    public static String getRandomStr(int length){
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int a = r.nextInt(3);
            switch (a) {
                case 0:
                    char ac = (char)(r.nextInt(26) + 65);
                    code.append(ac);
                    break;
                case 1:
                    char kl = (char)(r.nextInt(26) + 97);
                    code.append(kl);
                    break;
                case 2:
                    int mk = r.nextInt(10);
                    code.append(mk);
                    break;
            }
        }
        return code.toString();
    }
}
