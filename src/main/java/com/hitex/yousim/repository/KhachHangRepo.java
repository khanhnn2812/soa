package com.hitex.yousim.repository;

import com.hitex.yousim.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KhachHangRepo extends JpaRepository<KhachHang, Integer> {
    @Query(value = "select x from KhachHang x where x.maKhachHang = ?1 or x.idKhachHang = ?1")
    KhachHang findByMaKH(String maKhachHang);


    @Query(value = "select x from KhachHang x where x.idKhachHang = ?1")
    KhachHang findById(int idKhachHang);

    @Query(value = "select x from KhachHang x where x.soDienThoai = ?1")
    KhachHang findBySoDienThoai(String soDienThoai);

    @Query(value = "select n from KhachHang n where n.hoTen like %?1% or n.soDienThoai like %?1%")
    List<KhachHang> getListKhachHang(String text);

    @Query(value = "select n from KhachHang n where n.trangThai = ?1")
    List<KhachHang> getListKhachHangByTrangThai(String trangThai);

}

