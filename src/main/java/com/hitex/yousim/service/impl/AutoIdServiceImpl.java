package com.hitex.yousim.service.impl;


import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.autoid.AutoIdResponse;
import com.hitex.yousim.model.AutoId;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.AutoIdRepo;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.AutoIdService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class AutoIdServiceImpl implements AutoIdService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AutoIdRepo autoIdRepo;


    @Override
    public AutoId getAutoId( String code) throws ApplicationException {
        try {
            AutoId autoId = autoIdRepo.findByCode(code);
            if (ObjectUtils.isEmpty(autoId)) {
               autoId = new AutoId();
                autoId.setCode(code);
                autoId.setIndex(2);
                autoId.setValue(code+"000001");
            }
            else{
                String value = code;
                for (int i = 0; i < 6-String.valueOf(autoId.getIndex()).length(); i++) {
                    value += "0";
                }
                value += String.valueOf(autoId.getIndex());

                autoId.setValue(value);
                autoId.setIndex(autoId.getIndex()+1);

            }
            autoIdRepo.save(autoId);
            return autoId;
        } catch (Exception e) {
            throw e;
        }
    }
}
