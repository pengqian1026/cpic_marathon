package com.cpic.mia.service;

import com.alibaba.fastjson.JSONObject;
import com.cpic.mia.domain.request.ChatOutRequst;
import com.cpic.mia.domain.request.MiaPromptRequest;

public interface PromptService {
    public ChatOutRequst promptResult(MiaPromptRequest request) throws Exception;

    public ChatOutRequst promptResultBck(MiaPromptRequest request) throws Exception;

}
