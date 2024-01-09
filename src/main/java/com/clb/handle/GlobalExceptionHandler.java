package com.clb.handle;

import com.clb.constant.Excep;
import com.clb.domain.Result;
import com.clb.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.SocketException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获业务异常
     */
    @ExceptionHandler
    public Result<String> exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 完整性约束冲突异常
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        if (ex.getMessage().contains("book")) {
            return Result.error(Excep.DELETE_BOOK_NOT_ALLOW);
        } else {
            return Result.error(Excep.UNKNOWN_ERROR);
        }
    }

    /**
     * redis未开启可能导致的异常
     */
    @ExceptionHandler(SocketException.class)
    public Result<String> exceptionHandler(SocketException socketException) {
        log.error("检查redis是否开启 " + socketException.getMessage());
        return Result.error("SocketException!检查redis是否开启");
    }

}
