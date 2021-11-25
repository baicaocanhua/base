package com.sleeper.base.advice;


import com.sleeper.base.enums.ResultCode;
import com.sleeper.base.exception.ApiException;
import com.sleeper.base.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author sleeper
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResultVO<String> apiExceptionHandler(ApiException e) {
        log.error("接口请求异常：{}{}",e.getResultCode(),e.getMsg());
        return new ResultVO<>(e.getResultCode(), e.getMsg());
    }

    @ExceptionHandler
    public ResultVO unknownException(Exception e) {
        log.error("发生了未知异常", e);
        return new ResultVO<>(ResultCode.ERROR, "系统出现错误, 请联系网站管理员!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }

}
