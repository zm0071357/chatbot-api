package com.chatbotapi.domain.zsxq.service;

import com.alibaba.fastjson.JSON;
import com.chatbotapi.domain.zsxq.IZsxqApi;
import com.chatbotapi.domain.zsxq.model.aggregates.UnansweredQuestionsAggregates;
import com.chatbotapi.domain.zsxq.model.req.AnswerReq;
import com.chatbotapi.domain.zsxq.model.req.ReqData;
import com.chatbotapi.domain.zsxq.model.res.AnswerRes;
import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ZsxqApi implements IZsxqApi {

    // 创建日志记录器
    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public UnansweredQuestionsAggregates queryUnansweredQuestions(String groupId, String cookie) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?/scope=unanswered_questions&count=20");
        get.addHeader("cookie",cookie);
        get.addHeader("Content-Type","application/json;charset=utf8");
        CloseableHttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("获取星球问题,groupId:{},jsonStr:{}",groupId,jsonStr);
            return JSON.parseObject(jsonStr, UnansweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("返回错误信息:" + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/answer");
        post.addHeader("cookie",cookie);
        post.addHeader("Content-Type","application/json;charset=utf8");
        //post.addHeader("user-agent","");

        AnswerReq answerReq = new AnswerReq(new ReqData(text,silenced));
        String paramJson = JSONObject.fromObject(answerReq).toString();

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "utf-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答星球问题结果,groupId:{},topicId:{},jsonStr:{}",groupId,topicId,jsonStr);
            AnswerRes res = JSON.parseObject(jsonStr, AnswerRes.class);
            return res.isSucceeded();
        } else {
            throw new RuntimeException("返回错误信息:" + response.getStatusLine().getStatusCode());
        }
    }
}
