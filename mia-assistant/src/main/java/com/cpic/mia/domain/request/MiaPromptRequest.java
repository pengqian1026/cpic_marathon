package com.cpic.mia.domain.request;

import lombok.Data;

@Data
public class MiaPromptRequest {
    public String appToke;
    public String userId;
    public String cnvsId;
    public String question;
}
