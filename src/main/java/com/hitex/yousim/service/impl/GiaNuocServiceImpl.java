package com.hitex.yousim.service.impl;


import com.hitex.yousim.model.GiaNuoc;
import com.hitex.yousim.repository.GiaNuocRepo;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.GiaNuocService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class GiaNuocServiceImpl implements GiaNuocService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GiaNuocRepo giaNuocRepo;


    @Override
    public List<GiaNuoc> getGiaNuoc(boolean isHoNgheo, boolean isDoanhNghiep) throws ApplicationException {
        List<GiaNuoc> listGiaNuoc;
        try {

            listGiaNuoc = giaNuocRepo.getAll(isHoNgheo, isDoanhNghiep);

        } catch (Exception e) {
            throw e;
        }
        return listGiaNuoc;
    }
}
