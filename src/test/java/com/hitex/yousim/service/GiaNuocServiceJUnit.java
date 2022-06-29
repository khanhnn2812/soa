package com.hitex.yousim.service;

import java.lang.Exception;
import java.util.List;

import com.hitex.yousim.model.GiaNuoc;
import com.hitex.yousim.service.impl.GiaNuocServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GiaNuocServiceJUnit extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    GiaNuocServiceImpl giaNuocService;

    @Test
    public void LayListGiaNuocChoNguoiBT(){
        try{
            List<GiaNuoc>  list = giaNuocService.getGiaNuoc(false,false);
            assertEquals(list.size(), 4);
            GiaNuoc bac1 = new GiaNuoc();
            bac1.setBac(1);
            bac1.setGiaNuoc(5973);
            bac1.setDoanhNghiep(false);
            bac1.setHoNgheo(false);
            bac1.setId(1);
            assertTrue(list.contains(bac1));
        }catch (Exception e){
            Assert.fail("Ko lay thanh cong");
        }
    }

    @Test
    public void LayListGiaNuocChoNguoiNgheo(){
        try{
            List<GiaNuoc>  list = giaNuocService.getGiaNuoc(true,false);
            assertEquals(list.size(), 4);
            GiaNuoc bac1 = new GiaNuoc();
            bac1.setBac(1);
            bac1.setGiaNuoc(3600);
            bac1.setDoanhNghiep(false);
            bac1.setHoNgheo(true);
            bac1.setId(5);
            assertTrue(list.contains(bac1));
        }catch (Exception e){
            Assert.fail("Ko lay thanh cong");
        }
    }

    @Test
    public void LayListGiaNuocChoDN(){
        try{
            List<GiaNuoc>  list = giaNuocService.getGiaNuoc(false,true);
            assertEquals(list.size(), 4);
            GiaNuoc bac1 = new GiaNuoc();
            bac1.setBac(1);
            bac1.setGiaNuoc(9955);
            bac1.setDoanhNghiep(true);
            bac1.setHoNgheo(false);
            bac1.setId(9);
            bac1.setDescription("1");
            assertTrue(list.contains(bac1));
        }catch (Exception e){
            Assert.fail("Ko lay thanh cong");
        }
    }
}
