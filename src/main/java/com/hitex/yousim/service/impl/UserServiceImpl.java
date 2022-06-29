package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.user.UserRequest;
import com.hitex.yousim.dto.response.user.GetListUserReponse;
import com.hitex.yousim.dto.response.user.UserRespone;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.UserService;
import com.hitex.yousim.utils.GenCodeUtils;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.PasswordEncryption;
import com.hitex.yousim.utils.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private HttpServletRequest httpServletRequest;

//    @Override
//    public UserRespone addUser(BaseRequestData request) throws ApplicationException {
//        UserRespone userRespone = new UserRespone();
//        UserRequest userRequest = (UserRequest) request.getWsRequest();
//        try {
//            User userLogin = userRepository.findUserBySessionAngToken(request.getToken());
//            if (ObjectUtils.isEmpty(userLogin)) {
//                throw new ApplicationException("ERR_0000003");
//            }
//            if (StringUtils.isEmpty(userRequest.getUserName())) {
//                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("username"));
//            }
//            if (StringUtils.isEmpty(userRequest.getPassword())) {
//                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("password"));
//            }
//            User userr = userRepository.findByUsername(userRequest.getUserName());
//            if (ObjectUtils.isEmpty(userr)) {
//                User user = new User();
//                BeanUtils.copyProperties(userRequest, user);
//                user.setStatusUser(Constant.ACTIVE_USER);
//                user.setCreateTime(LocalDateTime.now());
//                user.setUpdateTime(LocalDateTime.now());
//                user.setPassword(PasswordEncryption.encryteBCryptPassword(userRequest.getPassword()));
//                userRepository.save(user);
//                BeanUtils.copyProperties(user, userRespone);
//            }
//        } catch (ApplicationException e) {
//            e.getLocalizedMessage();
//            throw e;
//        }
//        return userRespone;
//    }
//
//    @Override
//    public UserRespone createUser(BaseRequestData request) throws ApplicationException {
//        UserRespone userRespone = new UserRespone();
//        UserRequest userRequest = (UserRequest) request.getWsRequest();
//        try {
//            if (StringUtils.isEmpty(userRequest.getUserName())) {
//                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("username"));
//            }
//            if (StringUtils.isEmpty(userRequest.getPassword())) {
//                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("password"));
//            }
//            User userr = userRepository.findByUsername(userRequest.getUserName());
//            if (ObjectUtils.isEmpty(userr)) {
//                User user = new User();
//                BeanUtils.copyProperties(userRequest, user);
//                user.setStatusUser(Constant.ACTIVE_USER);
//                user.setCreateTime(LocalDateTime.now());
//                user.setUpdateTime(LocalDateTime.now());
//                user.setPassword(PasswordEncryption.encryteBCryptPassword(userRequest.getPassword()));
//                userRepository.save(user);
//                BeanUtils.copyProperties(user, userRespone);
//
//            }
//            return userRespone;
//        } catch (ApplicationException e) {
//            e.getLocalizedMessage();
//            return null;
//        }
//
//    }

    @Override
    public UserRespone login(BaseRequestData request) throws ApplicationException {
        UserRespone userRespone = null;
        UserRequest userRequest = (UserRequest) request.getWsRequest();
        try {
            User user = userRepository.findByUsername(userRequest.getUserName());
            if(ObjectUtils.isEmpty(user)) {
                throw new ApplicationException("Tài khoản không tồn tại");
            }
            boolean checkUser = PasswordEncryption.bCryptPasswordEncoder(userRequest.getPassword(), user.getPassword());
            if (checkUser) {
//                if (user.getRoleId() == Constant.ROLE_ADMIN) {
                userRespone = new UserRespone();
                BeanUtils.copyProperties(user, userRespone);
                String tokenData = GenCodeUtils.encrypt(user.getUserName() + "_" + user.getRole() + "_" + user.getUserId(), Constant.KEY, Constant.SECRET_KEY);
//                    Constant.userToken = tokenData;
                userRespone.setToken(tokenData);
                user.setToken(tokenData);
                HttpSession session = httpServletRequest.getSession();
                session.setMaxInactiveInterval(60 * 60 * 24);
                userRespone.setSession(session.getId());
                userRepository.save(user);
                BeanUtils.copyProperties(user, userRespone);
                userRespone.setPassword(null);
//                }
            } else {
                throw new ApplicationException("Mật khẩu không chính xác");
            }
        } catch (ApplicationException e) {
            throw e;
        }
        return userRespone;
    }

