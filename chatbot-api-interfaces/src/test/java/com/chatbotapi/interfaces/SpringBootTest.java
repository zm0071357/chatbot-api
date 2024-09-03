package com.chatbotapi.interfaces;

import com.alibaba.fastjson.JSON;
import com.chatbotapi.domain.ai.IOpenAi;
import com.chatbotapi.domain.zsxq.IZsxqApi;
import com.chatbotapi.domain.zsxq.model.aggregates.UnansweredQuestionsAggregates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;


@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAi openAi;

    /**
     * 测试知识星球问题获取
     * @throws IOException
     */
    @Test
    public void test_zsxqApi() throws IOException {
        UnansweredQuestionsAggregates unansweredQuestionsAggregates = zsxqApi.queryUnansweredQuestions(groupId, cookie);
        logger.info("测试结果:{}", JSON.toJSONString(unansweredQuestionsAggregates));
    }

    @Test
    public void test_chatGPT() throws IOException {
        String res = openAi.doChatGPT("背诵静夜思");
        logger.info("测试结果:{}",res);
    }

}
