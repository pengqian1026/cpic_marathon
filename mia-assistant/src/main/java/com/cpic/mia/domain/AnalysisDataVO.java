package com.cpic.mia.domain;

import lombok.Data;

import java.util.List;

/**
 * @author pengqian-012
 * @package com.cpic.mia.domain
 * @describe
 * @date 2024/5/26
 */
@Data
public class AnalysisDataVO {
    private String cnsvId;
    private String ruleId;
    private List<MiaHistoryInfoPO> miaErrDatas;
    private Double cost_sum;
}
