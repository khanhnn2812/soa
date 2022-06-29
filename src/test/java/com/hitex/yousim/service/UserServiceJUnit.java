package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.khachHang.KhachHangReq;
import com.hitex.yousim.dto.request.user.UserRequest;
import com.hitex.yousim.dto.response.user.UserRespone;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.impl.UserServiceImpl;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceJUnit {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImpl userService;

    @Test
    public void login(){
        try {
            BaseRequestData<UserRequest> baseRequestData = new BaseRequestData();
            UserRequest userRequest = new UserRequest();
            userRequest.setUserName("longnt");
            userRequest.setPassword("Long@123");
            baseRequestData.setWsRequest(userRequest);
            UserRespone user = userService.login(baseRequestData);
            User userTest = userRepository.findByUsername("longnt");
            assertNotNull(user);
            assertEquals(user.getToken(), userTest.getToken());
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void loginWrongUsername(){
        try {
            BaseRequestData<UserRequest> baseRequestData = new BaseRequestData();
            UserRequest userRequest = new UserRequest();
            userRequest.setUserName("long");
            userRequest.setPassword("Long@123");
            baseRequestData.setWsRequest(userRequest);
            UserRespone user = userService.login(baseRequestData);
            fail("Not throw exception");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Tài khoản không tồn tại");
        }
    }

    @Test
    public void loginWrongPassword(){
        try {
            BaseRequestData<UserRequest> baseRequestData = new BaseRequestData();
            UserRequest userRequest = new UserRequest();
            userRequest.setUserName("longnt");
            userRequest.setPassword("Long");
            baseRequestData.setWsRequest(userRequest);
            UserRespone user = userService.login(baseRequestData);
            fail("Not throw exception");
        }
        catch (Exception e){
            assertThat(e, instanceOf(ApplicationException.class));
            assertEquals(e.getMessage(), "Mật khẩu không chính xác");
        }
    }
}
