package com.cpic.mia.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

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
    private List<Map<String,Object>> dataSchema;
    private List<Map<String,Object>> replyData;
    private String[] suggestList;
    private String summary;
}
