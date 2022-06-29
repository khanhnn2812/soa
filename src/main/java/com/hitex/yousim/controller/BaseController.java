package com.hitex.yousim.controller;

import com.hitex.yousim.dto.response.BaseResponseData;
import com.hitex.yousim.utils.MessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    protected  <T> ResponseEntity success(T respone) {
        BaseResponseData baseResponseData= new BaseResponseData();

        baseResponseData.setSuccess(MessageUtils.getMessage("true"));
        baseResponseData.setData(respone);
        return new ResponseEntity(baseResponseData, HttpStatus.OK);
    }
    protected  <T > ResponseEntity error(String code, String message) {
        BaseResponseData baseResponseData= new BaseResponseData();
//        baseResponseData.setErrorCode(code);
//        baseResponseData.setMessage(message);
        return new ResponseEntity(baseResponseData, HttpStatus.OK);
    }

    protected ResponseEntity success() {
        BaseResponseData baseResponseData= new BaseResponseData();
//        baseResponseData.setErrorCode("200");
//        baseResponseData.setMessage(MessageUtils.getMessage("success"));
        return new ResponseEntity(baseResponseData, HttpStatus.OK);
    }
}
