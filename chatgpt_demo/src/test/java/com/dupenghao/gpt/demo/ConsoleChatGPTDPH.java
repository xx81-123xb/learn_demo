package com.dupenghao.gpt.demo;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.ConsoleStreamListener;
import com.plexpt.chatgpt.util.Proxys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


/**
 * open ai 客户端
 *
 * @author plexpt
 */

@Slf4j

public class ConsoleChatGPTDPH {

    public static Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));

    public static void main(String[] args) {

        String key = "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";

        List<String> history = new ArrayList<>();
        while (true) {
            String prompt = getInput("\nYou:\n");

            ChatGPTStream chatGPT = ChatGPTStream.builder()
                    .apiKey(key)
                    .proxy(proxy)
                    .build()
                    .init();

            //卡住
            CountDownLatch countDownLatch = new CountDownLatch(1);

            if(history.size()!=0){
                prompt = history.get(history.size()-1);
            }
            Message message = Message.of(prompt);
            ConsoleStreamListener listener = new ConsoleStreamListener() {

                @Override
                public void onMsg(String message) {
                    super.onMsg(message);
                    history.clear();
                    history.add(message);
                }

                @Override
                public void onError(Throwable throwable, String response) {
                    throwable.printStackTrace();
                    countDownLatch.countDown();
                }
            };

            listener.setOnComplate(msg -> {
                countDownLatch.countDown();
            });
            chatGPT.streamChatCompletion(Arrays.asList(message), listener);

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    private static BigDecimal getBalance(String key) {

        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(key)
                .proxy(proxy)
                .build()
                .init();

        return chatGPT.balance();
    }

    private static void check(String key) {
        if (key == null || key.isEmpty()) {
            throw new RuntimeException("请输入正确的KEY");
        }
    }

    @SneakyThrows
    public static String getInput(String prompt) {
        System.out.print(prompt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = new ArrayList<>();
        String line;
        try {
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.stream().collect(Collectors.joining("\n"));
    }

}

