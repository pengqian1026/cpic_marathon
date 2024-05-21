package com.cpic.mia.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cpic.mia.domain.request.MiaPromptRequest;
import com.cpic.mia.service.PromptService;
import com.cpic.mia.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class PromptServiceImpl implements PromptService {
    @Value("${big-model.url}")
    private String url;
    @Value("${big-model.app-token}")
    private String appToken;
    @Override
    public String promptResult(MiaPromptRequest request) throws Exception {
        //设置请求参数
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("app-token",appToken);
        String question=request.question;

        String json = FileUtil.loadConfig("JSON/request.json");
        String promptContent = json.replace("question", question);

        con.setDoOutput(true);
        con.getOutputStream().write(promptContent.getBytes());


        //获取响应结果
        InputStreamReader reader = new InputStreamReader(con.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer response = new StringBuffer();
        String inputLine;
        while ((inputLine =bufferedReader.readLine())!=null){
            response.append(inputLine);
        }
        bufferedReader.close();
        //提取想要的回复内容
        Object content = JSONObject.parseObject(response.toString()).get("content");
        Object payload = JSONObject.parseObject(content.toString()).get("payload");
        Object choices = JSONObject.parseObject(payload.toString()).get("choices");
        Object text = JSONObject.parseObject(choices.toString()).get("text");
        JSONArray objects = JSONObject.parseArray(text.toString());
        String result = null;
        for (Object o:objects){
            result = (String) JSONObject.parseObject(o.toString()).get("content");
            System.out.println(result);
        }
        return result;
    }
}
