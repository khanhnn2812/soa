package com.hitex.yousim.service;

import com.hitex.yousim.dto.model.FeedMessage;
import com.hitex.yousim.utils.exception.ApplicationException;

import java.text.ParseException;
import java.util.List;

public interface FeedMessageService {

    List<FeedMessage> getListFeedMessage() throws ApplicationException, ParseException;
}