//    @Override
//    public UserRespone updateUser(BaseRequestData request) {
//        UserRequest userRequest = (UserRequest) request.getWsRequest();
//        User checkMail = userRepository.findByEmail(userRequest.getEmail());
////        User checkPhone = userRepository.findByPhone(userRequest.getPhone());
//        UserRespone userRespone = new UserRespone();
//        if (!ObjectUtils.isEmpty(checkMail)) {
//            User userUpdate = userRepository.findUserByUserId(userRequest.getUserId());
//            userUpdate.setRole(userRequest.getRole());
//            userUpdate.setEmail(userRequest.getEmail());
//            userUpdate.setStatusUser(userRequest.getStatusUser());
//            userUpdate.setUserName(userRequest.getUserName());
//            userUpdate.setPhone(userRequest.getPhone());
//            userUpdate.setUpdateTime(LocalDateTime.now());
//            userRepository.save(userUpdate);
//            BeanUtils.copyProperties(userUpdate, userRespone);
//        }
//        return userRespone;
//    }
//
//    @Override
//    public UserRespone selfUpdatedUser(BaseRequestData request) throws ApplicationException {
//        UserRequest userRequest = (UserRequest) request.getWsRequest();
//        UserRespone userRespone = new UserRespone();
//        try {
//            User user = userRepository.findByUserId(userRequest.getUserId());
//            if (!ObjectUtils.isEmpty(user)) {
//                user.setEmail(userRequest.getEmail());
//                user.setPhone(userRequest.getPhone());
//                user.setUpdateTime(LocalDateTime.now());
//                userRepository.save(user);
//                BeanUtils.copyProperties(user, userRespone);
//            }
//        } catch (Exception e) {
//            e.getLocalizedMessage();
//            throw new ApplicationException("error");
//        }
//        return userRespone;
//    }
//
//    @Override
//    public UserRespone detailUser(BaseRequestData request) {
//        UserRequest userRequest = (UserRequest) request.getWsRequest();
//        User user = userRepository.findUserByUserId(userRequest.getUserId());
//        UserRespone userRespone = new UserRespone();
//        BeanUtils.copyProperties(user, userRespone);
//        return userRespone;
//    }
//
//    @Override
//    public GetListUserReponse getListUser(BaseRequestData request) throws ApplicationException {
//        UserRequest userRequest = (UserRequest) request.getWsRequest();
//        try {
//            GetListUserReponse getListUserReponse = new GetListUserReponse();
//            List<User> userList = userRepository.getListUser(userRequest.getUserName(), userRequest.getStatusUser(),
//                    PageRequest.of(userRequest.getPage(), userRequest.getPageSize()));
//            int totalItem = userRepository.countUserByUserNameAndStatusUser(userRequest.getUserName(), userRequest.getStatusUser());
//            int totalPage = (int) Math.ceil((double) totalItem / (double) userRequest.getPageSize());
//            getListUserReponse.setTotalItem(totalItem);
//            getListUserReponse.setTotalPage(totalPage);
//            getListUserReponse.setUserList(userList);
//            return getListUserReponse;
//        } catch (Exception e) {
//            throw new ApplicationException("error");
//        }
//    }
//
//    @Override
//    public UserRespone getAdminInfo(BaseRequestData requestData) throws ApplicationException {
//        UserRespone userRespone = new UserRespone();
//        try {
//            String userRequest = GenCodeUtils.decrypt(requestData.getToken(), Constant.KEY, Constant.SECRET_KEY);
//            String userId = userRequest.split("_")[2];
//            User user = userRepository.findByUserId(Integer.parseInt(userId));
//            user.setPassword(null);
//            BeanUtils.copyProperties(user, userRespone);
//        } catch (Exception e) {
//            log.error(e.getStackTrace());
//            throw new ApplicationException("error");
//        }
//        return userRespone;
//    }
//
//    @Override
//    public boolean changePassUser(BaseRequestData request) throws ApplicationException {
//        UserRequest userRequest = (UserRequest) request.getWsRequest();
//        try {
//            String newPass = userRequest.getPassword();
//            User user = userRepository.findByUserId(userRequest.getUserId());
//            user.setPassword(PasswordEncryption.encryteBCryptPassword(newPass));
//            user.setUpdateTime(LocalDateTime.now());
//            userRepository.save(user);
//            return true;
//        } catch (Exception e) {
//            throw new ApplicationException("ERR_0000001");
//        }
//    }
//
//    @Override
//    public boolean selfChangePassUser(BaseRequestData request) throws ApplicationException {
//        UserRequest userRequest = (UserRequest) request.getWsRequest();
//        try {
//            String newPass = userRequest.getNewPass();
//            User user = userRepository.findByUserId(userRequest.getUserId());
//            if (!PasswordEncryption.bCryptPasswordEncoder(userRequest.getPassword(), user.getPassword())) {
//                return false;
//            } else {
//                user.setPassword(PasswordEncryption.encryteBCryptPassword(newPass));
//                user.setUpdateTime(LocalDateTime.now());
//                userRepository.save(user);
//                return true;
//            }
//        } catch (Exception e) {
//            throw new ApplicationException("ERR_0000001");
//        }
//    }
}
