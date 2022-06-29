package com.hitex.yousim.dto.request;

import lombok.Data;

/**
 *
 * @author Chidq
 */
@Data
public class BaseRequestData<T> {
    
    private String wsCode;
    private String sessionId;
    private String token;
    private T wsRequest;
}
