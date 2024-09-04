package com.chatbotapi.domain.zsxq.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回答的请求对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerReq {
    /**
     * 回答
     */
    private ReqData req_data;
}
