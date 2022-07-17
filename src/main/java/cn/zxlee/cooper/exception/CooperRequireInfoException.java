package cn.zxlee.cooper.exception;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-16 20:29
 **/
public class CooperRequireInfoException extends RuntimeException {

    public CooperRequireInfoException(String message) {
        super(String.format("%s,如有问题请查阅文档",message));
    }
}
