package com.pp.ppgraduate.utils;

public class Result {
    private Integer code;
    private String msg;
    private Object data;
    private Object data2;
    private String resultId;
    private String differenceTime; //

    public String getDifferenceTime() {
        return differenceTime;
    }

    public void setDifferenceTime(String differenceTime) {
        this.differenceTime = differenceTime;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 接口成功调用
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result=new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg("成功！");
        result.setData(object);
        return result;
    }


    /**
     * 接口成功调用
     * @param object
     * @return
     */
    public static Result success(Object object,String differenceTime,String buxuyoa) {
        Result result=new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg("成功！");
        result.setData(object);
        result.setDifferenceTime(differenceTime);
        return result;
    }

    /**
     * 接口成功调用
     * @param
     * @return
     */
    public static Result setSuccessMsg(String msg) {
        Result result=new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 接口成功调用
     * @param
     * @return
     */
    public static Result success() {
        Result result=new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg("成功！");
        result.setData(null);
        return result;
    }

    /**
     * 接口成功调用
     * @param object
     * @return
     */
    public static Result success(String msg, Object object) {
        Result result=new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    /**
     * 接口成功调用
     * @param object
     * @param object2
     * @return
     */
    public static Result success(String msg, Object object, Object object2) {
        Result result=new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(msg);
        result.setData(object);
        result.setData2(object2);
        return result;
    }
    /**
     * 接口失败调用
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    /**
     * 接口失败调用
     * @param msg
     * @param msg
     * @return
     */
    public static Result error(String msg){
        Result result=new Result();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }
}
