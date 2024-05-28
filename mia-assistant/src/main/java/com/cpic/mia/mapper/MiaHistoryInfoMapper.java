package com.cpic.mia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpic.mia.domain.MiaHistoryInfoPO;
import com.cpic.mia.domain.MiaQuery;

import java.util.List;

/**
* @author pengqian-012
* @description 针对表【mia_history_info(医院病例信息表)】的数据库操作Mapper
* @createDate 2024-05-26 16:59:26
* @Entity com.cpic.mia.domain.MiaHistoryInfoPO
*/
public interface MiaHistoryInfoMapper extends BaseMapper<MiaHistoryInfoPO> {

    List<MiaHistoryInfoPO> getErrDataByRule(MiaQuery miaQuery);

}
