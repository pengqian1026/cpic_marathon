package com.cpic.mia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpic.mia.domain.MiaCnsvInfoPO;

import java.util.List;

/**
 * @author pengqian-012
 * @description 针对表【mia_cnsv_info(稽核对话信息记录表)】的数据库操作Mapper
 * @createDate 2024-05-26 16:59:26
 * @Entity com.cpic.mia.domain.MiaCnsvInfoPO
 */
public interface MiaCnsvInfoMapper extends BaseMapper<MiaCnsvInfoPO> {

    List<MiaCnsvInfoPO> getHistoryCnsv(String cnsvId);

}
