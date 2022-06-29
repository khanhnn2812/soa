package com.hitex.yousim.dto.request.area;

import com.hitex.yousim.model.Area;
import lombok.Data;

@Data
public class AreaReq extends Area {
    private String textSearch;


}