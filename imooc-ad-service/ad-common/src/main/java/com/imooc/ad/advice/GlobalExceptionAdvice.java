package com.imooc.ad.advice;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionAdvice {
    /**
     * process AdException
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdExcepetion(HttpServletRequest req,
                                                      AdException ex) {
        CommonResponse<String> response = new CommonResponse<>(-1,"business error");
        response.setData((ex.getMessage()));
        return response;
    }
}
