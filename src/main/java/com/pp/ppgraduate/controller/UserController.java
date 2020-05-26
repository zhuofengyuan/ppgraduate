package com.pp.ppgraduate.controller;

import com.google.gson.Gson;
import com.pp.ppgraduate.entity.UserModel;
import com.pp.ppgraduate.entity.WeChatModel;
import com.pp.ppgraduate.entity.WeChatSession;
import com.pp.ppgraduate.service.UserService;
import com.pp.ppgraduate.utils.EmptyUtil;
import com.pp.ppgraduate.utils.MyException;
import com.pp.ppgraduate.utils.Result;
import com.pp.ppgraduate.utils.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private Logger logger = Logger.getLogger(this.getClass());

    private static final String APPID = "wxc56aa70bdeb3f75d";
//    private static final String APPID = "wxe50169ffe4555ed7";
    private static final String SECRET = "c85078e3a4224f03c5b25f9919bbf8da";
//    private static final String SECRET = "59d8d4a9b4bc945016340e5e01728657";

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody WeChatModel weChatModel){
        String openid = null;
        String session_key = null;
        String errcode = null;
        String errmsg = null;
        WeChatSession weChatSession;
        //微信的接口
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+ "&secret="+SECRET+"&js_code="+ weChatModel.getCode() +"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        //进行网络请求,访问url接口
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //根据返回值进行后续操作

        if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            Gson gson = new Gson();
            //解析从微信服务器获得的openid和session_key;
            weChatSession = gson.fromJson(sessionData, WeChatSession.class);
            //获取用户的唯一标识
            openid = weChatSession.getOpenid();
            //获取会话秘钥
            session_key = weChatSession.getSession_key();
            //获取错误码
            errcode = weChatSession.getErrcode();
            //获取错误信息
            errmsg = weChatSession.getErrmsg();
            //下面就可以写自己的业务代码了

            //最后要返回一个自定义的登录态,用来做后续数据传输的验证
        }
        try{
            if (openid == null || session_key == null){
                return Result.error("errcode: " + errcode + ";errmsg: " + errmsg);
            }
            UserModel user = userService.login(openid);
            weChatModel.setOpenId(openid);
            weChatModel.setSeesionKey(session_key);
            if(EmptyUtil.isEmpty(user)){
                boolean flag = userService.insertUser(weChatModel);
                if(flag == true){
                    return Result.success(weChatModel);
                }
                else {
                    return Result.error("用户login失败！");
                }
            }
            else{
                return Result.success(weChatModel);
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "添加用户信息Exception");
        }
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public Result updateUser(@RequestBody UserModel userModel){
        if(EmptyUtil.isEmpty(userModel)){
            return Result.error("用户信息为空");
        }
        try{
            boolean flag = userService.updateUser(userModel);
            if (flag==true){
                return Result.success("修改成功！");
            }
            else {
                return Result.error("修改失败！");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "修改用户信息Exception");
        }
    }

    @PostMapping("/selectUser")
    @ResponseBody
    public Result selectUser(@RequestBody UserModel userModel){
        if(EmptyUtil.isEmpty(userModel)){
            return Result.error("用户信息为空");
        }
        if(EmptyUtil.isEmpty(userModel.getUserId())){
            return Result.error("用户id为空");
        }
        try{
            return Result.success(userService.selectUser(userModel));
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询用户信息Exception");
        }
    }
}
