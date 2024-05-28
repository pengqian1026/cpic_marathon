package com.cpic.mia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpic.mia.domain.MiaRuleInfoPO;

/**
* @author pengqian-012
* @description 针对表【mia_rule_info(稽核规则信息表)】的数据库操作Mapper
* @createDate 2024-05-26 16:59:26
* @Entity com.cpic.mia.domain.MiaRuleInfoPO
*/

public interface MiaRuleInfoMapper extends BaseMapper<MiaRuleInfoPO> {

    void createMiaRuleInfo(MiaRuleInfoPO miaRuleInfoPO);
}
