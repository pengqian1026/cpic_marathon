package com.cpic.mia.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpic.mia.domain.AnalysisDataVO;
import com.cpic.mia.domain.MiaCnsvInfoPO;
import com.cpic.mia.domain.request.ChatOutRequst;
import com.cpic.mia.domain.request.MiaPromptRequest;
import com.cpic.mia.service.MiaCnsvInfoService;
import com.cpic.mia.mapper.MiaCnsvInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author pengqian-012
* @description 针对表【mia_cnsv_info(稽核对话信息记录表)】的数据库操作Service实现
* @createDate 2024-05-26 16:59:26
*/
@Service
public class MiaCnsvInfoServiceImpl extends ServiceImpl<MiaCnsvInfoMapper, MiaCnsvInfoPO>
implements MiaCnsvInfoService{

    @Autowired
    private MiaCnsvInfoMapper miaCnsvInfoMapper;

    @Override
    public void saveUserCnsvInfo(MiaPromptRequest miaPromptRequest, ChatOutRequst result, Object content) {
        //存request
        MiaCnsvInfoPO request = new MiaCnsvInfoPO();
        request.setCnsvId(miaPromptRequest.getCnvsId());
        request.setCnsvSeqNo(1);
        request.setRole("user");
        request.setQuestion(miaPromptRequest.getQuestion());
        request.setAnswer(result.getRequestData());
        request.setContent(content.toString());
        miaCnsvInfoMapper.insert(request);
        //存answer
        MiaCnsvInfoPO answer = new MiaCnsvInfoPO();
        answer.setCnsvId(miaPromptRequest.getCnvsId());
        answer.setCnsvSeqNo(2);
        answer.setRole("assistant");
        answer.setQuestion(miaPromptRequest.getQuestion());
        answer.setAnswer(result.getRequestData());
        answer.setContent(content.toString());
        miaCnsvInfoMapper.insert(answer);

    }
}
