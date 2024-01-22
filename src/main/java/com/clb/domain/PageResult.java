package com.clb.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页返回结果
 * @param <T> 返回数据的类型，泛型
 */
@Data
public class PageResult<T> implements Serializable {
    Integer code;
    String msg = "";
    Long total = 0L;
    T data;

    public static <T> PageResult<T> success() {
        PageResult<T> result = new PageResult<>();
        result.code = 1;
        return result;
    }

    public static <T> PageResult<T> success(T data, Long total) {
        PageResult<T> result = new PageResult<>();
        result.data = data;
        result.code = 1;
        result.total = total;
        return result;
    }

    public static <T> PageResult<T> error(String msg) {
        PageResult<T> result = new PageResult<>();
        result.code = 0;
        result.msg = msg;
        return result;
    }

}
