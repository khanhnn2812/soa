package com.hitex.yousim.service.impl;


import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;

import com.hitex.yousim.dto.request.khachHang.KhachHangReq;
import com.hitex.yousim.dto.response.khachhang.KhachHangRes;
import com.hitex.yousim.dto.response.khachhang.KhachHangResponse;
import com.hitex.yousim.model.KhachHang;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.KhachHangRepo;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.AutoIdService;
import com.hitex.yousim.service.KhachHangService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    KhachHangRepo khachHangRepo;
    @Autowired
    AutoIdService autoIdService;

    @Override
    public int themKhachHang(BaseRequestData baseRequestData) throws ApplicationException {

        KhachHangReq khachHangReq = (KhachHangReq) baseRequestData.getWsRequest();
        //Tên, số đt, email, địa chỉ, cmtTruoc, cmtSau
        if(khachHangReq.getHoTen() == null || khachHangReq.getHoTen().equals("")){
            throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("Họ tên"));
        }
        if(khachHangReq.getDiaChi() == null || khachHangReq.getDiaChi().equals("")){
            throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("Địa chỉ"));
        }
        if(khachHangReq.getSoDienThoai() == null || khachHangReq.getSoDienThoai().equals("")){
            throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("Số điện thoại"));
        }
        if(khachHangReq.getCccdTruoc() == null || khachHangReq.getCccdTruoc().equals("")){
            throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("Căn cước công dân mặt trước"));
        }
        if(khachHangReq.getCccdSau() == null || khachHangReq.getCccdSau().equals("")){
            throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("Căn cước công dân mặt sau"));
        }

//        if(!khachHangReq.getHoTen().matches("[a-zA-Z ]+")){
//            throw new ApplicationException("ERR_00000010", MessageUtils.getMessage("Họ tên"));
//        }
//        if(!khachHangReq.getDiaChi().matches("[\\w ]+")){
//            throw new ApplicationException("ERR_00000010", MessageUtils.getMessage("Đia chỉ"));
//        }
//        if(!khachHangReq.getSoDienThoai().matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")){
//            throw new ApplicationException("ERR_00000010", MessageUtils.getMessage("Số điện thoại"));
//        }
//        if(!khachHangReq.getEmail().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")){
//            throw new ApplicationException("ERR_00000010", MessageUtils.getMessage("Email"));
//        }
//        if(!khachHangReq.getCccdTruoc().matches("https?:\\/\\/(?:[-\\w]+\\.)?([-\\w]+)\\.\\w+(?:\\.\\w+)?\\/?.*")){
//            throw new ApplicationException("ERR_00000010", MessageUtils.getMessage("Căn cước công dân mặt trước"));
//        }
//        if(!khachHangReq.getCccdSau().matches("https?:\\/\\/(?:[-\\w]+\\.)?([-\\w]+)\\.\\w+(?:\\.\\w+)?\\/?.*")){
//            throw new ApplicationException("ERR_00000010", MessageUtils.getMessage("Căn cước công dân mặt sau"));
//        }

        try {
//            User user = userRepository.findUserBySessionAngToken(baseRequestData.getToken());
//            if (ObjectUtils.isEmpty(user)) {
//                throw new ApplicationException("ERR_0000003");
//            }
            KhachHang kh = new KhachHang();
            BeanUtils.copyProperties(khachHangReq, kh);
            kh.setTrangThai(Constant.taoMoi);
            khachHangRepo.save(kh);
            return kh.getIdKhachHang();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public KhachHangRes getListKhachHang(BaseRequestData baseRequestData) throws ApplicationException {
        KhachHangRes khachHangRes = new KhachHangRes();
        KhachHangReq khachHangReq = (KhachHangReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            List<KhachHang> listXe = khachHangRepo.getListKhachHang(khachHangReq.getTextSearch());
            khachHangRes.setListKhachHang(listXe);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
        return khachHangRes;
    }

    @Override
    public KhachHangRes getListKhachHangByTrangThai(BaseRequestData baseRequestData) throws ApplicationException {
        KhachHangRes khachHangRes = new KhachHangRes();
        KhachHangReq khachHangReq = (KhachHangReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            List<KhachHang> listXe = khachHangRepo.getListKhachHangByTrangThai(khachHangReq.getTextSearch());
            khachHangRes.setListKhachHang(listXe);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
        return khachHangRes;
    }

    @Override
    public boolean suaKhachHang(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        KhachHangReq khachHangReq = (KhachHangReq) baseRequestData.getWsRequest();

        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            KhachHang khachHang = khachHangRepo.findById(khachHangReq.getIdKhachHang());
            if (ObjectUtils.isEmpty(khachHang)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("Khách hàng"));
            }
            if (khachHangReq.getTrangThai().equals("APROVE")){
                khachHang.setMaKhachHang(autoIdService.getAutoId("KH").getValue());
            }
//            khachHang.setHoTen(khachHangReq.getHoTen());
//            khachHang.setDiaChi(khachHangReq.getDiaChi());
//            khachHang.setTinh(khachHangReq.getTinh());
//            khachHang.setHuyen(khachHangReq.getHuyen());
//            khachHang.setXa(khachHangReq.getXa());
//            khachHang.setLoaiDoanhNghiep(khachHangReq.getLoaiDoanhNghiep());
//            khachHang.setSoDienThoai(khachHangReq.getSoDienThoai());
//            khachHang.setEmail(khachHangReq.getEmail());
            khachHang.setTrangThai(khachHangReq.getTrangThai());
//            khachHang.setCccdSau(khachHangReq.getCccdSau());
//            khachHang.setCccdTruoc(khachHangReq.getCccdTruoc());


            khachHangRepo.save(khachHang);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }



    @Override
    public boolean xoaKhachHang(BaseRequestData baseRequestData, int id) throws ApplicationException {
        boolean success = false;
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            KhachHang khachHang = khachHangRepo.findById(id);
            if (ObjectUtils.isEmpty(khachHang)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("Khách hàng"));
            }
            khachHangRepo.deleteById(id);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }


    @Override
    public KhachHangResponse chiTietKhachHang(BaseRequestData baseRequestData) throws ApplicationException {
        KhachHangResponse khachHangRes = new KhachHangResponse();
        KhachHangReq khachHangReq = (KhachHangReq) baseRequestData.getWsRequest();

        try {
//            User user = userRepository.findUserBySessionAngToken(baseRequestData.getToken());
//            if (ObjectUtils.isEmpty(user)) {
//                throw new ApplicationException("ERR_0000003");
//            }

            KhachHang kh = khachHangRepo.findByMaKH(khachHangReq.getTextSearch());
            if (ObjectUtils.isEmpty(kh)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("Khách hàng"));
            }
            khachHangRes.setKhachHang(kh);
        } catch (ApplicationException e) {
            throw e;
        }
        return khachHangRes;
    }

    public KhachHang getByMaKH(String maKH){
        return khachHangRepo.findByMaKH(maKH);
    }
}
