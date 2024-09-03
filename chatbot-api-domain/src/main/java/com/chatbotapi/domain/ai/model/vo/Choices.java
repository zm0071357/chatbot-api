package com.chatbotapi.domain.ai.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Choices {
    private int index;
    private Message message;
    private String logprobs;
    private String finish_reason;
}
