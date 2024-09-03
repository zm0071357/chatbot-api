package com.chatbotapi.domain.ai.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 信息
 */
@Data
@AllArgsConstructor
public class Message {
    /**
     * 对话角色 assistant/user
     */
    private String role;

    /**
     * 回答内容
     */
    private String content;
}
