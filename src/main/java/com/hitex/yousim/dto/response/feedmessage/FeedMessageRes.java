package com.hitex.yousim.dto.response.feedmessage;

import com.hitex.yousim.dto.model.FeedMessage;
import lombok.Data;

import java.util.List;

@Data
public class FeedMessageRes  {
    List<FeedMessage> listFeedMessage;
}