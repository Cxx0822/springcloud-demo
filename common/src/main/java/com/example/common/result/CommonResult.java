package com.example.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel(value = "全局统一返回结果")
public class CommonResult {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    public CommonResult(){}

    public static CommonResult ok(){
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        commonResult.setCode(ResultCodeEnum.SUCCESS.getCode());
        commonResult.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return commonResult;
    }

    public static CommonResult error(){
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        commonResult.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        commonResult.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return commonResult;
    }

    public static CommonResult setResult(ResultCodeEnum resultCodeEnum){
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(resultCodeEnum.getSuccess());
        commonResult.setCode(resultCodeEnum.getCode());
        commonResult.setMessage(resultCodeEnum.getMessage());
        return commonResult;
    }

    public CommonResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public CommonResult message(String message){
        this.setMessage(message);
        return this;
    }

    public CommonResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public CommonResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public CommonResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}

