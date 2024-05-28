package com.cpic.mia.utils;

public enum RestResponseCode {

    SUCCESS(200, "success"),
    FAIL(300, "fail"),
    BODY_NOT_MATCH(400, "请求的数据格式不符！"),
    SIGNATURE_NOT_MATCH(401, "请求的数字签名不匹配！"),
    NOT_FOUND(404, "未找到该资源！"),
    METHOD_NOT_ALLOWED(405, "方法不匹配！"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误！"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试！"),
    UNDEFINED_ERROR(1000, "未知错误！"),
    NOTE(1001, "自定义提示！"),
    PARAMETER_ERROR(409, "参数错误！"),
    NOT_LOGIN(4002, "未登录"),
    SYSTEM_ERROR(500,"系统错误"),
    DB_ERROR(600,"数据库错误"),
    EXPIRE(4003, "登录过期"),
    TABLE_NOT_FOUND(6001, "数据库未查到"),
    FILE_UPLOAD_ERROR(700,"文件上传错误"),
    FILE_DOWN_ERROR(701,"文件下载错误"),
    FILE_EXPORT_ERROR(702,"文件导入错误"),
    FILE_DELETE_ERROR(703,"文件删除错误"),
    LN_NO_AUDIT(8001,"上游节点未审核"),
    NOT_RELATIED(9999,"内容与医保稽核无关"),
    ;
    int code;
    String msg;

    RestResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

