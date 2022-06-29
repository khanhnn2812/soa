package com.hitex.yousim.service;

import java.lang.Exception;

import com.hitex.yousim.model.AutoId;
import com.hitex.yousim.service.impl.AutoIdServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AutoIdServiceJUnit extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    AutoIdServiceImpl autoIdService;

    @Test
    public void TaoLoaiNguoiSuDungMoi(){
        try {
            //create new code for staff
            AutoId id = autoIdService.getAutoId("ST");
            assertEquals(id.getValue(), "ST000001");
            assertEquals(id.getCode(), "ST");
        }
        catch (Exception e){
            Assert.fail("Không tạo mới đc mã Staff");
        }
    }

    @Test
    public void TaoNguoiSuDungMoi(){
        try {
            //when create new ma HD
            AutoId id = autoIdService.getAutoId("HD");
            assertEquals(id.getCode(), "HD");
        }
        catch (Exception e){
            Assert.fail("Không tạo mới đc được mã HD mới");
        }
    }
}
