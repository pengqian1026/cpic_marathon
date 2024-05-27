package com.cpic.mia.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cpic.mia.domain.AnalysisDataVO;
import com.cpic.mia.domain.MiaErrData;
import com.cpic.mia.domain.MiaQuery;
import com.cpic.mia.domain.request.BigDataSqlCallback;
import com.cpic.mia.domain.request.MiaPromptRequest;
import com.cpic.mia.service.MiaCnsvInfoService;
import com.cpic.mia.service.PromptService;
import com.cpic.mia.service.RuleDeployService;
import com.cpic.mia.utils.CommonResult;
import com.cpic.mia.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mia")
public class PromptController {

    @Autowired
    MiaCnsvInfoService miaCnsvInfoService;

    @Autowired
    PromptService promptService;

    @Autowired
    RuleDeployService ruleDeployService;
    @PostMapping("/prompt")
    public CommonResult pormpt(@RequestBody MiaPromptRequest request) throws Exception {
        //获取到大模型出参
        String result = promptService.promptResult(request);
        //根据LLM输出结果进行模式匹配
        JSONObject llmResult = JSONObject.parseObject(result);
        Object request_type = llmResult.get("request_type");
        if(ObjectUtils.isEmpty(request_type)){
            //结果数据集取样后,返回
            CommonResult res = ResponseUtil.success();
            res.setData(result);
            return res;
        }else{

        }

        MiaQuery miaQuery = parseRequest(result);
        AnalysisDataVO analysisDataVO = ruleDeployService.executeRule(miaQuery);

        //对话数据存储
        miaCnsvInfoService.saveCnsvInfo(request,analysisDataVO)
        //结果数据集取样后,返回
        CommonResult res = ResponseUtil.success();
        res.setData(analysisDataVO);
        return res;
    }

    //返回值数据格式解析
    private static MiaQuery parseRequest(String result){



        return null;

    }

    @PostMapping("/callback")
    public CommonResult callback(@RequestBody BigDataSqlCallback bigDataSqlCallback) throws Exception {
        log.info(bigDataSqlCallback.toString());
        CommonResult res = ResponseUtil.success();
        res.setData(bigDataSqlCallback);
        return res;
    }

}
