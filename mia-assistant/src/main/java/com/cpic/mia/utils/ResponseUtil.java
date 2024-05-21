package com.cpic.mia.utils;

public class ResponseUtil {
    public static CommonResult failedResponse(RestResponseCode msg){
        CommonResult CommonResult = new CommonResult();
        CommonResult.setCode(msg.getCode());
        CommonResult.setSuccess(false);
        CommonResult.setMessage(msg.getMsg());
        return CommonResult;
    }
    public static CommonResult failedResponse(RestResponseCode msg,String str){
        CommonResult CommonResult = new CommonResult();
        CommonResult.setCode(msg.getCode());
        CommonResult.setSuccess(false);
        CommonResult.setMessage(str);
        return CommonResult;
    }
    public static CommonResult success() {
        CommonResult CommonResult = new CommonResult();
        CommonResult.setCode(RestResponseCode.SUCCESS.getCode());
        CommonResult.setSuccess(true);
        CommonResult.setMessage(RestResponseCode.SUCCESS.getMsg());
        return CommonResult;
    }
    public static CommonResult success(String msg) {
        CommonResult res = success();
        res.setMessage(msg);
        return res;
    }
}
