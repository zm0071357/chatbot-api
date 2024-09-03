package com.chatbotapi.domain.zsxq.model.vo;

import lombok.Data;

/**
 * 用户详情
 */
@Data
public class OwnerDetail {

    /**
     * 问题数量
     */
    private int questions_count;

    /**
     * 加入星球时间
     */
    private String join_time;
}
