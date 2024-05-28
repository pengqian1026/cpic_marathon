package com.cpic.mia.service;

import com.cpic.mia.domain.request.ChatOutRequst;
import com.cpic.mia.domain.request.MiaPromptRequest;
/**
 * PromptService 接口定义了与提示结果相关的服务。
 * 它提供了两种方法来处理不同的提示请求。
 */
public interface PromptService {

    /**
     * 处理提示结果的请求。
     *
     * @param request 包含提示请求详细信息的 MiaPromptRequest 对象。
     * @return 返回一个 ChatOutRequst 对象，包含处理结果。
     * @throws Exception 如果处理过程中出现错误，则抛出异常。
     */
    ChatOutRequst promptResult(MiaPromptRequest request) throws Exception;

    /**
     * 处理备份提示结果的请求。
     *
     * @param request 包含提示请求详细信息的 MiaPromptRequest 对象。
     * @return 返回一个 ChatOutRequst 对象，包含处理结果。
     * @throws Exception 如果处理过程中出现错误，则抛出异常。
     */
    ChatOutRequst promptResultBck(MiaPromptRequest request) throws Exception;

}

