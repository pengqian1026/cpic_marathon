package com.cpic.mia.service;

import com.cpic.mia.domain.AnalysisDataVO;
import com.cpic.mia.domain.MiaErrData;
import com.cpic.mia.domain.MiaQuery;
import com.cpic.mia.domain.request.MiaPromptRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pengqian-012
 * @package com.cpic.mia.service
 * @describe
 * @date 2024/5/26
 */
@Service
public interface RuleDeployService {

    AnalysisDataVO executeRule(MiaQuery miaQuery, MiaPromptRequest request);
}
