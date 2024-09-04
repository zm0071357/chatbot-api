package com.chatbotapi.domain.zsxq.model.res;

import com.chatbotapi.domain.zsxq.model.vo.Topics;
import lombok.Data;

import java.util.List;

/**
 * 请求全部话题
 */
@Data
public class RespData {
    /**
     * 话题集合
     */
    private List<Topics> topics;
}
