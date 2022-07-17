package cn.zxlee.cooper.exception;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-17 17:44
 **/
public class CooperDecryptFailedException extends RuntimeException {
    public CooperDecryptFailedException(String message) {
        super(String.format("%s,如有问题请查阅文档",message));
    }
}
