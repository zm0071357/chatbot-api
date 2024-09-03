package com.chatbotapi.domain.zsxq.model.vo;

import lombok.Data;

/**
 * 话题
 */
@Data
public class Topics {
    /**
     * id
     */
    private String topic_id;

    /**
     * 知识星球
     */
    private Group group;

    /**
     * 话题类型
     */
    private String type;

    /**
     * 问题详情
     */
    private Question question;

    /**
     * 是否被回答
     */
    private boolean answered;

    /**
     * 点赞数
     */
    private int likes_count;

    /**
     *
     */
    private int rewards_count;

    /**
     * 评论数
     */
    private int comments_count;

    /**
     * 阅读人数
     */
    private int reading_count;

    /**
     * 读者人数
     */
    private int readers_count;

    private boolean digested;

    private boolean sticky;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 用户操作
     */
    private UserSpecific user_specific;
}
