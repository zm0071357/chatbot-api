package com.chatbotapi.domain.zsxq.model.vo;

import lombok.Data;

/**
 * 知识星球
 */
@Data
public class Group {
    /**
     * 星球id
     */
    private String group_id;

    /**
     * 星球名字
     */
    private String name;

    /**
     * 星球类型
     */
    private String type;
}
