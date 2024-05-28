package com.cpic.mia.domain.request;

import com.cpic.mia.domain.MiaQuery;
import lombok.Data;

import java.util.List;

/**
 * @author pengqian-012
 * @package com.cpic.mia.domain.request
 * @describe 大模型返回的应用请求
 * @date 2024/5/27
 */
@Data
public class ChatOutRequst {
    private String requestType;
    private String userCheckFlag;
    private String requestData;
}
