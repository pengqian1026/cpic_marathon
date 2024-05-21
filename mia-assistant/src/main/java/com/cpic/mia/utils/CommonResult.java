package com.cpic.mia.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Type;

public class CommonResult<T> {

    private T data;

    private boolean success = true;

    private Integer code;

    private String message;


    public CommonResult<T> data(T data) {
        this.data = data;
        return this;
    }

    public CommonResult() {
    }

    public T getData() {
        return this.data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setData(final T data) {
        String jsonData = JSON.toJSONString(data, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        this.data = (JSON.parseObject(jsonData, (Type) data.getClass().getDeclaringClass(), Feature.OrderedField));
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CommonResult)) {
            return false;
        } else {
            CommonResult<?> other = (CommonResult) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                if (this.isSuccess() != other.isSuccess()) {
                    return false;
                } else {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code != null) {
                            return false;
                        }
                    } else if (!this$code.equals(other$code)) {
                        return false;
                    }

                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message != null) {
                            return false;
                        }
                    } else if (!this$message.equals(other$message)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CommonResult;
    }

    public int hashCode() {
        int result = 1;
        T data = this.getData();
        result = result * 59 + (data == null ? 43 : data.hashCode());
        result = result * 59 + (this.isSuccess() ? 79 : 97);
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "CommonResult(data=" + this.getData() + ", success=" + this.isSuccess() + ", code=" + this.getCode() + ", message=" + this.getMessage() + ")";
    }
}