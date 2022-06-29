package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.hoaDon.HoaDonReq;
import com.hitex.yousim.dto.response.hoadon.HoaDonRes;
import com.hitex.yousim.dto.response.hoadon.HoaDonResponse;
import com.hitex.yousim.service.HoaDonService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class HoaDonController extends BaseController {
    @Autowired
    HoaDonService hoaDonService;

    @PostMapping(value = "themHoaDon")
    @ResponseBody
    public ResponseEntity themHoaDon(@RequestBody BaseRequestData<HoaDonReq> requestData) throws ApplicationException {
        try {
            hoaDonService.themHoaDon(requestData);
            return success();
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

//    @PostMapping(value = "suaHoaDon")
//    @ResponseBody
//    public ResponseEntity suaHoaDon(@RequestBody BaseRequestData<HoaDonReq> requestData) throws ApplicationException {
//        try {
//            hoaDonService.suaHoaDon(requestData);
//            return success();
//        } catch (ApplicationException e) {
//            return error(e.getCode(), e.getMessage());
//        }
//    }


    @PostMapping(value = "chiTietHoaDon")
    @ResponseBody
    public ResponseEntity chiTietHoaDon(@RequestBody BaseRequestData<HoaDonReq> requestData) throws ApplicationException {
        try {
            HoaDonResponse response = hoaDonService.chiTietHoaDon(requestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "thanhToanHoaDon")
    @ResponseBody
    public ResponseEntity thanhToanHoaDon(@RequestBody BaseRequestData<HoaDonReq> requestData) throws ApplicationException {
        try {
            boolean response = hoaDonService.thanhToanHoaDon(requestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "getListBill")
    @ResponseBody
    public ResponseEntity getHoaDonChuaTTByMaKH(@RequestBody BaseRequestData<HoaDonReq> requestData) throws ApplicationException {
        try {
            HoaDonRes response = hoaDonService.getHoaDonChuaTTByMaKH(requestData);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}

