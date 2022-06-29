package com.hitex.yousim.repository;

import com.hitex.yousim.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepo extends JpaRepository<Area, Integer> {

    @Query(value = "select n from Area n where n.parent = ?1")
    List<Area> getListArea(String id);


}

