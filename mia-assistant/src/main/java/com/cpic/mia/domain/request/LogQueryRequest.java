package com.cpic.mia.domain.request;

import lombok.Data;

/**
 * @author pengqian-012
 * @package com.cpic.mia.domain.request
 * @describe 日志查询
 * @date 2024/5/29
 */
@Data
public class LogQueryRequest {
    private String cnsvId;
    private String keyWord;

}
