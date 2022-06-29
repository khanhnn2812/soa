package com.hitex.yousim.service.impl;


import com.hitex.yousim.dto.request.BaseRequestData;

import com.hitex.yousim.dto.request.feedmessage.FeedMessageReq;
import com.hitex.yousim.dto.response.feedmessage.FeedMessageRes;

import com.hitex.yousim.model.FeedMessage;
import com.hitex.yousim.read.RSSFeedParser;
import com.hitex.yousim.service.FeedMessageService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.net.URL;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

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
