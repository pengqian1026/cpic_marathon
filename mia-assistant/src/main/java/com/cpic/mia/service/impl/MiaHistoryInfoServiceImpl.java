package com.cpic.mia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpic.mia.domain.MiaHistoryInfoPO;
import com.cpic.mia.service.MiaHistoryInfoService;
import com.cpic.mia.mapper.MiaHistoryInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author pengqian-012
* @description 针对表【mia_history_info(医院病例信息表)】的数据库操作Service实现
* @createDate 2024-05-26 16:59:26
*/
@Service
public class MiaHistoryInfoServiceImpl extends ServiceImpl<MiaHistoryInfoMapper, MiaHistoryInfoPO>
implements MiaHistoryInfoService{

}
