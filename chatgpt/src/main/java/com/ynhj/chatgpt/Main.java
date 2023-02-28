package com.ynhj.chatgpt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @date: 2023/2/27
 * @author: yangniuhaojiang
 * @title: main
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private final static ChatGptApi chatGptApi = ChatGptApi.getInstance();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<Chat> chats = new ArrayList<>();
        while (scanner.hasNextLine()) {
            Chat chat = new Chat();
            String question = scanner.nextLine();
            chat.question = question;
            String questionsString = arrayJoin(chats, question);
            String answer = chatGptApi.chat(questionsString);

            LOGGER.info("chat gpt question:{}, answer:{}", question, answer);
            if (!answer.isEmpty()) {
                chat.answer = answer;
                chats.add(chat);
            }
        }
    }

    public static class Chat {
        private String question;
        private String answer;


    }

    private static String arrayJoin(List<Chat> chats, String question) {
//        String[] strings = new String[questions.size()];
//        strings = questions.toArray(strings);
//        return String.join("", strings).replace("\n", "");
        StringBuilder stringBuilder = new StringBuilder();
        chats.forEach(s -> {
            stringBuilder.append("Human:").append(s.question).append("\n");
            stringBuilder.append("AI:").append(s.answer);
        });
        stringBuilder.append("Human:").append(question);
        return stringBuilder.toString().replaceAll("\n", "");
    }
}
