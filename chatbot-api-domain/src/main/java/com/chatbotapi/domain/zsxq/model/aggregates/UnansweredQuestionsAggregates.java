package com.chatbotapi.domain.zsxq.model.aggregates;

import com.chatbotapi.domain.zsxq.model.res.RespData;
import lombok.Data;

/**
 * 未回答问题的聚合信息
 */
@Data
public class UnansweredQuestionsAggregates {
    /**
     * 接口是否请求成功
     */
    private boolean succeeded;

    /**
     * 请求数据
     */
    private RespData resp_data;
}
