package com.cpic.mia.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cpic.mia.domain.MiaCnsvInfoPO;
import com.cpic.mia.domain.request.ChatOutRequst;
import com.cpic.mia.domain.request.MiaPromptRequest;
import com.cpic.mia.mapper.MiaCnsvInfoMapper;
import com.cpic.mia.service.MiaCnsvInfoService;
import com.cpic.mia.service.PromptService;
import com.cpic.mia.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PromptServiceImpl implements PromptService {
    @Value("${big-model.url}")
    private String url;
    @Value("${big-model.app-token}")
    private String appToken;

    @Value("${big-model.72b-bck.url}")
    private String bck_url;

    @Value("${big-model.72b-bck.serviceId}")
    private String bck_serviceId;

    @Value("${big-model.72b-bck.longAppToken}")
    private String bck_LongAppToken;

    @Autowired
    private MiaCnsvInfoMapper miaCnsvInfoMapper;

    @Override
    public ChatOutRequst promptResult(MiaPromptRequest request) throws Exception {
        String tmpRequestPath = "tmp/65b_request.txt";
        //请求参数组装
        String finalJson = generateFinalContent(request,tmpRequestPath);

        //设置请求参数
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("app-token",appToken);

        con.setDoOutput(true);
        con.getOutputStream().write(finalJson.getBytes());

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
           String tmp = (String) JSONObject.parseObject(o.toString()).get("content");
            result = tmp.replace("<ret>","")
                    .replace("<end>","")
                   .replace(" ","");
            System.out.println(result);
        }
        ChatOutRequst chatOutRequst = new ChatOutRequst();
        try{
            JSONObject rstJson = JSONObject.parseObject(result);
            String requestType = rstJson.getString("requestType");
            String userCheckFlag = rstJson.getString("userCheckFlag");
            String requestData = rstJson.getString("requestData");
            chatOutRequst.setRequestType(requestType);
            chatOutRequst.setUserCheckFlag(userCheckFlag);
            chatOutRequst.setRequestData(requestData);
        }catch ( Exception e ){
            chatOutRequst.setRequestType(String.valueOf(3));
            chatOutRequst.setUserCheckFlag(String.valueOf(0));
            chatOutRequst.setRequestData(result);
        }finally {
            return chatOutRequst;
        }
    }

    @Override
    public ChatOutRequst promptResultBck(MiaPromptRequest request) throws Exception {
        String tmpRequestPath = "tmp/72b_request.txt";
        //提示词与 用户提问组装;
        String question=request.question;
        String useQuestion = FileUtil.loadConfig(tmpRequestPath);
        String finalJson = useQuestion.replace("#question#", question);

        //设置请求参数
        HttpURLConnection con = (HttpURLConnection) new URL(bck_url).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("serviceId",bck_serviceId);
        con.setRequestProperty("longAppToken",bck_LongAppToken);

        con.setDoOutput(true);
        con.getOutputStream().write(finalJson.getBytes());

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
        JSONArray choices = JSONObject.parseObject(response.toString()).getJSONArray("choices");
        Object message = JSONObject.parseObject(choices.get(0).toString()).get("message");
        Object content = JSONObject.parseObject(message.toString()).get("content");
        System.out.println(content.toString());

        ChatOutRequst chatOutRequst = new ChatOutRequst();
        try{
            JSONObject rstJson = JSONObject.parseObject(content.toString());
            String requestType = rstJson.getString("requestType");
            String userCheckFlag = rstJson.getString("userCheckFlag");
            String requestData = rstJson.getString("requestData");
            chatOutRequst.setRequestType(requestType);
            chatOutRequst.setUserCheckFlag(userCheckFlag);
            chatOutRequst.setRequestData(requestData);
        }catch ( Exception e ){
            chatOutRequst.setRequestType(String.valueOf(3));
            chatOutRequst.setUserCheckFlag(String.valueOf(0));
            chatOutRequst.setRequestData(content.toString());
        }finally {
            return chatOutRequst;
        }
    }

    public String generateFinalContent(MiaPromptRequest request,String tmpRequestPath) throws  Exception{
        //历史对话信息组装;
        JSONArray historyCnsv = getHisPromptsByCnsvId(request.getCnvsId());

        if(ObjectUtils.isEmpty(historyCnsv)){
            historyCnsv = new JSONArray();
        }

        String question=request.question;
        JSONObject newQuestion = new JSONObject();
        newQuestion.put("role","user");
        newQuestion.put("content",question);
        historyCnsv.add(newQuestion);

        String promptContent = "tmp/promptFile.txt";
        String promptStr = FileUtil.loadConfig(promptContent);
        JSONArray preContent = JSONArray.parseArray(promptStr);

        for(int i=0;i < historyCnsv.size();i++){
            preContent.add(historyCnsv.get(i));
        }

        String allContent = preContent.toString();

        String useQuestion = FileUtil.loadConfig(tmpRequestPath);
        String finalJson = useQuestion.replace("#messagesText#", allContent);

        return  finalJson;
    }

    public JSONArray getHisPromptsByCnsvId(String cnvsId){
        List<MiaCnsvInfoPO> historyCnsv = this.miaCnsvInfoMapper.getHistoryCnsv(cnvsId);
        if(CollectionUtils.isEmpty(historyCnsv)){
            return null;
        }
        JSONArray hisPromptLists = new JSONArray();
        for(int i=0;i < historyCnsv.size();i++){
            HashMap<String, String> hisprompt = new HashMap<>();
            String role = historyCnsv.get(i).getRole();
            String content = null;
            if(role.equals("user")){
                content = historyCnsv.get(i).getAnswer();
            }else {
                content = historyCnsv.get(i).getQuestion();
            }
            hisprompt.put("content",content);
            hisPromptLists.add(hisprompt);
        }
        return  hisPromptLists;
    }
}
