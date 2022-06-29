package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.hoaDon.HoaDonReq;
import com.hitex.yousim.dto.response.hoadon.HoaDonRes;
import com.hitex.yousim.dto.response.hoadon.HoaDonResponse;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.HoaDonRepo;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.impl.HoaDonServiceImpl;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class HoaDonServiceJUnit extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    HoaDonServiceImpl hoaDonService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HoaDonRepo hoaDonRepo;

    @Test
    public void LayDuocChiTietHoaDon(){

        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setTextSearch("HD000031");
            baseRequestData.setWsRequest(hoaDonReq);
            HoaDonResponse HD = hoaDonService.chiTietHoaDon(baseRequestData);
            assertNotNull(HD);
            assertEquals(HD.getHoaDon().getMaHoaDon(), "HD000031");
        }
        catch (Exception e){
            Assert.fail("Lỗi không lấy được HĐ");
        }
    }

    @Test
    public void ChiTietHoaDonKoTonTai(){

        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setTextSearch("HD000");
            baseRequestData.setWsRequest(hoaDonReq);
            HoaDonResponse HD = hoaDonService.chiTietHoaDon(baseRequestData);
            fail("Not throw exception");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "hoa don không tồn tại");
        }
    }

    @Test
    public void LayHDTheoMaKH() {
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setTextSearch("KH000002");
            baseRequestData.setWsRequest(hoaDonReq);
            HoaDonRes HD = hoaDonService.getHoaDonChuaTTByMaKH(baseRequestData);
            assertNotNull(HD);
            assertEquals(HD.getListHoaDon().size(), 9);
            assertEquals(HD.getListHoaDon().get(0).getMaKhachHang(),"KH000002" );
        }
        catch (Exception e){
            Assert.fail("Lỗi không lấy list HD");
        }
    }

    @Test
    public void LayHDTheoMaKHKoTonTai() {
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setTextSearch("KH000");
            baseRequestData.setWsRequest(hoaDonReq);
            HoaDonRes HD = hoaDonService.getHoaDonChuaTTByMaKH(baseRequestData);
            fail("Not throw exception");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "hoa don không tồn tại");
        }
    }

    @Test
    public void ThemHoaDonKoToken(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("");

            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000009");
            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi()+9);
            baseRequestData.setWsRequest(hoaDonReq);
            Boolean done = hoaDonService.themHoaDon(baseRequestData);
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Chưa đăng nhập");
        }
    }

    @Test
    public void ThemHoaDonChiSoNuocThapHon(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");

            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000009");
            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi()-2);
            baseRequestData.setWsRequest(hoaDonReq);
            Boolean done = hoaDonService.themHoaDon(baseRequestData);
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertThat(e.getMessage(), containsString("Chỉ số mới không được nhỏ hơn chỉ số cũ"));

        }
    }

    @Test
    public void ThemHoaDonChoHoNgheoDuoi10(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000009");
            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi()+9);
            baseRequestData.setWsRequest(hoaDonReq);

            Boolean done = hoaDonService.themHoaDon(baseRequestData);
            assertEquals(done, true);
            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(32400).floatValue();
            assertEquals(tien, dungTien);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được HĐ");
        }
    }

    @Test
    public void ThemHoaDonChoHoNgheoDuoi20(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000009");
            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi()+19);
            baseRequestData.setWsRequest(hoaDonReq);

            Boolean done = hoaDonService.themHoaDon(baseRequestData);
            assertEquals(done, true);
            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(76500).floatValue();
            assertEquals(tien, dungTien);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được HĐ");
        }
    }

    @Test
    public void ThemHoaDonChoHoNgheoDuoi30(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000009");
            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi()+29);
            baseRequestData.setWsRequest(hoaDonReq);

            Boolean done = hoaDonService.themHoaDon(baseRequestData);
            assertEquals(done, true);
            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(131400).floatValue();
            assertEquals(tien, dungTien);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được HĐ");
        }
    }

    @Test
    public void ThemHoaDonChoHoNgheoTren30(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000009");
            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi()+39);
            baseRequestData.setWsRequest(hoaDonReq);
            Boolean done = hoaDonService.themHoaDon(baseRequestData);
            assertEquals(done, true);
            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(197300).floatValue();
            assertEquals(tien, dungTien);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được HĐ");
        }
    }

    @Test
    public void ThemHoaDonChoBinhThuongDuoi10(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000006");
            double soNuoc = 0;
            if (hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).size() > 0) soNuoc = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi();
            hoaDonReq.setChiSoCuoi(soNuoc+9);
            baseRequestData.setWsRequest(hoaDonReq);

            Boolean done = hoaDonService.themHoaDon(baseRequestData);
            assertEquals(done, true);
            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(53757).floatValue();
            assertEquals(tien, dungTien);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được HĐ");
        }
    }

    @Test
    public void ThemHoaDonChoBinhThuongDuoi2(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000006");
            double soNuoc = 0;
            if (hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).size() > 0) soNuoc = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi();
            hoaDonReq.setChiSoCuoi(soNuoc+19);
            baseRequestData.setWsRequest(hoaDonReq);

            Boolean done = hoaDonService.themHoaDon(baseRequestData);
            assertEquals(done, true);
            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(123198).floatValue();
            assertEquals(tien, dungTien);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được HĐ");
        }
    }

    @Test
    public void ThemHoaDonChoBinhThuongDuoi30(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000006");
            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi()+29);
            baseRequestData.setWsRequest(hoaDonReq);

            Boolean done = hoaDonService.themHoaDon(baseRequestData);
            assertEquals(done, true);
            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(208271).floatValue();
            assertEquals(tien, dungTien);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được HĐ");
        }
    }

    @Test
    public void ThemHoaDonChoBinhThuongTren30(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000006");
            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi()+39);
            baseRequestData.setWsRequest(hoaDonReq);
            Boolean done = hoaDonService.themHoaDon(baseRequestData);
            assertEquals(done, true);
            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(360301).floatValue();
            assertEquals(tien, dungTien);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được HĐ");
        }
    }

    @Test
    public void ThemHoaDonChoDoanhNghiepLoai1(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000012");
            double soNuoc = 0;
            if (hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).size() > 0) soNuoc = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi();
            hoaDonReq.setChiSoCuoi(soNuoc+9);
            baseRequestData.setWsRequest(hoaDonReq);

            Boolean done = hoaDonService.themHoaDon(baseRequestData);
            assertEquals(done, true);
            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(89595).floatValue();
            assertEquals(tien, dungTien);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được HĐ");
        }
    }

    @Test
    public void ThemHoaDonChoDoanhNghiepLoai2(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000005");
            double soNuoc = 0;
            if (hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).size() > 0) soNuoc = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi();
            hoaDonReq.setChiSoCuoi(soNuoc+19);
            baseRequestData.setWsRequest(hoaDonReq);

            Boolean done = hoaDonService.themHoaDon(baseRequestData);
            assertEquals(done, true);
            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(220685).floatValue();
            assertEquals(tien, dungTien);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được HĐ");
        }
    }

    @Test
    public void ThemHoaDonChoDoanhNghiepDuoiLoai3(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000008");
            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi()+29);
            baseRequestData.setWsRequest(hoaDonReq);

            Boolean done = hoaDonService.themHoaDon(baseRequestData);
            assertEquals(done, true);
            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(288695).floatValue();
            assertEquals(tien, dungTien);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được HĐ");
        }
    }

    @Test
    public void ThemHoaDonChoDoanhNghiepLoai4(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaKhachHang("KH000004");
            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getChiSoCuoi()+39);
            baseRequestData.setWsRequest(hoaDonReq);
            Boolean done = hoaDonService.themHoaDon(baseRequestData);
            assertEquals(done, true);
            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(860652).floatValue();
            assertEquals(tien, dungTien);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được HĐ");
        }
    }

