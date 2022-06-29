package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.hoaDon.HoaDonReq;
import com.hitex.yousim.dto.response.area.AreaRes;
import com.hitex.yousim.model.Area;
import com.hitex.yousim.repository.AreaRepo;
import com.hitex.yousim.service.impl.AreaServiceImpl;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AreaServiceJUnit extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    AreaServiceImpl areaService;
    @Autowired
    AreaRepo areaRepo;

    @Test
    public void LayDuocKhuVucTheoIdTrongDB(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            AreaRes area = areaService.getListArea(baseRequestData, "0");
            List<Area> areaList = areaRepo.getListArea("0");
            System.out.println(area.toString());
            assertNotNull(area);
            assertEquals(area.getListArea(), areaList);
            Area HN = new Area();
            HN.setId("01");
            HN.setName("Thành phố Hà Nội");
            HN.setParent("0");
            HN.setType("TINH");
            assertEquals(area.getListArea().get(0), HN);
            assertEquals(area.getListArea().size(), 63);
        }
        catch (Exception e){
            Assert.fail("Không lấy thành công khu vực");
        }
    }

    @Test
    public void LayDuocKhuVucTheoIdTrongDBKhongToken(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("");
            AreaRes area = areaService.getListArea(baseRequestData, "0");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Chưa đăng nhập");
        }
    }

    @Test
    public void LayDuocKhuVucTheoIdTrongDBKoTonTai(){
        try {
            BaseRequestData<HoaDonReq> baseRequestData = new BaseRequestData();
            baseRequestData.setToken("t65cFG6aFx5wFoaOkOSvNQ==");
            AreaRes area = areaService.getListArea(baseRequestData, "000000");
            List<Area> areaList = areaRepo.getListArea("0");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), " area không tồn tại");
        }
    }
}
