package com.chatbotapi.domain.zsxq.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 填写回答
 */
@Data
public class ReqData {
    /**
     * 文本
     */
    private String text;

    /**
     * 图片id集合
     */
    private String[] image_ids = new String[]{};

    /**
     * 是否全员可见
     */
    private boolean silenced;

    public ReqData(String text, boolean silenced) {
        this.text = text;
        this.silenced = silenced;
    }
}
