package com.hitex.yousim.dto.request.khachHang;

import com.hitex.yousim.model.KhachHang;
import lombok.Data;

@Data
public class KhachHangReq extends KhachHang {
    private String textSearch;
}
