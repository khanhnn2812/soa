package com.hitex.yousim.dto.request.hoaDon;

import com.hitex.yousim.model.HoaDon;
import lombok.Data;

@Data
public class HoaDonReq extends HoaDon {
    private String textSearch;
}
