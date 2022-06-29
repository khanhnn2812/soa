package com.hitex.yousim.service.impl;


import com.hitex.yousim.dto.response.feedmessage.FeedMessageRes;

import com.hitex.yousim.dto.model.FeedMessage;
import com.hitex.yousim.read.RSSFeedParser;
import com.hitex.yousim.service.FeedMessageService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.text.ParseException;

@Service
public class FeedMessageServiceImpl implements FeedMessageService {


    @Override
    public List<FeedMessage> getListFeedMessage() throws ApplicationException, ParseException {
        FeedMessageRes feedMessageRes = new FeedMessageRes();

        RSSFeedParser parser = new RSSFeedParser(
                "https://vnexpress.net/rss/thoi-su.rss");
        List<FeedMessage> feed = parser.readFeed();

        feedMessageRes.setListFeedMessage(feed);
        return feed;
    }
}
