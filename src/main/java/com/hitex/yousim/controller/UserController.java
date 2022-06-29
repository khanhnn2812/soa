package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.user.UserRequest;
import com.hitex.yousim.dto.response.BaseResponseData;
import com.hitex.yousim.dto.response.user.UserRespone;
import com.hitex.yousim.model.User;
import com.hitex.yousim.service.UserService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@RequestMapping("/api/")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

//    @PostMapping(value = "addUser")
//    @ResponseBody
//    public ResponseEntity addUser(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
//        try {
//            UserRespone userRespone = userService.addUser(requestData);
//            return success(userRespone);
//        } catch (ApplicationException e) {
//            return error(e.getCode(), e.getMessage());
//        }
//    }
//
//    @PostMapping(value = "createUser")
//    @ResponseBody
//    public ResponseEntity createUser(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
//        try {
//            UserRespone userRespone = userService.createUser(requestData);
//            return success(userRespone);
//        } catch (ApplicationException e) {
//            return error(e.getCode(), e.getMessage());
//        }
//    }

    @PostMapping(value = "login")
    @ResponseBody
    public ResponseEntity login(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
        try {
            UserRespone userRespone = userService.login(requestData);
            return success(userRespone);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
