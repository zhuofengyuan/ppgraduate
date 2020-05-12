package com.pp.ppgraduate.utils;

public enum ResultEnum {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(100, "成功！"),
    ERROR(101, "出问题了！没有找到数据！"),
    SEND_VERIFICATION_ERROR(105,"发送验证码失败！"),
    NO_AUTHORITY(102,"请求失败，请重新登陆！"),
    NO_NEW(103,",非最新数据，前台需要进行数据刷新！"),
    CODE_ERROR(106,"微信openid或者code错误"),
    OPEN_ID_ERROR(107,"微信用户未注册")
    ;


    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
