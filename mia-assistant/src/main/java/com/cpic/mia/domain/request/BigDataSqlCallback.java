package com.cpic.mia.domain.request;

import lombok.Data;

/**
 * @author pengqian-012
 * @package com.cpic.mia.domain.request
 * @describe 1
 * @date 2024/5/23
 */
@Data
public class BigDataSqlCallback {
    private String status;
    private String result;
    private String bizNo;
    private String appId;
}
