package cn.zxlee.test.response;

import cn.zxlee.test.enums.ResponseStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result<T> {
    /**
     * response timestamp.
     */
    private long timestamp;

    /**
     * response code, 0 -> OK.
     */
    private int status;

    /**
     * response message.
     */
    private String message;

    /**
     * response data.
     */
    private T data;

    /**
     * response success result wrapper.
     *
     * @param <T> type of data class
     * @return response result
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * response success result wrapper.
     *
     * @param data response data
     * @param <T>  type of data class
     * @return response result
     */
    public static <T> Result<T> success(T data) {
        return Result.<T>builder().data(data)
                .message(ResponseStatus.SUCCESS.getMsg())
                .status(ResponseStatus.SUCCESS.getCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }


    public static <T> Result<T> fail(int code, String message) {
        return Result.<T>builder().data(null)
                .message(message)
                .status(code)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * response error result wrapper.
     *
     * @param message error message
     * @param <T>     type of data class
     * @return response result
     */
    public static <T> Result<T> fail(String message) {
        return fail(ResponseStatus.FAIL.getCode(), message);
    }
}
