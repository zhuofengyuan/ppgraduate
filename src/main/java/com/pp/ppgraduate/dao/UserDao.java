package com.pp.ppgraduate.dao;

import com.pp.ppgraduate.entity.UserModel;
import com.pp.ppgraduate.entity.WeChatModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public UserModel login(String openid);

    public boolean insertUser(WeChatModel weChatModel);

    public boolean updateUser(UserModel userModel);

    public UserModel selectUser(UserModel userModel);
}
