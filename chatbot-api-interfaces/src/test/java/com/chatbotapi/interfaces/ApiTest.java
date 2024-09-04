package com.chatbotapi.interfaces;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * 单元测试
 */
public class ApiTest {

    /**
     * 获取提问
     * @throws IOException
     */
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/51111544522284/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie","sensorsdata2015jssdkcross={\"distinct_id\":\"19135ead97758-0c0a64fece585a8-f1d0278-1638720-19135ead97811fc\",\"first_id\":\"\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkxMzVlYWQ5Nzc1OC0wYzBhNjRmZWNlNTg1YTgtZjFkMDI3OC0xNjM4NzIwLTE5MTM1ZWFkOTc4MTFmYyJ9\",\"history_login_id\":{\"name\":\"\",\"value\":\"\"},\"$device_id\":\"19135ead97758-0c0a64fece585a8-f1d0278-1638720-19135ead97811fc\"}; abtest_env=product; zsxqsessionid=986852050f0232ec316021cec8db1555; tfstk=flJIgy_iYy4Io0otVznN1GUkhl6S0UMqdusJm3eU29BLyuQ6YJzPT3QWPF7MUyonZ1L6SFYeY3JWs8tDqpohUDXHxTX-uqkVFHxhE_BSrYvQXGIeXyQRyBBBYGB-uqkwHa2dNTdziLkUChQGVgUReUeTXNjdvTQdwPFODNBRyUCLBlIVDWULean6XNjReahXCAsZ4wtIXnZzy-2oyH7_e-ZcA6IZnNe8TdIBvZKKM8eJCM1pUVJj1XCewnfDLnH7I9-X6OI2xvw1HHIX8N8xHR69bhLPiC0u31Y6Fsvd9unOWpT6K9LEw-6Bwev5tBZomhBpSIbc9f0GfpJVNZf_W2KwfwCXNeugewRXXF12QPyVEFAJygTTRg873ZG3K791b8s1uci_Z7Xj2ojL4Gqxg6IGbrosfy6O9Gj1uci_Z7fdjGoqfcaCB; zsxq_access_token=68FD39B8-92FF-B372-94F1-5BC297EB3F85_E22F7051EBEF9162");
        get.addHeader("Content-Type","application/json; charset=UTF-8");
        CloseableHttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    /**
     * 回答提问
     * @throws IOException
     */
    @Test
    public void answer() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/2855142518524581/answer");
        post.addHeader("cookie","sensorsdata2015jssdkcross={\"distinct_id\":\"19135ead97758-0c0a64fece585a8-f1d0278-1638720-19135ead97811fc\",\"first_id\":\"\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkxMzVlYWQ5Nzc1OC0wYzBhNjRmZWNlNTg1YTgtZjFkMDI3OC0xNjM4NzIwLTE5MTM1ZWFkOTc4MTFmYyJ9\",\"history_login_id\":{\"name\":\"\",\"value\":\"\"},\"$device_id\":\"19135ead97758-0c0a64fece585a8-f1d0278-1638720-19135ead97811fc\"}; abtest_env=product; zsxqsessionid=986852050f0232ec316021cec8db1555; tfstk=flJIgy_iYy4Io0otVznN1GUkhl6S0UMqdusJm3eU29BLyuQ6YJzPT3QWPF7MUyonZ1L6SFYeY3JWs8tDqpohUDXHxTX-uqkVFHxhE_BSrYvQXGIeXyQRyBBBYGB-uqkwHa2dNTdziLkUChQGVgUReUeTXNjdvTQdwPFODNBRyUCLBlIVDWULean6XNjReahXCAsZ4wtIXnZzy-2oyH7_e-ZcA6IZnNe8TdIBvZKKM8eJCM1pUVJj1XCewnfDLnH7I9-X6OI2xvw1HHIX8N8xHR69bhLPiC0u31Y6Fsvd9unOWpT6K9LEw-6Bwev5tBZomhBpSIbc9f0GfpJVNZf_W2KwfwCXNeugewRXXF12QPyVEFAJygTTRg873ZG3K791b8s1uci_Z7Xj2ojL4Gqxg6IGbrosfy6O9Gj1uci_Z7fdjGoqfcaCB; zsxq_access_token=68FD39B8-92FF-B372-94F1-5BC297EB3F85_E22F7051EBEF9162");
        post.addHeader("Content-Type","application/json; charset=UTF-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "utf-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    /**
     * GPT
     * @throws IOException
     */
    @Test
    public void chatGPT() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.chatanywhere.tech/v1/chat/completions");
        post.addHeader("Authorization","Bearer sk-MgKjmMFB19YkKcE2AHi4o0oBqqkZdDT1DpJ9eD5egZM7ysOS");
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
                "        \"content\": \"背诵静夜思\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "utf-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

}
