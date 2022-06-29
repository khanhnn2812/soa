package com.hitex.yousim.dto.request.user;

import com.hitex.yousim.model.User;
import lombok.Data;

@Data
public class UserRequest extends User {
    private String newPass;
    private int pageSize;
    private int page;

}
