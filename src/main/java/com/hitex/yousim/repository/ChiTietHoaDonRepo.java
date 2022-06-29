package com.hitex.yousim.repository;

import com.hitex.yousim.model.ChiTietHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface ChiTietHoaDonRepo extends JpaRepository<ChiTietHoaDon, Integer> {
    @Query(value = "select n from ChiTietHoaDon n where n.maHoaDon = ?1")
    List<ChiTietHoaDon> findByMaHoaDon(String text);

    @Transactional
    @Modifying
    @Query(value = "delete from ChiTietHoaDon n where n.maHoaDon = ?1")
    void deleteAllByMaHoaDon(String text);
}

