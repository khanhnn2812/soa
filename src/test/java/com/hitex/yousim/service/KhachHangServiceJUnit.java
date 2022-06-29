package com.hitex.yousim.service;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.hoaDon.HoaDonReq;
import com.hitex.yousim.dto.request.khachHang.KhachHangReq;
import com.hitex.yousim.dto.response.area.AreaRes;
import com.hitex.yousim.dto.response.khachhang.KhachHangRes;
import com.hitex.yousim.dto.response.khachhang.KhachHangResponse;
import com.hitex.yousim.model.Area;
import com.hitex.yousim.model.KhachHang;
import com.hitex.yousim.repository.KhachHangRepo;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.impl.KhachHangServiceImpl;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class KhachHangServiceJUnit {
    @Autowired
    KhachHangServiceImpl khachHangService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    KhachHangRepo khachHangRepo;

    @Test
    public void themKhachHangHoGiaDinh(){
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            khachHangReq.setHoTen("Nguyễn Văn Minh");
            khachHangReq.setDiaChi("94 Trần Phú");
            khachHangReq.setTinh("Thành phố Hà Nội");
            khachHangReq.setHuyen("Quận Hà Đông");
            khachHangReq.setXa("Phường Mỗ Lao");
            khachHangReq.setSoDienThoai("0987654321");
            khachHangReq.setEmail("minhnv@email.com");
            khachHangReq.setCccdTruoc("xxxxxx");
            khachHangReq.setCccdSau("yyyyyy");
            khachHangReq.setHoNgheo(false);
            khachHangReq.setDoanhNghiep(false);
            baseRequestData.setWsRequest(khachHangReq);
            int KH = khachHangService.themKhachHang(baseRequestData);
            assertNotNull(KH);
            assertTrue(KH > 0);
        }
        catch (Exception e){
            Assert.fail("Lỗi không thêm được Khách hàng");
        }
    }

    @Test
    public void themKhachHangHoGiaDinhThieuHoTen() throws Exception {
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            khachHangReq.setHoTen("");
            khachHangReq.setDiaChi("94 Trần Phú");
            khachHangReq.setTinh("Thành phố Hà Nội");
            khachHangReq.setHuyen("Quận Hà Đông");
            khachHangReq.setXa("Phường Mỗ Lao");
            khachHangReq.setSoDienThoai("0987654321");
            khachHangReq.setEmail("minhnv@email.com");
            khachHangReq.setCccdTruoc("xxxxxx");
            khachHangReq.setCccdSau("yyyyyy");
            khachHangReq.setHoNgheo(false);
            khachHangReq.setDoanhNghiep(false);
            baseRequestData.setWsRequest(khachHangReq);
            int KH = khachHangService.themKhachHang(baseRequestData);
            fail("Not throw exception");
        } catch (Exception e) {
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Họ tên không được để trống");
        }
    }

    @Test
    public void themKhachHangHoGiaDinhThieuDiaChi() throws Exception {
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            khachHangReq.setHoTen("Nguyễn Văn Minh");
            khachHangReq.setDiaChi("");
            khachHangReq.setTinh("Thành phố Hà Nội");
            khachHangReq.setHuyen("Quận Hà Đông");
            khachHangReq.setXa("Phường Mỗ Lao");
            khachHangReq.setSoDienThoai("0987654321");
            khachHangReq.setEmail("minhnv@email.com");
            khachHangReq.setCccdTruoc("xxxxxx");
            khachHangReq.setCccdSau("yyyyyy");
            khachHangReq.setHoNgheo(false);
            khachHangReq.setDoanhNghiep(false);
            baseRequestData.setWsRequest(khachHangReq);
            int KH = khachHangService.themKhachHang(baseRequestData);
            fail("Not throw exception");
        } catch (Exception e) {
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Địa chỉ không được để trống");
        }
    }

    @Test
    public void themKhachHangHoGiaDinhThieuDienThoai() throws Exception {
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            khachHangReq.setHoTen("Nguyễn Văn Minh");
            khachHangReq.setDiaChi("94 Trần Phú");
            khachHangReq.setTinh("Thành phố Hà Nội");
            khachHangReq.setHuyen("Quận Hà Đông");
            khachHangReq.setXa("Phường Mỗ Lao");
            khachHangReq.setSoDienThoai("");
            khachHangReq.setEmail("minhnv@email.com");
            khachHangReq.setCccdTruoc("xxxxxx");
            khachHangReq.setCccdSau("yyyyyy");
            khachHangReq.setHoNgheo(false);
            khachHangReq.setDoanhNghiep(false);
            baseRequestData.setWsRequest(khachHangReq);
            int KH = khachHangService.themKhachHang(baseRequestData);
            fail("Not throw exception");
        } catch (Exception e) {
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Số điện thoại không được để trống");
        }
    }

    @Test
    public void themKhachHangHoGiaDinhThieuCCCDTruoc() throws Exception {
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            khachHangReq.setHoTen("Nguyễn Văn Minh");
            khachHangReq.setDiaChi("94 Trần Phú");
            khachHangReq.setTinh("Thành phố Hà Nội");
            khachHangReq.setHuyen("Quận Hà Đông");
            khachHangReq.setXa("Phường Mỗ Lao");
            khachHangReq.setSoDienThoai("0987654321");
            khachHangReq.setEmail("minhnv@email.com");
            khachHangReq.setCccdTruoc("");
            khachHangReq.setCccdSau("yyyyyy");
            khachHangReq.setHoNgheo(false);
            khachHangReq.setDoanhNghiep(false);
            baseRequestData.setWsRequest(khachHangReq);
            int KH = khachHangService.themKhachHang(baseRequestData);
            fail("Not throw exception");
        } catch (Exception e) {
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Căn cước công dân mặt trước không được để trống");
        }
    }

    @Test
    public void themKhachHangHoGiaDinhThieuCCCDSau() throws Exception {
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            khachHangReq.setHoTen("Nguyễn Văn Minh");
            khachHangReq.setDiaChi("94 Trần Phú");
            khachHangReq.setTinh("Thành phố Hà Nội");
            khachHangReq.setHuyen("Quận Hà Đông");
            khachHangReq.setXa("Phường Mỗ Lao");
            khachHangReq.setSoDienThoai("0987654321");
            khachHangReq.setEmail("minhnv@email.com");
            khachHangReq.setCccdTruoc("xxxxxx");
            khachHangReq.setCccdSau("");
            khachHangReq.setHoNgheo(false);
            khachHangReq.setDoanhNghiep(false);
            baseRequestData.setWsRequest(khachHangReq);
            int KH = khachHangService.themKhachHang(baseRequestData);
            fail("Not throw exception");
        } catch (Exception e) {
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Căn cước công dân mặt sau không được để trống");
        }
    }

    @Test
    public void getListKhachHang(){
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            khachHangReq.setTextSearch("a");
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            baseRequestData.setWsRequest(khachHangReq);
            KhachHangRes khachHang = khachHangService.getListKhachHang(baseRequestData);
            List<KhachHang> khachHangList = khachHangRepo.getListKhachHang("a");
            //System.out.println(khachHang.toString());
            assertNotNull(khachHang);
            assertEquals(khachHang.getListKhachHang(), khachHangList);
        }
        catch (Exception e){
            Assert.fail("Lấy không thành công danh sách khách hàng");
        }
    }

    @Test
    public void getListKhachHangWithoutToken() throws Exception {
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            baseRequestData.setToken("");
            khachHangReq.setTextSearch("a");
            baseRequestData.setWsRequest(khachHangReq);
            KhachHangRes khachHang = khachHangService.getListKhachHang(baseRequestData);
            fail("Not throw exception");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Chưa đăng nhập");
        }
    }

    @Test
    public void getListKhachHangByTrangThai(){
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            khachHangReq.setTextSearch(Constant.chapThuan);
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            baseRequestData.setWsRequest(khachHangReq);
            KhachHangRes khachHang = khachHangService.getListKhachHangByTrangThai(baseRequestData);
            List<KhachHang> khachHangList = khachHangRepo.getListKhachHangByTrangThai(Constant.chapThuan);
            //System.out.println(khachHang.toString());
            assertNotNull(khachHang);
            assertEquals(khachHang.getListKhachHang(), khachHangList);
        }
        catch (Exception e){
            Assert.fail("Lấy không thành công danh sách khách hàng");
        }
    }

    @Test
    public void getListKhachHangByTrangThaiWithoutToken() throws Exception {
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            baseRequestData.setToken("");
            khachHangReq.setTextSearch(Constant.chapThuan);
            baseRequestData.setWsRequest(khachHangReq);
            KhachHangRes khachHang = khachHangService.getListKhachHangByTrangThai(baseRequestData);
            fail("Not throw exception");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Chưa đăng nhập");
        }
    }

    @Test
    public void suaKhachHang(){
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            List<KhachHang> khachHangList = khachHangRepo.getListKhachHangByTrangThai(Constant.taoMoi);
            KhachHangReq khachHangReq = new KhachHangReq();
            khachHangReq.setTrangThai(Constant.chapThuan);
            khachHangReq.setIdKhachHang(khachHangList.get(0).getIdKhachHang());
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            baseRequestData.setWsRequest(khachHangReq);
            boolean khachHang = khachHangService.suaKhachHang(baseRequestData);
            //System.out.println(khachHang.toString());
            assertNotNull(khachHang);
            assertTrue(khachHang);
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void suaKhachHangKhongTonTai(){
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            khachHangReq.setTrangThai(Constant.chapThuan);
            khachHangReq.setIdKhachHang(0);
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            baseRequestData.setWsRequest(khachHangReq);
            boolean khachHang = khachHangService.suaKhachHang(baseRequestData);
            fail("Not throw exception");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Khách hàng không tồn tại");
        }
    }

    @Test
    public void suaKhachHangWithoutToken() throws Exception {
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            baseRequestData.setToken("");
            khachHangReq.setTextSearch(Constant.chapThuan);
            baseRequestData.setWsRequest(khachHangReq);
            boolean khachHang = khachHangService.suaKhachHang(baseRequestData);
            fail("Not throw exception");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Chưa đăng nhập");
        }
    }

    @Test
    public void xoaKhachHang(){
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            List<KhachHang> khachHangList = khachHangRepo.getListKhachHangByTrangThai(Constant.chapThuan);
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            baseRequestData.setWsRequest(khachHangReq);
            boolean delKhachHang = khachHangService.xoaKhachHang(baseRequestData, khachHangList.get(0).getIdKhachHang());
            assertNotNull(delKhachHang);
            assertTrue(delKhachHang);
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void xoaKhachHangWithoutToken() throws Exception {
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            List<KhachHang> khachHangList = khachHangRepo.getListKhachHangByTrangThai(Constant.chapThuan);
            baseRequestData.setToken("");
            baseRequestData.setWsRequest(khachHangReq);
            boolean delKhachHang = khachHangService.xoaKhachHang(baseRequestData, khachHangList.get(0).getIdKhachHang());
            fail("Not throw exception");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Chưa đăng nhập");
        }
    }

    @Test
    public void xoaKhachHangKhongTonTai(){
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            baseRequestData.setWsRequest(khachHangReq);
            boolean delKhachHang = khachHangService.xoaKhachHang(baseRequestData, 0);
            fail("Not throw exception");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Khách hàng không tồn tại");
        }
    }

    @Test
    public void chiTietKhachHang(){
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            List<KhachHang> khachHangList = khachHangRepo.getListKhachHangByTrangThai(Constant.chapThuan);
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            khachHangReq.setTextSearch(khachHangList.get(0).getMaKhachHang());
            baseRequestData.setWsRequest(khachHangReq);
            KhachHangResponse khachHang = khachHangService.chiTietKhachHang(baseRequestData);
            assertNotNull(khachHang);
            KhachHang kh = khachHangRepo.findByMaKH(khachHangList.get(0).getMaKhachHang());
            assertEquals(khachHang.getKhachHang(), kh);
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

//    @Test
//    public void chiTietKhachHangWithoutToken() throws Exception {
//        try {
//            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
//            KhachHangReq khachHangReq = new KhachHangReq();
//            List<KhachHang> khachHangList = khachHangRepo.getListKhachHangByTrangThai(Constant.chapThuan);
//            baseRequestData.setToken("");
//            khachHangReq.setTextSearch(khachHangList.get(0).getMaKhachHang());
//            baseRequestData.setWsRequest(khachHangReq);
//            KhachHangResponse khachHang = khachHangService.chiTietKhachHang(baseRequestData);
//            fail("Not throw exception");
//        }
//        catch (Exception e){
//            assertThat(e, instanceOf(ApplicationException.class));
//            assertEquals(e.getMessage(), "Chưa đăng nhập");
//        }
//    }

    @Test
    public void chiTietKhachHangKhongTonTai(){
        try {
            BaseRequestData<KhachHangReq> baseRequestData = new BaseRequestData();
            KhachHangReq khachHangReq = new KhachHangReq();
            List<KhachHang> khachHangList = khachHangRepo.getListKhachHangByTrangThai(Constant.chapThuan);
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            khachHangReq.setTextSearch("KH000000");
            baseRequestData.setWsRequest(khachHangReq);
            KhachHangResponse khachHang = khachHangService.chiTietKhachHang(baseRequestData);
            fail("Not throw exception");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Khách hàng không tồn tại");
        }
    }


    @Test
    public void getByMaKH(){
        try {
            List<KhachHang> khachHangList = khachHangRepo.getListKhachHangByTrangThai(Constant.chapThuan);
            KhachHang khachHang = khachHangService.getByMaKH(khachHangList.get(0).getMaKhachHang());
            KhachHang kh = khachHangRepo.findByMaKH(khachHangList.get(0).getMaKhachHang());
            assertEquals(khachHang, kh);
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
}
