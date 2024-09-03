package com.chatbotapi.domain.zsxq.model.vo;

import lombok.Data;

/**
 * 用户操作
 */
@Data
public class UserSpecific {
    /**
     * 点赞
     */
    private boolean liked;

    /**
     * 收藏
     */
    private boolean subscribed;

}
