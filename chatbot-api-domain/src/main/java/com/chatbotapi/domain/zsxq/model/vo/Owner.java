package com.chatbotapi.domain.zsxq.model.vo;

import lombok.Data;

/**
 * 用户
 */
@Data
public class Owner {

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
     * 位置
     */
    private String location;
}
