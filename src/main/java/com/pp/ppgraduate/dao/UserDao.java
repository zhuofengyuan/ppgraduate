package com.pp.ppgraduate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pp.ppgraduate.entity.UserModel;
import com.pp.ppgraduate.entity.WeChatModel;

public interface UserDao extends BaseMapper<UserModel> {
    public UserModel login(String openid);

    public boolean insertUser(WeChatModel weChatModel);

    public boolean updateUser(UserModel userModel);

    public UserModel selectUser(UserModel userModel);
}
