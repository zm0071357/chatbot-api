package com.chatbotapi.domain.ai;

import java.io.IOException;

public interface IOpenAi {

    /**
     * 问GPT问题
     * @param question
     * @return
     * @throws IOException
     */
    String doChatGPT(String question) throws IOException;
}
