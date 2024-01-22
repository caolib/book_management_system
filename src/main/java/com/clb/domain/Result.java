package com.clb.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一的返回结果对象
 * @param <T> 返回的数据类型，泛型
 */

@Data
public class Result<T> implements Serializable {
    Integer code;
    String msg = "";
    T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = 1;
        result.data = data;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.code = 0;
        result.msg = msg;
        return result;
    }
    public static <T> Result<T> error(Integer code,String msg) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        return result;
    }
}
