package com.cpic.mia.mapper;

import com.cpic.mia.domain.MiaErrData;
import com.cpic.mia.domain.MiaQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MiaMapper {
    MiaErrData select(MiaQuery query);
}
