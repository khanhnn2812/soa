package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.area.AreaReq;
import com.hitex.yousim.dto.response.area.AreaRes;
import com.hitex.yousim.dto.response.area.AreaResponse;
import com.hitex.yousim.service.AreaService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class AreaController extends BaseController {
    @Autowired
    AreaService areaService;

    @PostMapping(value = "getListArea/{id}")
    @ResponseBody
    public ResponseEntity getListArea(@RequestBody BaseRequestData<AreaReq> requestData, @PathVariable("id") String id) throws ApplicationException {
        try {
            AreaRes response = areaService.getListArea(requestData, id);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}

