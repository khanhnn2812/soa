package com.hitex.yousim.controller;

import com.hitex.yousim.dto.model.FeedMessage;
import com.hitex.yousim.service.FeedMessageService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/")
public class FeedMessageController extends BaseController {
    @Autowired
    FeedMessageService feedMessageService;

    @PostMapping(value = "getListFeedMessage")
    @ResponseBody
    public ResponseEntity getListFeedMessage() throws ApplicationException, ParseException {
        try {
            List<FeedMessage> response = feedMessageService.getListFeedMessage();
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}

