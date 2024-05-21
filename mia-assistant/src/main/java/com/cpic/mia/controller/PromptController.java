package com.cpic.mia.controller;

import com.cpic.mia.domain.request.MiaPromptRequest;
import com.cpic.mia.service.PromptService;
import com.cpic.mia.utils.CommonResult;
import com.cpic.mia.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mia")
public class PromptController {
    @Autowired
    PromptService promptService;
    @PostMapping("/prompt")
    public CommonResult pormpt(@RequestBody MiaPromptRequest request) throws Exception {
        String result = promptService.promptResult(request);
        CommonResult res = ResponseUtil.success();
        res.setData(result);
        return res;
    }

}