//    @Test
//    public void SuaHoaDonChoNguoiDanDuoi10(){
//        try {
//            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
//            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
//
//            HoaDonReq hoaDonReq = new HoaDonReq();
//            hoaDonReq.setMaKhachHang("KH000009");
//            hoaDonReq.setMaHoaDon("HD000062");
//            Integer index = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).indexOf(hoaDonRepo.findByMaHD(hoaDonReq.getMaHoaDon())) + 1 ;
//            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(index).getChiSoCuoi()+9);
//            baseRequestData.setWsRequest(hoaDonReq);
//
//            Boolean done = hoaDonService.suaHoaDon(baseRequestData);
//            assertEquals(done, true);
//            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(32400).floatValue();
//            assertEquals(tien, dungTien);
//        }
//        catch (Exception e){
//            Assert.fail("Lỗi không sửa đc HĐ đã có do láy chỉ số nước đã có mới nhất");
//        }
//    }
//
//    @Test
//    public void SuaHoaDonChoNguoiDanDuoi20(){
//        try {
//            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
//            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
//
//            HoaDonReq hoaDonReq = new HoaDonReq();
//            hoaDonReq.setMaKhachHang("KH000009");
//            hoaDonReq.setMaHoaDon("HD000062");
//            Integer index = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).indexOf(hoaDonRepo.findByMaHD(hoaDonReq.getMaHoaDon())) + 1 ;
//            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(index).getChiSoCuoi()+19);
//            baseRequestData.setWsRequest(hoaDonReq);
//
//            Boolean done = hoaDonService.themHoaDon(baseRequestData);
//            assertEquals(done, true);
//            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(76500).floatValue();
//            assertEquals(tien, dungTien);
//        }
//        catch (Exception e){
//            Assert.fail("Lỗi không sửa đc HĐ đã có do láy chỉ số nước đã có mới nhất");
//        }
//    }
//
//    @Test
//    public void SuaHoaDonChoNguoiDanDuoi30(){
//        try {
//            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
//            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
//
//            HoaDonReq hoaDonReq = new HoaDonReq();
//            hoaDonReq.setMaKhachHang("KH000009");
//            hoaDonReq.setMaHoaDon("HD000062");
//            Integer index = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).indexOf(hoaDonRepo.findByMaHD(hoaDonReq.getMaHoaDon())) + 1 ;
//            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(index).getChiSoCuoi()+29);
//            baseRequestData.setWsRequest(hoaDonReq);
//
//            Boolean done = hoaDonService.themHoaDon(baseRequestData);
//            assertEquals(done, true);
//            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(131400).floatValue();
//            assertEquals(tien, dungTien);
//        }
//        catch (Exception e){
//            Assert.fail("Lỗi không sửa đc HĐ đã có do láy chỉ số nước đã có mới nhất");
//        }
//    }
//
//    @Test
//    public void SuaHoaDonChoNguoiDanTren30(){
//        try {
//            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
//            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
//
//            HoaDonReq hoaDonReq = new HoaDonReq();
//            hoaDonReq.setMaKhachHang("KH000009");
//            hoaDonReq.setMaHoaDon("HD000062");
//            Integer index = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).indexOf(hoaDonRepo.findByMaHD(hoaDonReq.getMaHoaDon())) + 1 ;
//            hoaDonReq.setChiSoCuoi(hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(index).getChiSoCuoi()+39);
//            baseRequestData.setWsRequest(hoaDonReq);
//            Boolean done = hoaDonService.themHoaDon(baseRequestData);
//            assertEquals(done, true);
//            Float tien = hoaDonRepo.findByMaKH(hoaDonReq.getMaKhachHang()).get(0).getTongTien(), dungTien = new Integer(197300).floatValue();
//            assertEquals(tien, dungTien);
//        }
//        catch (Exception e){
//            Assert.fail("Lỗi không sửa đc HĐ đã có do láy chỉ số nước đã có mới nhất");
//        }
//    }

    @Test
    public void ThanhToanHoaDon(){
        try{
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaHoaDon("HD000070");
            hoaDonReq.setDaThanhToan(true);
            hoaDonReq.setNgayThanhToan(LocalDateTime.now());
            baseRequestData.setWsRequest(hoaDonReq);
            Boolean done = hoaDonService.thanhToanHoaDon(baseRequestData);
            assertEquals(done, true);
            assertEquals(hoaDonRepo.findByMaHD("HD000070").isDaThanhToan(), true);
        } catch (Exception e){
            Assert.fail("Thanh Toán ko thành công");
        }
    }

    @Test
    public void ThanhToanHoaDonKoTonTai(){
        try{
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            HoaDonReq hoaDonReq = new HoaDonReq();
            hoaDonReq.setMaHoaDon("HD000");
            hoaDonReq.setDaThanhToan(true);
            hoaDonReq.setNgayThanhToan(LocalDateTime.now());
            baseRequestData.setWsRequest(hoaDonReq);
            Boolean done = hoaDonService.thanhToanHoaDon(baseRequestData);
        } catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "hoa don không tồn tại");
        }
    }

}
