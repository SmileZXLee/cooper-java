package cn.zxlee.test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    SUCCESS(0, "ok"),
    FAIL(500, "failed");


    /**
     * response code
     */
    private final int code;

    /**
     * message.
     */
    private final String msg;

}
