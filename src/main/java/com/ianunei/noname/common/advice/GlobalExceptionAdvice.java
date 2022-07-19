package com.ianunei.noname.common.advice;

import com.ianunei.noname.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author 帅小鸦
 * @date 2022/5/12
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    /**
     * 对 MethodArgumentNotValidException 进行统一处理</h2>
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result handlerException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        if (fieldError != null) {
            return Result.failure(1001, String.format("参数: [%s], 值: [%s], 错误信息: [%s]",
                    fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()));
        } else {
            return Result.failure(1001, ex.getMessage());
        }
    }

    /**
     * 对 Exception 进行统一处理</h2>
     */
    @ExceptionHandler({Exception.class})
    public Result handlerException(Exception ex) {
        log.error(getStackTrace(ex));
        return Result.failure(1000, ex.getMessage());
    }

    private String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
