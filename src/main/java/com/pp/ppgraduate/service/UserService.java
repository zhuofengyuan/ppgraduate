package com.pp.ppgraduate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pp.ppgraduate.entity.UserModel;
import com.pp.ppgraduate.entity.WeChatModel;

public interface UserService extends IService<UserModel> {
    public UserModel login(String openid);

    public boolean insertUser(WeChatModel weChatModel);

    public boolean updateUser(UserModel userModel);

    public UserModel selectUser(UserModel userModel);
}
