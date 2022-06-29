package com.hitex.yousim.repository;

import com.hitex.yousim.model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HoaDonRepo extends JpaRepository<HoaDon, Integer> {
    @Query(value = "select n from HoaDon n where n.maHoaDon = ?1")
    HoaDon findByMaHD(String text);

    @Query(value = "select n from HoaDon n where n.maKhachHang = ?1 order by n.thoiGianTao desc")
    List<HoaDon> chuaTTByMaKH(String text);

    @Query(value = "select n from HoaDon n where n.maKhachHang = ?1 order by n.thoiGianTao desc")
    List<HoaDon> findByMaKH(String maKhachHang);

}

