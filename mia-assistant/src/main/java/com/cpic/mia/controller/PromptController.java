package com.cpic.mia.controller;

import com.cpic.mia.domain.AnalysisDataVO;
import com.cpic.mia.domain.MiaErrData;
import com.cpic.mia.domain.MiaQuery;
import com.cpic.mia.domain.request.BigDataSqlCallback;
import com.cpic.mia.domain.request.MiaPromptRequest;
import com.cpic.mia.service.PromptService;
import com.cpic.mia.service.RuleDeployService;
import com.cpic.mia.utils.CommonResult;
import com.cpic.mia.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    PromptService promptService;

    @Autowired
    RuleDeployService ruleDeployService;
    @PostMapping("/prompt")
    public CommonResult pormpt(@RequestBody MiaPromptRequest request) throws Exception {
        //获取到大模型出参
        String result = promptService.promptResult(request);
        //根据LLM输出结果进行模式匹配
        MiaQuery miaQuery = parseRequest(result);
        AnalysisDataVO analysisDataVO = ruleDeployService.executeRule(miaQuery);
        //结果数据集取样后,返回

        CommonResult res = ResponseUtil.success();
        res.setData(result);
        return res;
    }



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
