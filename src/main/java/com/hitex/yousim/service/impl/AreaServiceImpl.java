package com.hitex.yousim.service.impl;


import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.area.AreaReq;

import com.hitex.yousim.dto.response.area.AreaRes;
import com.hitex.yousim.dto.response.area.AreaResponse;
import com.hitex.yousim.model.Area;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.AreaRepo;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.AreaService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AreaRepo areaRepo;

    @Override
    public AreaRes getListArea(BaseRequestData baseRequestData, String id) throws ApplicationException {
        AreaRes areaRes = new AreaRes();
        AreaReq AreaReq = (AreaReq) baseRequestData.getWsRequest();

        try {
            User user = userRepository.findUserBySessionAngToken(baseRequestData.getToken());
            if (ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("ERR_0000003");
            }
            AreaReq areaReq = new AreaReq();
            areaReq.setId(id);
            List<Area> listArea =  areaRepo.getListArea(areaReq.getId());
            if (ObjectUtils.isEmpty(listArea)) {
                throw new ApplicationException("ERR_0000006", MessageUtils.getMessage(" area"));
            }
            areaRes.setListArea(listArea);
        } catch (ApplicationException e) {
            throw e;
        }
        return areaRes;
    }
}
