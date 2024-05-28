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

    /**
     * 执行规则分析并返回分析数据。
     *
     * @param miaQuery 包含查询条件的对象，用于指定规则查询的参数。
     * @param request  包含用户信息和请求参数的对象，用于指定规则的额外上下文信息。
     * @return 返回一个包含分析数据、规则ID、CNVS ID和成本总和的AnalysisDataVO对象。
     */
    @Override
    public AnalysisDataVO executeRule(MiaQuery miaQuery, MiaPromptRequest request) {
        // 根据规则查询条件获取目标记录
        List<MiaHistoryInfoPO> targetRecords = miaHistoryInfoMapper.getErrDataByRule(miaQuery);

        // 生成唯一的规则代码
        String ruleCode = StdCodeUtil.generateCode();
        // 如果目标记录非空，则插入一条新的规则信息
        if (!CollectionUtils.isEmpty(targetRecords)) {
            MiaRuleInfoPO miaRuleInfoPO = new MiaRuleInfoPO();
            // 设置规则信息
            miaRuleInfoPO.setRuleCode(ruleCode);
            miaRuleInfoPO.setRuleName(miaQuery.getItemNameHosp1());
            miaRuleInfoPO.setRuleType(miaQuery.getRule());
            miaRuleInfoPO.setHospitalId(request.getUserId());
            miaRuleInfoPO.setItemnamehosp1(miaQuery.getItemNameHosp1());
            miaRuleInfoPO.setItemnamehosp2(miaQuery.getItemNameHosp2());
            miaRuleInfoPO.setScope((Integer.valueOf(miaQuery.getScope())));
            miaRuleInfoPO.setNum(Long.getLong(miaQuery.getNum()));
            // 插入规则信息到数据库
            miaRuleInfoMapper.insert(miaRuleInfoPO);
        }

        // 构建并返回分析数据视图对象
        AnalysisDataVO analysisDataVO = new AnalysisDataVO();
        analysisDataVO.setMiaErrDatas(targetRecords); // 设置错误数据列表
        analysisDataVO.setRuleId(ruleCode); // 设置规则ID
        analysisDataVO.setCnsvId(request.getCnvsId()); // 设置CNVS ID
        analysisDataVO.setCost_sum(128.00); // 设置成本总和
        return analysisDataVO;
    }

}
