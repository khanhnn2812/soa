package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.autoid.AutoIdResponse;
import com.hitex.yousim.model.AutoId;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface AutoIdService {

    AutoId getAutoId(String code) throws ApplicationException;

}
