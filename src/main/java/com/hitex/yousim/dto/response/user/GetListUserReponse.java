package com.hitex.yousim.dto.response.user;


import com.hitex.yousim.model.User;
import lombok.Data;

import java.util.List;

/**
 * @author Chidq
 * @project yousim
 * @created 10/04/2021 - 1:28 PM
 */
@Data
public class GetListUserReponse  {
    private int totalItem;
    private int totalPage;
    private List<User> userList;
}
