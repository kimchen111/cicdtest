package com.ianunei.noname.common.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 帅小鸦
 * @date 2022/5/12
 */

@Data
public class Result implements Serializable {

    public boolean isSuccess() {
        return Objects.equals(code, 0);
    }

    private int code;

    private String message;

    private Object data;

    public static Result success(Object data) {
        return Result.of(0, null, data);
    }

    public static Result success() {
        return Result.success(null);
    }

    public static Result failure(int code, String message) {
        return Result.of(code, message, null);
    }

    public static Result of(int code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}