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

    /**
     * 处理用户请求，并根据请求内容进行智能问答或规则分析。
     *
     * @param request 包含用户查询信息和请求参数的对象。
     * @return 返回一个通用结果对象，包含处理结果的状态码和数据。
     * @throws Exception 如果处理过程中发生错误，则抛出异常。
     */
    @PostMapping("/prompt")
    public CommonResult pormpt(@RequestBody MiaPromptRequest request) throws Exception {
        // 如果请求中没有提供canvas ID，则自动生成一个
        if (ObjectUtils.isEmpty(request.getCnvsId())) {
            String cnsvId = StdCodeUtil.generateCode();
            request.setCnvsId(cnsvId);
        }
        // 调用服务，获取大模型的处理结果
        ChatOutRequst result = promptService.promptResult(request);
        // 根据LLM（Large Language Model）的输出结果，决定后续处理流程
        CommonResult res = ResponseUtil.success();

        String userCheckFlag = result.getUserCheckFlag();
        // 如果模型判断当前问题与医保稽核不相关
        if (userCheckFlag.equals("0")) {
            // 设置响应状态码，并准备相关提示信息
            res.setCode(RestResponseCode.NOT_RELATIED.getCode());
            String content = "当前问题可能与医保稽核无关,无关内容提问会影响稽核规则稳定性;\\n" + result.getRequestData();
            res.setData(content);
            // 存储对话数据
            miaCnsvInfoService.saveUserCnsvInfo(request, result, content);
        } else {
            // 如果问题与医保稽核相关，则进行规则分析处理
            MiaQuery miaQuery = parseRequest(result);
            AnalysisDataVO analysisDataVO = ruleDeployService.executeRule(miaQuery, request);
            // 返回规则处理的结果
            res.setCode(RestResponseCode.SUCCESS.getCode());
            res.setData(analysisDataVO);
            // 存储对话及分析数据
            miaCnsvInfoService.saveUserCnsvInfo(request, result, analysisDataVO);
        }
        return res;
    }

    /**
     * 解析请求数据并根据规则类型设置MiaQuery对象的属性。
     *
     * @param result 包含请求数据的ChatOutRequst对象。
     * @return 解析后生成的MiaQuery对象，包含了根据规则类型提取的相关信息。
     */
    private static MiaQuery parseRequest(ChatOutRequst result) {
        MiaQuery miaQuery = new MiaQuery();
        String requestDataStr = result.getRequestData();
        JSONObject requestData = JSONObject.parseObject(requestDataStr);

        // 从请求数据中解析规则类型和相关参数
        String ruleType = requestData.getString("ruleType");
        JSONObject ruleData = requestData.getJSONObject("ruleData");

        // 根据不同的规则类型设置MiaQuery对象的属性
        if (ruleType.equals("1")) {
            String scope = ruleData.getString("scope");
            String itemNameHosp1 = ruleData.getString("itemNameHosp1");
            String itemNameHosp2 = ruleData.getString("itemNameHosp2");
            miaQuery.setRule(String.valueOf(1));
            miaQuery.setScope(scope);
            miaQuery.setItemNameHosp1(itemNameHosp1);
            miaQuery.setItemNameHosp2(itemNameHosp2);
        } else if (ruleType.equals("2")) {
            String scope = ruleData.getString("scope");
            String itemNameHosp1 = ruleData.getString("itemNameHosp1");
            Object num = ruleData.get("num");
            miaQuery.setRule(String.valueOf(2));
            miaQuery.setScope(scope);
            miaQuery.setItemNameHosp1(itemNameHosp1);
            miaQuery.setNum(num.toString());
        } else if (ruleType.equals("3")) {
            String itemNameHosp1 = ruleData.getString("itemNameHosp1");
            miaQuery.setRule(String.valueOf(3));
            miaQuery.setItemNameHosp1(itemNameHosp1);
        }
        return miaQuery;

    }

    /**
     * 处理大数据SQL回调的接口方法。
     *
     * @param bigDataSqlCallback 接收前端传来的BigDataSqlCallback对象，包含回调所需的数据。
     * @return 返回一个CommonResult对象，其中包含操作结果和回调数据。
     * @throws Exception 如果处理过程中出现异常，则抛出Exception。
     */
    @PostMapping("/callback")
    public CommonResult callback(@RequestBody BigDataSqlCallback bigDataSqlCallback) throws Exception {
        // 记录回调信息日志
        log.info(bigDataSqlCallback.toString());

        CommonResult res = ResponseUtil.success(); // 创建一个表示成功的响应结果
        res.setData(bigDataSqlCallback); // 将回调数据设置到响应结果中

        return res;
    }

}
