package com.chatbotapi.application.job;

import com.chatbotapi.domain.ai.IOpenAi;
import com.chatbotapi.domain.zsxq.IZsxqApi;
import com.chatbotapi.domain.zsxq.model.aggregates.UnansweredQuestionsAggregates;
import com.chatbotapi.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * 定时任务
 */
@EnableScheduling
@Configuration
public class ChatbotSchedule {

    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAi openAi;

    // cron.qqe2.com
    // 每5秒查询一次
    @Scheduled(cron = "0/5 * * * * ?")
    public void run(){
        try {
            // 防止封控
            if (new Random().nextBoolean()) {
                logger.info("防止封控，跳过");
                return;
            }
            // 查询待回答问题
            UnansweredQuestionsAggregates unansweredQuestionsAggregates = zsxqApi.queryUnansweredQuestions(groupId, cookie);
            logger.info("查询结果:{}",unansweredQuestionsAggregates);
            List<Topics> topicsList = unansweredQuestionsAggregates.getResp_data().getTopics();
            if (topicsList == null || topicsList.isEmpty()) {
                logger.info("本次未查询到待回答问题");
                return;
            }
            // AI回答
            Topics topic = topicsList.get(0);
            String answer = openAi.doChatGPT(topic.getQuestion().getText().trim());

            // 问题回复
            boolean isSucceed = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            logger.info("问题Id:{},问题:{},回答:{},回答是否成功:{}",
                    topic.getTopic_id(),topic.getQuestion().getText(),answer,isSucceed);
        } catch (Exception e) {
            logger.error("自动回答问题异常");
        }
    }

}
