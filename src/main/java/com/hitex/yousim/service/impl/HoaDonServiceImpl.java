package com.hitex.yousim.service.impl;


import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.hoaDon.HoaDonReq;
import com.hitex.yousim.dto.response.hoadon.HoaDonRes;
import com.hitex.yousim.dto.response.hoadon.HoaDonResponse;
import com.hitex.yousim.model.*;
import com.hitex.yousim.repository.*;
import com.hitex.yousim.service.AutoIdService;
import com.hitex.yousim.service.HoaDonService;
import com.hitex.yousim.service.KhachHangService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    HoaDonRepo hoaDonRepo;

    @Autowired
    ChiTietHoaDonRepo chiTietHoaDonRepo;

    @Autowired
    GiaNuocRepo giaNuocRepo;

    @Autowired
    KhachHangRepo khachHangRepo;

    @Autowired
    KhachHangService khachHangService;

    @Autowired
    AutoIdService autoIdService;


    @Override
    public boolean themHoaDon(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        HoaDonReq hoaDonReq = (HoaDonReq) baseRequestData.getWsRequest();
        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(autoIdService.getAutoId("HD").getValue());
            hd.setMaKhachHang(hoaDonReq.getMaKhachHang());
            hd.setChiSoCuoi(hoaDonReq.getChiSoCuoi());
            List<HoaDon> listHD = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang());

            double soNuocOld = 0;
            if (listHD.size() > 0) soNuocOld = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi();
            if (soNuocOld > hoaDonReq.getChiSoCuoi()) {
                throw new ApplicationException("ERR_0000013", String.valueOf(soNuocOld));
            }
            double soNuoc = hoaDonReq.getChiSoCuoi() - soNuocOld;
            KhachHang kh = khachHangService.getByMaKH(hoaDonReq.getMaKhachHang());
            hd.setSoNuocDaDung(soNuoc);
            ChiTietHoaDon chiTietHoaDon;

            if (kh.isDoanhNghiep() == false) {
                List<GiaNuoc> listGiaNuoc = giaNuocRepo.getAll(kh.isHoNgheo(), kh.isDoanhNghiep());

                GiaNuoc giaNuocBac1 = listGiaNuoc.stream().filter(x -> x.getBac() == 1).findFirst().orElse(null);
                GiaNuoc giaNuocBac2 = listGiaNuoc.stream().filter(x -> x.getBac() == 2).findFirst().orElse(null);
                GiaNuoc giaNuocBac3 = listGiaNuoc.stream().filter(x -> x.getBac() == 3).findFirst().orElse(null);
                GiaNuoc giaNuocBac4 = listGiaNuoc.stream().filter(x -> x.getBac() == 4).findFirst().orElse(null);

                if (soNuoc <= 10) {
                    chiTietHoaDon = new ChiTietHoaDon();
                    hd.setTongTien((float) (giaNuocBac1.getGiaNuoc() * soNuoc));
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(1);
                    chiTietHoaDon.setSoTien(giaNuocBac1.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(soNuoc);
                    chiTietHoaDon.setThanhTien(giaNuocBac1.getGiaNuoc() * soNuoc);
                    chiTietHoaDonRepo.save(chiTietHoaDon);




                } else if (soNuoc <= 20) {
                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(1);
                    chiTietHoaDon.setSoTien(giaNuocBac1.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(10);
                    chiTietHoaDon.setThanhTien(giaNuocBac1.getGiaNuoc() * 10);
                    chiTietHoaDonRepo.save(chiTietHoaDon);

                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    hd.setTongTien((float) (giaNuocBac1.getGiaNuoc() * 10 + giaNuocBac2.getGiaNuoc() * (soNuoc - 10)));
                    chiTietHoaDon.setBacNuoc(2);
                    chiTietHoaDon.setSoTien(giaNuocBac2.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(soNuoc - 10);
                    chiTietHoaDon.setThanhTien(giaNuocBac2.getGiaNuoc() * (soNuoc - 10));
                    chiTietHoaDonRepo.save(chiTietHoaDon);

                } else if (soNuoc <= 30) {
                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(1);
                    chiTietHoaDon.setSoTien(giaNuocBac1.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(10);
                    chiTietHoaDon.setThanhTien(giaNuocBac1.getGiaNuoc() * 10);
                    chiTietHoaDonRepo.save(chiTietHoaDon);

                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(2);
                    chiTietHoaDon.setSoTien(giaNuocBac2.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(10);
                    chiTietHoaDon.setThanhTien(giaNuocBac2.getGiaNuoc() * 10);
                    chiTietHoaDonRepo.save(chiTietHoaDon);

                    hd.setTongTien((float) (giaNuocBac1.getGiaNuoc() * 10 + giaNuocBac2.getGiaNuoc() * 10 + giaNuocBac3.getGiaNuoc() * (soNuoc - 20)));

                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(3);
                    chiTietHoaDon.setSoTien(giaNuocBac3.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(soNuoc - 20);
                    chiTietHoaDon.setThanhTien(giaNuocBac3.getGiaNuoc() * (soNuoc - 20));
                    chiTietHoaDonRepo.save(chiTietHoaDon);
                } else if (soNuoc > 30) {
                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(1);
                    chiTietHoaDon.setSoTien(giaNuocBac1.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(10);
                    chiTietHoaDon.setThanhTien(giaNuocBac1.getGiaNuoc() * 10);
                    chiTietHoaDonRepo.save(chiTietHoaDon);

                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(2);
                    chiTietHoaDon.setSoTien(giaNuocBac2.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(10);
                    chiTietHoaDon.setThanhTien(giaNuocBac2.getGiaNuoc() * 10);
                    chiTietHoaDonRepo.save(chiTietHoaDon);

                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(3);
                    chiTietHoaDon.setSoTien(giaNuocBac3.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(10);
                    chiTietHoaDon.setThanhTien(giaNuocBac3.getGiaNuoc() * 10);
                    chiTietHoaDonRepo.save(chiTietHoaDon);

                    hd.setTongTien((float) (giaNuocBac1.getGiaNuoc() * 10 + giaNuocBac2.getGiaNuoc() * 10 + giaNuocBac3.getGiaNuoc() * 10 + giaNuocBac4.getGiaNuoc() * (soNuoc - 30)));
                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(4);
                    chiTietHoaDon.setSoTien(giaNuocBac4.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(soNuoc - 30);
                    chiTietHoaDon.setThanhTien(giaNuocBac4.getGiaNuoc() * (soNuoc - 30));
                    chiTietHoaDonRepo.save(chiTietHoaDon);
                }
            } else {
                List<GiaNuoc> listGiaNuoc = giaNuocRepo.getAll(kh.isHoNgheo(), kh.isDoanhNghiep());

                GiaNuoc giaNuocLoai1 = listGiaNuoc.stream().filter(x -> x.getDescription().equals("1")).findFirst().orElse(null);
                GiaNuoc giaNuocLoai2 = listGiaNuoc.stream().filter(x -> x.getDescription().equals("2")).findFirst().orElse(null);
                GiaNuoc giaNuocLoai3 = listGiaNuoc.stream().filter(x -> x.getDescription().equals("3")).findFirst().orElse(null);
                GiaNuoc giaNuocLoai4 = listGiaNuoc.stream().filter(x -> x.getDescription().equals("4")).findFirst().orElse(null);

                if (kh.getLoaiDoanhNghiep().equals("1")) {
                    hd.setTongTien((float) (giaNuocLoai1.getGiaNuoc() * soNuoc));
                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(1);
                    chiTietHoaDon.setSoTien(giaNuocLoai1.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(soNuoc);
                    chiTietHoaDon.setThanhTien(giaNuocLoai1.getGiaNuoc() * soNuoc);
                    chiTietHoaDonRepo.save(chiTietHoaDon);
                } else if (kh.getLoaiDoanhNghiep().equals("2")) {
                    hd.setTongTien((float) (giaNuocLoai2.getGiaNuoc() * soNuoc));
                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(2);
                    chiTietHoaDon.setSoTien(giaNuocLoai2.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(soNuoc);
                    chiTietHoaDon.setThanhTien(giaNuocLoai2.getGiaNuoc() * soNuoc);
                    chiTietHoaDonRepo.save(chiTietHoaDon);
                } else if (kh.getLoaiDoanhNghiep().equals("3")) {
                    hd.setTongTien((float) (giaNuocLoai3.getGiaNuoc() * soNuoc));
                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(3);
                    chiTietHoaDon.setSoTien(giaNuocLoai3.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(soNuoc);
                    chiTietHoaDon.setThanhTien(giaNuocLoai3.getGiaNuoc() * soNuoc);
                    chiTietHoaDonRepo.save(chiTietHoaDon);
                } else if (kh.getLoaiDoanhNghiep().equals("4")) {
                    hd.setTongTien((float) (giaNuocLoai4.getGiaNuoc() * soNuoc));
                    chiTietHoaDon = new ChiTietHoaDon();
                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
                    chiTietHoaDon.setBacNuoc(4);
                    chiTietHoaDon.setSoTien(giaNuocLoai4.getGiaNuoc());
                    chiTietHoaDon.setSoNuoc(soNuoc);
                    chiTietHoaDon.setThanhTien(giaNuocLoai4.getGiaNuoc() * soNuoc);
                    chiTietHoaDonRepo.save(chiTietHoaDon);
                }
            }
            hd.setDaThanhToan(false);
            hd.setThoiGianTao(LocalDateTime.now());
            hd.setNgayThanhToan(null);

            khachHangRepo.save(kh);

            if( hd.getTongTien() == 0.0) {
                hd.setDaThanhToan(true);
            }

            hoaDonRepo.save(hd);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }


//    @Override
//    public boolean suaHoaDon(BaseRequestData baseRequestData) throws ApplicationException {
//        boolean success = false;
//        HoaDonReq hoaDonReq = (HoaDonReq) baseRequestData.getWsRequest();
//        try {
//            User user = userRepository.findUserBySessionAngToken(baseRequestData.getToken());
//            if (ObjectUtils.isEmpty(user)) {
//                throw new ApplicationException("ERR_0000003");
//            }
//            HoaDon hd = hoaDonRepo.findByMaHD(hoaDonReq.getMaHoaDon());
//            if (ObjectUtils.isEmpty(hd)) {
//                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("hoa don"));
//            }
//
//
//            hd.setChiSoCuoi(hoaDonReq.getChiSoCuoi());
//            hd.setMaKhachHang(hoaDonReq.getMaKhachHang());
////            hoaDon.setSoNuocDaDung(hoaDonReq.getSoNuocDaDung());
////            hoaDon.setTongTien(hoaDonReq.getTongTien());
//            List<HoaDon> listHD = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang());
//            double soNuocOld = 0;
//            if (listHD.size() > 0) soNuocOld = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(1).getChiSoCuoi();
//            if (soNuocOld > hoaDonReq.getChiSoCuoi()) {
//                throw new ApplicationException("ERR_0000013", String.valueOf(soNuocOld));
//            }
//            double soNuoc = hoaDonReq.getChiSoCuoi() - soNuocOld;
//
//            KhachHang kh = khachHangService.getByMaKH(hoaDonReq.getMaKhachHang());
//            hd.setSoNuocDaDung(soNuoc);
//            chiTietHoaDonRepo.deleteAllByMaHoaDon(hoaDonReq.getMaHoaDon());
//            ChiTietHoaDon chiTietHoaDon;
//            if (kh.isDoanhNghiep() == false) {
//                List<GiaNuoc> listGiaNuoc = giaNuocRepo.getAll(kh.isHoNgheo(), kh.isDoanhNghiep());
//
//                GiaNuoc giaNuocBac1 = listGiaNuoc.stream().filter(x -> x.getBac() == 1).findFirst().orElse(null);
//                GiaNuoc giaNuocBac2 = listGiaNuoc.stream().filter(x -> x.getBac() == 2).findFirst().orElse(null);
//                GiaNuoc giaNuocBac3 = listGiaNuoc.stream().filter(x -> x.getBac() == 3).findFirst().orElse(null);
//                GiaNuoc giaNuocBac4 = listGiaNuoc.stream().filter(x -> x.getBac() == 4).findFirst().orElse(null);
//
//                if (soNuoc <= 10) {
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    hd.setTongTien((float) (giaNuocBac1.getGiaNuoc() * soNuoc));
//                    chiTietHoaDon.setBacNuoc(1);
//                    chiTietHoaDon.setSoTien(giaNuocBac1.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(soNuoc);
//                    chiTietHoaDon.setThanhTien(giaNuocBac1.getGiaNuoc() * soNuoc);
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//
//
//                } else if (soNuoc <= 20) {
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    chiTietHoaDon.setBacNuoc(1);
//                    chiTietHoaDon.setSoTien(giaNuocBac1.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(10);
//                    chiTietHoaDon.setThanhTien(giaNuocBac1.getGiaNuoc() * 10);
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    hd.setTongTien((float) (giaNuocBac1.getGiaNuoc() * 10 + giaNuocBac2.getGiaNuoc() * (soNuoc - 10)));
//                    chiTietHoaDon.setBacNuoc(2);
//                    chiTietHoaDon.setSoTien(giaNuocBac2.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(soNuoc - 10);
//                    chiTietHoaDon.setThanhTien(giaNuocBac2.getGiaNuoc() * (soNuoc - 10));
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//
//                } else if (soNuoc <= 30) {
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    chiTietHoaDon.setBacNuoc(1);
//                    chiTietHoaDon.setSoTien(giaNuocBac1.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(10);
//                    chiTietHoaDon.setThanhTien(giaNuocBac1.getGiaNuoc() * 10);
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    chiTietHoaDon.setBacNuoc(2);
//                    chiTietHoaDon.setSoTien(giaNuocBac2.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(10);
//                    chiTietHoaDon.setThanhTien(giaNuocBac2.getGiaNuoc() * 10);
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//
//                    hd.setTongTien((float) (giaNuocBac1.getGiaNuoc() * 10 + giaNuocBac2.getGiaNuoc() * 10 + giaNuocBac3.getGiaNuoc() * (soNuoc - 20)));
//
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    chiTietHoaDon.setBacNuoc(3);
//                    chiTietHoaDon.setSoTien(giaNuocBac3.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(soNuoc - 20);
//                    chiTietHoaDon.setThanhTien(giaNuocBac3.getGiaNuoc() * (soNuoc - 20));
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//                } else if (soNuoc > 40) {
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    chiTietHoaDon.setBacNuoc(1);
//                    chiTietHoaDon.setSoTien(giaNuocBac1.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(10);
//                    chiTietHoaDon.setThanhTien(giaNuocBac1.getGiaNuoc() * 10);
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    chiTietHoaDon.setBacNuoc(2);
//                    chiTietHoaDon.setSoTien(giaNuocBac2.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(10);
//                    chiTietHoaDon.setThanhTien(giaNuocBac2.getGiaNuoc() * 10);
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    chiTietHoaDon.setBacNuoc(3);
//                    chiTietHoaDon.setSoTien(giaNuocBac3.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(10);
//                    chiTietHoaDon.setThanhTien(giaNuocBac3.getGiaNuoc() * 10);
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//
//                    hd.setTongTien((float) (giaNuocBac1.getGiaNuoc() * 10 + giaNuocBac2.getGiaNuoc() * 10 + giaNuocBac3.getGiaNuoc() * 10 + giaNuocBac4.getGiaNuoc() * (soNuoc - 30)));
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    chiTietHoaDon.setBacNuoc(4);
//                    chiTietHoaDon.setSoTien(giaNuocBac4.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(soNuoc - 30);
//                    chiTietHoaDon.setThanhTien(giaNuocBac4.getGiaNuoc() * (soNuoc - 30));
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//                }
//            } else {
//                List<GiaNuoc> listGiaNuoc = giaNuocRepo.getAll(kh.isHoNgheo(), kh.isDoanhNghiep());
//
//                GiaNuoc giaNuocLoai1 = listGiaNuoc.stream().filter(x -> x.getDescription().equals("1")).findFirst().orElse(null);
//                GiaNuoc giaNuocLoai2 = listGiaNuoc.stream().filter(x -> x.getDescription().equals("2")).findFirst().orElse(null);
//                GiaNuoc giaNuocLoai3 = listGiaNuoc.stream().filter(x -> x.getDescription().equals("3")).findFirst().orElse(null);
//                GiaNuoc giaNuocLoai4 = listGiaNuoc.stream().filter(x -> x.getDescription().equals("4")).findFirst().orElse(null);
//
//                if (kh.getLoaiDoanhNghiep().equals("1")) {
//                    hd.setTongTien((float) (giaNuocLoai1.getGiaNuoc() * soNuoc));
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    chiTietHoaDon.setBacNuoc(1);
//                    chiTietHoaDon.setSoTien(giaNuocLoai1.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(soNuoc);
//                    chiTietHoaDon.setThanhTien(giaNuocLoai1.getGiaNuoc() * soNuoc);
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//                } else if (kh.getLoaiDoanhNghiep().equals("2")) {
//                    hd.setTongTien((float) (giaNuocLoai2.getGiaNuoc() * soNuoc));
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    chiTietHoaDon.setBacNuoc(2);
//                    chiTietHoaDon.setSoTien(giaNuocLoai2.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(soNuoc);
//                    chiTietHoaDon.setThanhTien(giaNuocLoai2.getGiaNuoc() * soNuoc);
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//                } else if (kh.getLoaiDoanhNghiep().equals("3")) {
//                    hd.setTongTien((float) (giaNuocLoai3.getGiaNuoc() * soNuoc));
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    chiTietHoaDon.setBacNuoc(3);
//                    chiTietHoaDon.setSoTien(giaNuocLoai3.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(soNuoc);
//                    chiTietHoaDon.setThanhTien(giaNuocLoai3.getGiaNuoc() * soNuoc);
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//                } else if (kh.getLoaiDoanhNghiep().equals("4")) {
//                    hd.setTongTien((float) (giaNuocLoai4.getGiaNuoc() * soNuoc));
//                    chiTietHoaDon = new ChiTietHoaDon();
//                    chiTietHoaDon.setMaHoaDon(hd.getMaHoaDon());
//                    chiTietHoaDon.setBacNuoc(4);
//                    chiTietHoaDon.setSoTien(giaNuocLoai4.getGiaNuoc());
//                    chiTietHoaDon.setSoNuoc(soNuoc);
//                    chiTietHoaDon.setThanhTien(giaNuocLoai4.getGiaNuoc() * soNuoc);
//                    chiTietHoaDonRepo.save(chiTietHoaDon);
//                }
//            }
//
//            float x = hd.getTongTien();
//
//            if( hd.getTongTien() == 0.0) {
//                hd.setDaThanhToan(true);
//            }
//
//            hoaDonRepo.save(hd);
//            success = true;
//        } catch (ApplicationException e) {
//            throw e;
//        }
//        return success;
//    }


    @Override
    public HoaDonResponse chiTietHoaDon(BaseRequestData baseRequestData) throws ApplicationException {
        HoaDonResponse hoaDonRes = new HoaDonResponse();
        HoaDonReq hoaDonReq = (HoaDonReq) baseRequestData.getWsRequest();

        try {

            HoaDon hd = hoaDonRepo.findByMaHD(hoaDonReq.getTextSearch());
            if (ObjectUtils.isEmpty(hd)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("hoa don"));
            }
            KhachHang kh = khachHangRepo.findByMaKH(hd.getMaKhachHang());
            List<ChiTietHoaDon> listChiTietHoaDon= chiTietHoaDonRepo.findByMaHoaDon(hoaDonReq.getTextSearch());

            hoaDonRes.setHoaDon(hd);
            hoaDonRes.setKhachHang(kh);
            hoaDonRes.setListChiTietHoaDon(listChiTietHoaDon);
        } catch (ApplicationException e) {
            throw e;
        }
        return hoaDonRes;
    }

    @Override
    public HoaDonRes getHoaDonChuaTTByMaKH(BaseRequestData baseRequestData) throws ApplicationException {
        HoaDonRes hoaDonRes = new HoaDonRes();
        HoaDonReq hoaDonReq = (HoaDonReq) baseRequestData.getWsRequest();

        try {
//            User user = userRepository.findUserBySessionAngToken(baseRequestData.getToken());
//            if (ObjectUtils.isEmpty(user)) {
//                throw new ApplicationException("ERR_0000003");
//            }
            List<HoaDon> xe = hoaDonRepo.chuaTTByMaKH(hoaDonReq.getTextSearch());
            if (ObjectUtils.isEmpty(xe)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("hoa don"));
            }
            hoaDonRes.setListHoaDon(xe);
        } catch (ApplicationException e) {
            throw e;
        }
        return hoaDonRes;
    }

    @Override
    public boolean thanhToanHoaDon(BaseRequestData baseRequestData) throws ApplicationException {
        boolean success = false;
        HoaDonReq hoaDonReq = (HoaDonReq) baseRequestData.getWsRequest();
        try {

            HoaDon hd = hoaDonRepo.findByMaHD(hoaDonReq.getMaHoaDon());
            if (ObjectUtils.isEmpty(hd)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage("hoa don"));
            }
            hd.setDaThanhToan(hoaDonReq.isDaThanhToan());
            hd.setNgayThanhToan(hoaDonReq.getNgayThanhToan());


            hoaDonRepo.save(hd);
            success = true;
        } catch (ApplicationException e) {
            throw e;
        }
        return success;
    }
}
