package com.example.bookshop.exception;/**
 * @Auther: shu'ai
 * @Date: 2021/10/19 10:06
 * @Description:
 */

import com.example.bookshop.constants.StatusConstant;

import com.example.bookshop.pojo.ResultMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName:inteceptor
 * @Auther: shu'ai
 * @Description: 自定义异常捕获
 * @Date: 2021/10/19 10:06
 * @Version: v1.0
 */
@RestControllerAdvice
public class ControllerAdvices {

    /**
     * 处理服务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = myException.class)
    public ResultMap<?> errorHandler(myException e) {
        return new ResultMap(false, StatusConstant.ERROR, e.getMessage());
    }

    /**
     * 处理参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultMap<?> errorHandler(MethodArgumentNotValidException e) {
        return new ResultMap(false, StatusConstant.ERROR, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 处理系统异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultMap<?> errorHandler(Exception e) {
        e.printStackTrace();
        return new ResultMap(false, StatusConstant.SYSTEM_ERROR, "系统异常");
    }

}
