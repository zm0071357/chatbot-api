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
        // 构建Http客户端
        CloseableHttpClient client = HttpClientBuilder.create().build();
        // 请求接口
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=unanswered_questions&count=20");
        // 添加请求头
        get.addHeader("cookie",cookie);
        get.addHeader("Content-Type","application/json; charset=UTF-8");
        // 执行请求
        CloseableHttpResponse response = client.execute(get);
        // 接口响应200 接收数据
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 将数据解析为Java对象
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("获取到星球问题:jsonStr:{}",jsonStr);
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
        // 将回答对象解析为JSON数据
        AnswerReq answerReq = new AnswerReq(new ReqData(text,silenced));
        String paramJson = JSONObject.fromObject(answerReq).toString();
        // 创建请求体，将回答的JSON数据写入
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "utf-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = client.execute(post);
        // 接口响应200 接收数据
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("提问Id:{},回答:{}",topicId,jsonStr);
            // 解析
            AnswerRes res = JSON.parseObject(jsonStr, AnswerRes.class);
            return res.isSucceeded();
        } else {
            throw new RuntimeException("返回错误信息:" + response.getStatusLine().getStatusCode());
        }
    }
}
