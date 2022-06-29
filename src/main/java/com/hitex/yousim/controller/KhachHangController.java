package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.khachHang.KhachHangReq;
import com.hitex.yousim.dto.response.khachhang.KhachHangRes;
import com.hitex.yousim.dto.response.khachhang.KhachHangResponse;
import com.hitex.yousim.service.KhachHangService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class KhachHangController extends BaseController {
    @Autowired
    KhachHangService khachHangService;

    @PostMapping(value = "themKhachHang")
    @ResponseBody
    public ResponseEntity themKhachHang(@RequestBody BaseRequestData<KhachHangReq> requestData) throws ApplicationException {
        try {
            int id = khachHangService.themKhachHang(requestData);
            return success(id);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "getListKhachHang")
    @ResponseBody
    public ResponseEntity getListKhachHang(@RequestBody BaseRequestData<KhachHangReq> requestData) throws ApplicationException {
        try {
            KhachHangRes response = khachHangService.getListKhachHang(requestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "getListKhachHangByTrangThai")
    @ResponseBody
    public ResponseEntity getListKhachHangByTrangThai(@RequestBody BaseRequestData<KhachHangReq> requestData) throws ApplicationException {
        try {
            KhachHangRes response = khachHangService.getListKhachHangByTrangThai(requestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "suaKhachHang")
    @ResponseBody
    public ResponseEntity suaKhachHang(@RequestBody BaseRequestData<KhachHangReq> requestData) throws ApplicationException {
        try {
            khachHangService.suaKhachHang(requestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
    @PostMapping(value = "xoaKhachHang/{id}")
    @ResponseBody
    public ResponseEntity xoaKhachHang(@RequestBody BaseRequestData<KhachHangReq> requestData, @PathVariable("id") int id) throws ApplicationException {
        try {
            khachHangService.xoaKhachHang(requestData, id);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "chiTietKhachHang")
    @ResponseBody
    public ResponseEntity chiTietKhachHang(@RequestBody BaseRequestData<KhachHangReq> requestData) throws ApplicationException {
        try {
            KhachHangResponse response = khachHangService.chiTietKhachHang(requestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}

