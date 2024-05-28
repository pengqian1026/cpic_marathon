package com.cpic.mia.mapper;

import com.cpic.mia.domain.MiaErrData;
import com.cpic.mia.domain.MiaQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MiaMapper {
    List<MiaErrData> select(MiaQuery query);
}
