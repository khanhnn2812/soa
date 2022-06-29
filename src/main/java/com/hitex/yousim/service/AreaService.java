package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.area.AreaRes;
import com.hitex.yousim.dto.response.area.AreaResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface AreaService {
    public AreaRes getListArea(BaseRequestData baseRequestData, String id) throws ApplicationException;

}
