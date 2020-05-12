package com.pp.ppgraduate.service.impl;

import com.pp.ppgraduate.dao.UserDao;
import com.pp.ppgraduate.entity.UserModel;
import com.pp.ppgraduate.entity.WeChatModel;
import com.pp.ppgraduate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    public UserModel login(String openid){
        return userDao.login(openid);
    }

    public boolean insertUser(WeChatModel weChatModel){
        return userDao.insertUser(weChatModel);
    }

    public boolean updateUser(UserModel userModel){
        return userDao.updateUser(userModel);
    }

    public UserModel selectUser(UserModel userModel){
        return userDao.selectUser(userModel);
    }
}
