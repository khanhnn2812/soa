package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.khachhang.KhachHangRes;
import com.hitex.yousim.dto.response.khachhang.KhachHangResponse;
import com.hitex.yousim.model.KhachHang;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface KhachHangService {
    int themKhachHang(BaseRequestData baseRequestData) throws ApplicationException;
    KhachHangRes getListKhachHang(BaseRequestData baseRequestData) throws ApplicationException;
    KhachHangRes getListKhachHangByTrangThai(BaseRequestData baseRequestData) throws ApplicationException;
    boolean suaKhachHang(BaseRequestData baseRequestData) throws ApplicationException;
    boolean xoaKhachHang(BaseRequestData baseRequestData, int id) throws ApplicationException;
    KhachHangResponse chiTietKhachHang(BaseRequestData baseRequestData) throws ApplicationException;
    KhachHang getByMaKH(String maKH);
}
