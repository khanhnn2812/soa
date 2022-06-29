package com.hitex.yousim.service;

import com.hitex.yousim.model.GiaNuoc;
import com.hitex.yousim.utils.exception.ApplicationException;

import java.util.List;

public interface GiaNuocService {

    List<GiaNuoc> getGiaNuoc(boolean isHoNgheo, boolean isDoanhNghiep) throws ApplicationException;

}
