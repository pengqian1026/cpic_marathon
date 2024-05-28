package com.cpic.mia.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cpic.mia.domain.AnalysisDataVO;
import com.cpic.mia.domain.MiaErrData;
import com.cpic.mia.domain.MiaQuery;
import com.cpic.mia.domain.request.BigDataSqlCallback;
import com.cpic.mia.domain.request.ChatOutRequst;
import com.cpic.mia.domain.request.MiaPromptRequest;
import com.cpic.mia.service.MiaCnsvInfoService;
import com.cpic.mia.service.PromptService;
import com.cpic.mia.service.RuleDeployService;
import com.cpic.mia.utils.CommonResult;
import com.cpic.mia.utils.ResponseUtil;
import com.cpic.mia.utils.RestResponseCode;
import com.cpic.mia.utils.StdCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
        if(ObjectUtils.isEmpty(request.getCnvsId())){
            String cnsvId = StdCodeUtil.generateCode();
            request.setCnvsId(cnsvId);
        }
        //获取到大模型出参
        ChatOutRequst result = promptService.promptResult(request);
        //String result = promptService.promptResultBck(request);

        //根据LLM输出结果进行模式匹配
        CommonResult res = ResponseUtil.success();

        String userCheckFlag = result.getUserCheckFlag();
        if(userCheckFlag.equals("0")){
            res.setCode(RestResponseCode.NOT_RELATIED.getCode());
            String content = "当前问题可能与医保稽核无关,无关内容提问会影响稽核规则稳定性;\\n"+result.getRequestData();
            res.setData(content);
            //对话数据存储
            miaCnsvInfoService.saveUserCnsvInfo(request,result,content);
        }else{
            MiaQuery miaQuery = parseRequest(result);
            AnalysisDataVO analysisDataVO = ruleDeployService.executeRule(miaQuery,request);
            //结果数据集取样后,返回
            res.setCode(RestResponseCode.SUCCESS.getCode());
            res.setData(analysisDataVO);
            //对话数据存储
            miaCnsvInfoService.saveUserCnsvInfo(request,result,analysisDataVO);
        }
        return res;
    }

    //返回值数据格式解析
    private static MiaQuery parseRequest(ChatOutRequst result){
        MiaQuery miaQuery = new MiaQuery();
        String requestDataStr = result.getRequestData();
        JSONObject requestData = JSONObject.parseObject(requestDataStr);

        String ruleType = requestData.getString("ruleType");
        JSONObject ruleData = requestData.getJSONObject("ruleData");
        if(ruleType.equals("1")){
            String scope = ruleData.getString("scope");
            String itemNameHosp1 = ruleData.getString("itemNameHosp1");
            String itemNameHosp2 = ruleData.getString("itemNameHosp2");
            miaQuery.setRule(String.valueOf(1));
            miaQuery.setScope(scope);
            miaQuery.setItemNameHosp1(itemNameHosp1);
            miaQuery.setItemNameHosp2(itemNameHosp2);
        }else if(ruleType.equals("2")){
            String scope = ruleData.getString("scope");
            String itemNameHosp1 = ruleData.getString("itemNameHosp1");
            Object num = ruleData.get("num");
            miaQuery.setRule(String.valueOf(2));
            miaQuery.setScope(scope);
            miaQuery.setItemNameHosp1(itemNameHosp1);
            miaQuery.setNum(num.toString());
        }else if(ruleType.equals("3")){
            String itemNameHosp1 = ruleData.getString("itemNameHosp1");
            miaQuery.setRule(String.valueOf(3));
            miaQuery.setItemNameHosp1(itemNameHosp1);
        }

        return miaQuery;

    }

    @PostMapping("/callback")
    public CommonResult callback(@RequestBody BigDataSqlCallback bigDataSqlCallback) throws Exception {
        log.info(bigDataSqlCallback.toString());
        CommonResult res = ResponseUtil.success();
        res.setData(bigDataSqlCallback);
        return res;
    }

}
