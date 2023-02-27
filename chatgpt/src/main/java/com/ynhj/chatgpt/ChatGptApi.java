package com.ynhj.chatgpt;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @date: 2023/2/27
 * @author: yangniuhaojiang
 * @title: ChatGptApi
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class ChatGptApi {
    private static final Logger LOGGER = LogManager.getLogger(ChatGptApi.class);

    private ChatGptApi() {
    }

    private static ChatGptApi chatGptApi = new ChatGptApi();

    public static ChatGptApi getInstance() {
        return chatGptApi;
    }

    public String chat(String question) throws IOException {
        LOGGER.info("question : {}", question);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).
                readTimeout(2, TimeUnit.MINUTES).build();
        MediaType mediaType = MediaType.parse("application/json");
        String value = "{\"question\":\"" + question + "\",\"max_response_time\":20}";
        RequestBody body = RequestBody.create(mediaType, value);
        Request request = new Request.Builder()
                .url("https://you-chat-gpt.p.rapidapi.com/")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("X-RapidAPI-Key", "you-token")
                .addHeader("X-RapidAPI-Host", "you-chat-gpt.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        ResponseEntity responseEntity = JSONObject.parseObject(responseBody, ResponseEntity.class);
        if (!responseEntity.warning.isEmpty()) {
            LOGGER.info("WARNING : {}", responseEntity.warning);
        }
        return responseEntity.answer;
    }

    private static class ResponseEntity {
        String answer;
        Double response_elapsed_time;
        String warning;

        public ResponseEntity() {
        }

        public ResponseEntity(String answer, Double response_elapsed_time, String warning) {
            this.answer = answer;
            this.response_elapsed_time = response_elapsed_time;
            this.warning = warning;
        }

        /**
         * @return the String
         * @author: yangniuhaojiang
         * @title: getAnswer
         * @description: update_version: update_date: update_author: update_note:
         */
        public String getAnswer() {
            return answer;
        }

        /**
         * @param answer the String to set
         * @author: yangniuhaojiang
         * @title: setAnswer
         * @description: update_version: update_date: update_author: update_note:
         */
        public void setAnswer(String answer) {
            this.answer = answer;
        }

        /**
         * @return the Double
         * @author: yangniuhaojiang
         * @title: getResponse_elapsed_time
         * @description: update_version: update_date: update_author: update_note:
         */
        public Double getResponse_elapsed_time() {
            return response_elapsed_time;
        }

        /**
         * @param response_elapsed_time the Double to set
         * @author: yangniuhaojiang
         * @title: setResponse_elapsed_time
         * @description: update_version: update_date: update_author: update_note:
         */
        public void setResponse_elapsed_time(Double response_elapsed_time) {
            this.response_elapsed_time = response_elapsed_time;
        }

        /**
         * @return the String
         * @author: yangniuhaojiang
         * @title: getWarning
         * @description: update_version: update_date: update_author: update_note:
         */
        public String getWarning() {
            return warning;
        }

        /**
         * @param warning the String to set
         * @author: yangniuhaojiang
         * @title: setWarning
         * @description: update_version: update_date: update_author: update_note:
         */
        public void setWarning(String warning) {
            this.warning = warning;
        }
    }
}
