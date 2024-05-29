package com.cpic.mia.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cpic.mia.domain.AnalysisDataVO;
import com.cpic.mia.domain.MiaErrData;
import com.cpic.mia.domain.MiaHistoryInfoPO;
import com.cpic.mia.domain.MiaQuery;
import com.cpic.mia.domain.MiaRuleInfoPO;
import com.cpic.mia.domain.request.MiaPromptRequest;
import com.cpic.mia.mapper.MiaHistoryInfoMapper;
import com.cpic.mia.mapper.MiaMapper;
import com.cpic.mia.mapper.MiaRuleInfoMapper;
import com.cpic.mia.service.RuleDeployService;
import com.cpic.mia.utils.FileUtil;
import com.cpic.mia.utils.StdCodeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pengqian-012
 * @package com.cpic.mia.service.impl
 * @describe
 * @date 2024/5/26
 */
@Service
public class RuleDeployServiceImpl implements RuleDeployService {

    @Autowired
    private MiaHistoryInfoMapper miaHistoryInfoMapper;

    @Autowired
    private MiaRuleInfoMapper miaRuleInfoMapper;

    @Override
    public AnalysisDataVO executeRule(MiaQuery miaQuery, MiaPromptRequest request) throws Exception {
        //List<MiaErrData> targetRecords = miaMapper.select(miaQuery);

        List<MiaHistoryInfoPO> targetRecords = miaHistoryInfoMapper.getErrDataByRule(miaQuery);

        //计算总金额
        //Double amount = miaHistoryInfoMapper.getSumCost(miaQuery);
        Double amount = 0.0;
        if(!CollectionUtils.isEmpty(targetRecords)){
            for(MiaHistoryInfoPO miaHistoryInfoPO:targetRecords){
                double cost = Double.parseDouble(miaHistoryInfoPO.getCostSum());
                amount += cost;
            }
        }

        String ruleCode = StdCodeUtil.generateCode();
        if(!CollectionUtils.isEmpty(targetRecords)){
            MiaRuleInfoPO miaRuleInfoPO = new MiaRuleInfoPO();
            miaRuleInfoPO.setRuleCode(ruleCode);
            miaRuleInfoPO.setRuleName(miaQuery.getItemNameHosp1());
            miaRuleInfoPO.setRuleType(miaQuery.getRule());
            miaRuleInfoPO.setHospitalId(request.getUserId());
            miaRuleInfoPO.setItemnamehosp1(miaQuery.getItemNameHosp1());
            miaRuleInfoPO.setItemnamehosp2(miaQuery.getItemNameHosp2());
            miaRuleInfoPO.setScope((Integer.valueOf(miaQuery.getScope())));
            miaRuleInfoPO.setNum(Long.getLong(miaQuery.getNum()));
            miaRuleInfoMapper.insert(miaRuleInfoPO);
        }


        AnalysisDataVO analysisDataVO = new AnalysisDataVO();
        List<MiaHistoryInfoPO> simpleData = targetRecords.size() <= 10 ? targetRecords : targetRecords.subList(0, 10);
        ArrayList<Map<String,Object>> sampleResult = new ArrayList<>();
        for(MiaHistoryInfoPO miaHistoryInfoPO:simpleData){
            Map<String, Object> sample = convertBeanToMap(miaHistoryInfoPO);
            sampleResult.add(sample);
        }
        analysisDataVO.setReplyData(sampleResult);
        String schema = FileUtil.loadConfig("tmp/MiaHistoryDataSchema.json");
        ObjectMapper schemaMapper = new ObjectMapper();
        List<Map<String, Object>> schemaList = schemaMapper.readValue(schema, List.class);
        analysisDataVO.setDataSchema(schemaList);

        analysisDataVO.setRuleId(ruleCode);
        analysisDataVO.setCnsvId(request.getCnsvId());

        Integer recordCnt = targetRecords.size();
        String summary = "规则("+ruleCode+")已生成,命中阳性记录数("+recordCnt+")条,阳性数据总金额合计("+amount+")元";
        analysisDataVO.setSummary(summary);
        return analysisDataVO;
    }

    public static Map<String,Object> convertBeanToMap(Object bean) throws IllegalArgumentException, IllegalAccessException{
        Field[] fields = bean.getClass().getDeclaredFields();
        HashMap<String,Object> data = new HashMap<String,Object>();
        for(Field field : fields){
            field.setAccessible(true);
            data.put(field.getName(), field.get(bean));
        }
        return data;
    }
}
