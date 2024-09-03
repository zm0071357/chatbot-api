package com.chatbotapi.domain.ai.Service;

import com.alibaba.fastjson.JSON;
import com.chatbotapi.domain.ai.IOpenAi;
import com.chatbotapi.domain.ai.model.aggregates.AIAnswer;
import com.chatbotapi.domain.ai.model.vo.Choices;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OpenAi implements IOpenAi {

    @Value("${chatbot-api.apiKey}")
    private String apiKey;

    Logger logger = LoggerFactory.getLogger(OpenAi.class);

    @Override
    public String doChatGPT(String question) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.chatanywhere.tech/v1/chat/completions");
        post.addHeader("Authorization","Bearer " + apiKey);
        post.addHeader("Content-Type","application/json");
        String paramJson = "{\n" +
                "    \"model\": \"gpt-3.5-turbo\",\n" +
                "    \"messages\": [\n" +
                "      {\n" +
                "        \"role\": \"system\",\n" +
                "        \"content\": \"You are a helpful assistant.\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"role\": \"user\",\n" +
                "        \"content\": \""+ question +"\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "utf-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String JsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(JsonStr, AIAnswer.class);
            Choices choices = aiAnswer.getChoices().get(0);
            String res = choices.getMessage().getContent();
            return res;
        } else {
            throw new RuntimeException("返回错误信息:" + response.getStatusLine().getStatusCode());
        }
    }
}
