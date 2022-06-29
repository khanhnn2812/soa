package com.hitex.yousim.dto.request.feedmessage;

import com.hitex.yousim.dto.model.FeedMessage;
import lombok.Data;

@Data
public class FeedMessageReq extends FeedMessage {
    private String textSearch;


}