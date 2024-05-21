package com.cpic.mia.service;

import com.cpic.mia.domain.request.MiaPromptRequest;

public interface PromptService {
    public String promptResult(MiaPromptRequest request) throws Exception;
}
