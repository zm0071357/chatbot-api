package com.chatbotapi.domain.ai.model.aggregates;

import com.chatbotapi.domain.ai.model.vo.Choices;
import com.chatbotapi.domain.ai.model.vo.Usage;
import lombok.Data;

import java.util.List;

/**
 * GPT回答返回的聚合对象
 */
@Data
public class AIAnswer {
    private String id;

    private String object;

    private int created;

    private String model;

    private List<Choices> choices;

    private Usage usage;

    private String system_fingerprint;
}
