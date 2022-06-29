package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.hoadon.HoaDonRes;
import com.hitex.yousim.dto.response.hoadon.HoaDonResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface HoaDonService {
    boolean themHoaDon(BaseRequestData baseRequestData) throws ApplicationException;
    //boolean suaHoaDon(BaseRequestData baseRequestData) throws ApplicationException;
    HoaDonResponse chiTietHoaDon(BaseRequestData baseRequestData) throws ApplicationException;
    boolean thanhToanHoaDon(BaseRequestData baseRequestData) throws ApplicationException;
    HoaDonRes getHoaDonChuaTTByMaKH(BaseRequestData baseRequestData) throws ApplicationException;
}
