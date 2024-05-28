package com.cpic.mia.service.impl;

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
import com.cpic.mia.utils.StdCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
    public AnalysisDataVO executeRule(MiaQuery miaQuery, MiaPromptRequest request) {
        //List<MiaErrData> targetRecords = miaMapper.select(miaQuery);

        List<MiaHistoryInfoPO> targetRecords = miaHistoryInfoMapper.getErrDataByRule(miaQuery);

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
        analysisDataVO.setMiaErrDatas(targetRecords);
        analysisDataVO.setRuleId(ruleCode);
        analysisDataVO.setCnsvId(request.getCnvsId());
        analysisDataVO.setCost_sum(128.00);
        return analysisDataVO;
    }
}
