package com.hitex.yousim.repository;

import com.hitex.yousim.model.AutoId;
import com.hitex.yousim.model.GiaNuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiaNuocRepo extends JpaRepository<AutoId, Integer> {
    @Query(value = "select x from GiaNuoc x where x.hoNgheo = ?1 and x.doanhNghiep = ?2")
    List<GiaNuoc> getAll(boolean isHoNgheo, boolean isDoanhNghiep);

}

