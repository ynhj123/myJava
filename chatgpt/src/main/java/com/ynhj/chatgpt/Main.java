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
        List<String> questions = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String question = scanner.nextLine();
            questions.add(question);
            String questionsString = arrayJoin(questions);
            String answer = chatGptApi.chat(questionsString);
            LOGGER.info("chat gpt question:{}, answer:{}", question, answer);
            questions.add(answer);
        }
    }

    private static String arrayJoin(List<String> questions) {
        String[] strings = new String[questions.size()];
        strings = questions.toArray(strings);
        return String.join("", strings).replace("\n", "");
//        StringBuilder stringBuilder = new StringBuilder();
//        questions.forEach(s -> stringBuilder.append(s).append("\n"));
//        return stringBuilder.toString();
    }
}
