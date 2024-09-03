package com.chatbotapi.domain.zsxq.model.vo;

import lombok.Data;

/**
 * 回答者
 */
@Data
public class Questionee {
    /**
     * id
     */
    private String user_id;

    /**
     * 名字
     */
    private String name;

    /**
     * 头像url
     */
    private String avatar_url;

    /**
     * 描述
     */
    private String description;

    /**
     * 位置
     */
    private String location;
}
