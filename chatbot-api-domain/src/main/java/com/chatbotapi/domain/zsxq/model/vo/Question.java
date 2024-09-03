package com.chatbotapi.domain.zsxq.model.vo;

import lombok.Data;

/**
 * 问题详情
 */
@Data
public class Question {
    /**
     * 提问者
     */
    private Owner owner;

    /**
     * 回答者
     */
    private Questionee questionee;

    /**
     * 回答文本
     */
    private String text;

    /**
     * 星球是否到期
     */
    private boolean expired;

    /**
     * 是否匿名
     */
    private boolean anonymous;

    /**
     * 提问者详情
     */
    private OwnerDetail owner_detail;

    /**
     * 提问者位置
     */
    private String owner_location;

}
