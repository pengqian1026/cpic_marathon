package com.cpic.mia.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpic.mia.domain.AnalysisDataVO;
import com.cpic.mia.domain.MiaCnsvHistoryVO;
import com.cpic.mia.domain.MiaCnsvInfoPO;
import com.cpic.mia.domain.request.ChatOutRequst;
import com.cpic.mia.domain.request.LogQueryRequest;
import com.cpic.mia.domain.request.MiaPromptRequest;
import com.cpic.mia.service.MiaCnsvInfoService;
import com.cpic.mia.mapper.MiaCnsvInfoMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author pengqian-012
 * @description 针对表【mia_cnsv_info(稽核对话信息记录表)】的数据库操作Service实现
 * @createDate 2024-05-26 16:59:26
 */
@Service
public class MiaCnsvInfoServiceImpl extends ServiceImpl<MiaCnsvInfoMapper, MiaCnsvInfoPO>
        implements MiaCnsvInfoService {

    @Autowired
    private MiaCnsvInfoMapper miaCnsvInfoMapper;

    /**
     * 保存用户咨询信息的方法。
     * 该方法将用户提问和对应的回答保存到数据库中，包括提问的内容、回答的内容以及相关的额外信息。
     *
     * @param miaPromptRequest 包含用户提问信息的对象，如问题内容和相关的标识ID。
     * @param result           包含聊天机器人回答信息的对象，主要是回答的内容。
     * @param content          额外的内容信息，以字符串形式存储，提供给保存功能使用。
     */
    @Override
    public void saveUserCnsvInfo(MiaPromptRequest miaPromptRequest, ChatOutRequst result, AnalysisDataVO content) throws JsonProcessingException {
        // 存储用户请求信息
        MiaCnsvInfoPO request = new MiaCnsvInfoPO();
        request.setCnsvId(miaPromptRequest.getCnsvId());
        request.setCnsvSeqNo(1); // 设置序列号，用户信息为1
        request.setRole("user"); // 设置角色为用户
        request.setQuestion(miaPromptRequest.getQuestion()); // 设置用户提出的问题
        request.setAnswer(result.getRequestData()); // 设置对应的回答内容
        ObjectMapper mapper = new ObjectMapper();
        String finalContent = mapper.writeValueAsString(content);
        request.setContent(finalContent); // 设置额外的内容信息
        miaCnsvInfoMapper.insert(request); // 插入到数据库

        // 存储机器人回答信息
        MiaCnsvInfoPO answer = new MiaCnsvInfoPO();
        answer.setCnsvId(miaPromptRequest.getCnsvId()); // 使用相同的对话ID
        answer.setCnsvSeqNo(2); // 设置序列号，回答信息为2
        answer.setRole("assistant"); // 设置角色为助手
        answer.setQuestion(miaPromptRequest.getQuestion()); // 设置同样的问题，便于配对
        answer.setAnswer(result.getRequestData()); // 设置机器人的回答内容
        answer.setContent(finalContent); // 同样存储额外的内容信息
        miaCnsvInfoMapper.insert(answer); // 插入到数据库中
    }

    @Override
    public List<MiaCnsvHistoryVO> getRelatedCnsvInfo(LogQueryRequest logQueryRequest) {
        List<String> relatedCnsvId = miaCnsvInfoMapper.getRelatedCnsvId(logQueryRequest);
        if(CollectionUtils.isEmpty(relatedCnsvId)){
            return null;
        }

        List<MiaCnsvHistoryVO> historyCnsvInfo = miaCnsvInfoMapper.getHistoryCnsvInfo(relatedCnsvId);

        return historyCnsvInfo;
    }
}
