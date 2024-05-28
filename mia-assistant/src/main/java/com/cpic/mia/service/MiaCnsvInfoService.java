package com.cpic.mia.service;

import com.alibaba.fastjson.JSONObject;
import com.cpic.mia.domain.AnalysisDataVO;

import com.cpic.mia.domain.request.ChatOutRequst;
import com.cpic.mia.domain.request.MiaPromptRequest;


/**
* @author pengqian-012
* @description 针对表【mia_cnsv_info(稽核对话信息记录表)】的数据库操作Service
* @createDate 2024-05-26 16:59:26
*/
public interface MiaCnsvInfoService {

    void saveUserCnsvInfo(MiaPromptRequest request, ChatOutRequst result, Object content);

}
