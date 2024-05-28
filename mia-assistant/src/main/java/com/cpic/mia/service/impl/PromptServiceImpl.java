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

    /**
     * 根据给定的MiaPromptRequest请求，获取并处理响应结果。
     *
     * @param request 包含请求参数的MiaPromptRequest对象。
     * @return ChatOutRequst 包含处理后的请求信息的对象。
     * @throws Exception 抛出异常处理网络请求错误或解析错误。
     */
    @Override
    public ChatOutRequst promptResult(MiaPromptRequest request) throws Exception {
        String tmpRequestPath = "tmp/65b_request.txt";
        // 组装最终的请求内容
        String finalJson = generateFinalContent(request, tmpRequestPath);

        // 设置HTTP请求参数并发送POST请求
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("app-token", appToken);

        con.setDoOutput(true);
        con.getOutputStream().write(finalJson.getBytes());

        // 读取并处理响应结果
        InputStreamReader reader = new InputStreamReader(con.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer response = new StringBuffer();
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();

        // 解析响应结果，提取所需内容
        Object content = JSONObject.parseObject(response.toString()).get("content");
        Object payload = JSONObject.parseObject(content.toString()).get("payload");
        Object choices = JSONObject.parseObject(payload.toString()).get("choices");
        Object text = JSONObject.parseObject(choices.toString()).get("text");
        JSONArray objects = JSONObject.parseArray(text.toString());
        String result = null;
        for (Object o : objects) {
            String tmp = (String) JSONObject.parseObject(o.toString()).get("content");
            result = tmp.replace("<ret>", "")
                    .replace("<end>", "")
                    .replace(" ", "");
            System.out.println(result);
        }

        // 处理并返回结果
        ChatOutRequst chatOutRequst = new ChatOutRequst();
        try {
            JSONObject rstJson = JSONObject.parseObject(result);
            String requestType = rstJson.getString("requestType");
            String userCheckFlag = rstJson.getString("userCheckFlag");
            String requestData = rstJson.getString("requestData");
            chatOutRequst.setRequestType(requestType);
            chatOutRequst.setUserCheckFlag(userCheckFlag);
            chatOutRequst.setRequestData(requestData);
        } catch (Exception e) {
            chatOutRequst.setRequestType(String.valueOf(3));
            chatOutRequst.setUserCheckFlag(String.valueOf(0));
            chatOutRequst.setRequestData(result);
        } finally {
            return chatOutRequst;
        }
    }

    /**
     * 处理用户提示结果的返回。
     *
     * @param request 包含用户提问信息的请求对象。
     * @return ChatOutRequst 包含后续操作类型、用户验证标志和请求数据的响应对象。
     * @throws Exception 如果处理过程中发生错误，则抛出异常。
     */
    @Override
    public ChatOutRequst promptResultBck(MiaPromptRequest request) throws Exception {
        String tmpRequestPath = "tmp/72b_request.txt";
        // 组装提示词和用户提问
        String question = request.question;
        String useQuestion = FileUtil.loadConfig(tmpRequestPath);
        String finalJson = useQuestion.replace("#question#", question);

        // 设置HTTP请求参数
        HttpURLConnection con = (HttpURLConnection) new URL(bck_url).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("serviceId", bck_serviceId);
        con.setRequestProperty("longAppToken", bck_LongAppToken);

        con.setDoOutput(true);
        con.getOutputStream().write(finalJson.getBytes());

        // 获取并处理响应结果
        InputStreamReader reader = new InputStreamReader(con.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer response = new StringBuffer();
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();
        // 提取回复内容
        JSONArray choices = JSONObject.parseObject(response.toString()).getJSONArray("choices");
        Object message = JSONObject.parseObject(choices.get(0).toString()).get("message");
        Object content = JSONObject.parseObject(message.toString()).get("content");
        System.out.println(content.toString());

        ChatOutRequst chatOutRequst = new ChatOutRequst();
        try {
            JSONObject rstJson = JSONObject.parseObject(content.toString());
            String requestType = rstJson.getString("requestType");
            String userCheckFlag = rstJson.getString("userCheckFlag");
            String requestData = rstJson.getString("requestData");
            chatOutRequst.setRequestType(requestType);
            chatOutRequst.setUserCheckFlag(userCheckFlag);
            chatOutRequst.setRequestData(requestData);
        } catch (Exception e) {
            // 处理解析异常
            chatOutRequst.setRequestType(String.valueOf(3));
            chatOutRequst.setUserCheckFlag(String.valueOf(0));
            chatOutRequst.setRequestData(content.toString());
        } finally {
            return chatOutRequst;
        }
    }

    /**
     * 根据提供的请求和临时请求路径生成最终内容。
     *
     * @param request        包含请求信息的对象，比如历史对话的CNVS ID。
     * @param tmpRequestPath 临时请求文件的路径，用于加载额外的对话信息。
     * @return 经过处理，包含历史对话和当前请求问题的最终JSON字符串。
     * @throws Exception 如果读取文件或处理数据过程中出现错误，则抛出异常。
     */
    public String generateFinalContent(MiaPromptRequest request, String tmpRequestPath) throws Exception {
        // 组装历史对话信息
        JSONArray historyCnsv = getHisPromptsByCnsvId(request.getCnvsId());

        // 如果历史对话为空，则初始化为空数组
        if (ObjectUtils.isEmpty(historyCnsv)) {
            historyCnsv = new JSONArray();
        }

        // 添加当前用户的问题到历史对话中
        String question = request.question;
        JSONObject newQuestion = new JSONObject();
        newQuestion.put("role", "user");
        newQuestion.put("content", question);
        historyCnsv.add(newQuestion);

        // 加载预设的对话内容模板
        String promptContent = "tmp/promptFile.txt";
        String promptStr = FileUtil.loadConfig(promptContent);
        JSONArray preContent = JSONArray.parseArray(promptStr);

        // 将历史对话内容添加到预设内容中
        for (int i = 0; i < historyCnsv.size(); i++) {
            preContent.add(historyCnsv.get(i));
        }

        // 将合并后的内容转换为字符串
        String allContent = preContent.toString();

        // 加载并处理临时请求文件中的内容，替换特定标记为对话内容
        String useQuestion = FileUtil.loadConfig(tmpRequestPath);
        String finalJson = useQuestion.replace("#messagesText#", allContent);

        return finalJson;
    }

    /**
     * 根据cnvsId获取历史提示信息列表
     *
     * @param cnvsId 画布ID
     * @return 返回历史提示信息的JSONArray对象，如果无历史信息则返回null
     */
    public JSONArray getHisPromptsByCnsvId(String cnvsId) {
        // 通过画布ID获取历史信息列表
        List<MiaCnsvInfoPO> historyCnsv = this.miaCnsvInfoMapper.getHistoryCnsv(cnvsId);
        if (CollectionUtils.isEmpty(historyCnsv)) {
            return null;
        }
        JSONArray hisPromptLists = new JSONArray();
        // 遍历历史信息列表，将每条信息包装成HashMap并添加到JSONArray中
        for (int i = 0; i < historyCnsv.size(); i++) {
            HashMap<String, String> hisprompt = new HashMap<>();
            String role = historyCnsv.get(i).getRole();
            String content = null;
            // 根据角色(user或admin)，获取相应的提示内容(回答或问题)
            if (role.equals("user")) {
                content = historyCnsv.get(i).getAnswer();
            } else {
                content = historyCnsv.get(i).getQuestion();
            }
            hisprompt.put("content", content);
            hisPromptLists.add(hisprompt);
        }
        return hisPromptLists;
    }
}

