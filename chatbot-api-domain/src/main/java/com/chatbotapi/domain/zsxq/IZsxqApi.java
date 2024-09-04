package com.chatbotapi.domain.zsxq;

import com.chatbotapi.domain.zsxq.model.aggregates.UnansweredQuestionsAggregates;

import java.io.IOException;

public interface IZsxqApi {

    /**
     * 获取提问
     * @param groupId 知识星球id
     * @param cookie cookie
     * @return
     * @throws IOException
     */
    UnansweredQuestionsAggregates queryUnansweredQuestions(String groupId, String cookie) throws IOException;

    /**
     * 回答
     * @param groupId 知识星球id
     * @param cookie  cookie
     * @param topicId 话题id
     * @param text    回答文本
     * @param silenced 是否全员可见
     * @return
     * @throws IOException
     */
    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;

}
