package com.cpic.mia.domain;

import lombok.Data;

import java.util.List;

/**
 * @author pengqian-012
 * @package com.cpic.mia.domain
 * @describe
 * @date 2024/5/29
 */
@Data
public class MiaCnsvHistoryVO {
    private String cnsvId;
    private List<MiaCnsvInfoPO> miaCnsvInfoPOS;
}
