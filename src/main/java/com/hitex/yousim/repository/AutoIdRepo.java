package com.hitex.yousim.repository;

import com.hitex.yousim.model.AutoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoIdRepo extends JpaRepository<AutoId, Integer> {
    @Query(value = "select x from AutoId x where x.code = ?1")
    AutoId findByCode(String code);

}

