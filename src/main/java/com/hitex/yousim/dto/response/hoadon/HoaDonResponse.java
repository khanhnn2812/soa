package com.hitex.yousim.dto.response.hoadon;

import com.hitex.yousim.model.ChiTietHoaDon;
import com.hitex.yousim.model.HoaDon;
import com.hitex.yousim.model.KhachHang;
import lombok.Data;

import java.util.List;

@Data
public class HoaDonResponse {
    HoaDon HoaDon;
    KhachHang khachHang;
    List<ChiTietHoaDon> listChiTietHoaDon;

}
