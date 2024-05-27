package com.cpic.mia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpic.mia.domain.AnalysisDataVO;
import com.cpic.mia.domain.MiaCnsvInfoPO;
import com.cpic.mia.domain.request.MiaPromptRequest;
import com.cpic.mia.service.MiaCnsvInfoService;
import com.cpic.mia.mapper.MiaCnsvInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author pengqian-012
* @description 针对表【mia_cnsv_info(稽核对话信息记录表)】的数据库操作Service实现
* @createDate 2024-05-26 16:59:26
*/
@Service
public class MiaCnsvInfoServiceImpl extends ServiceImpl<MiaCnsvInfoMapper, MiaCnsvInfoPO>
implements MiaCnsvInfoService{

    @Autowired
    MiaCnsvInfoMapper miaCnsvInfoMapper;

    @Override
    public void saveCnsvInfo(MiaPromptRequest request, AnalysisDataVO analysisDataVO) {

    }
}
