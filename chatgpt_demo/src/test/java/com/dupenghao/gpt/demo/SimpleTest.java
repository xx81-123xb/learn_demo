package com.dupenghao.gpt.demo;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatChoice;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.util.Proxys;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * Created by 杜鹏豪 on 2023/8/3.
 */
public class SimpleTest {

    private static final Logger log = LoggerFactory.getLogger(SimpleTest.class);

    private static final String KEY = "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";

    @Test
    public void test() {
        System.out.println("===");
    }

    //    @Test
    public static void main(String[] args) throws IOException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
//        Proxys.http()
        ChatGPT gpt = ChatGPT.builder()
                .apiKey(KEY)
                .proxy(proxy)
                .build()
                .init();

        String res = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String message = "";
        log.info("开始问答:");

        //声明一个固定长度5的循环队列
        LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>(5);

        while (true) {
            message = br.readLine().trim();
            Message user = Message.builder().role("user").content(message).build();
            if (queue.size() == 5) {
                queue.poll();
            }
            queue.offer(user);
            if (message.equals("exit")) {
                break;
            }
            List<Message> messages = queue.stream().collect(Collectors.toList());
            List<ChatChoice> choices = gpt.chatCompletion(messages).getChoices();
            Message response = choices.get(0).getMessage();
            res = response.getContent();
            if (queue.size() == 5) {
                queue.poll();
            }
            queue.offer(response);
            System.out.println(res);
        }
        log.info("结束问答:");

    }

}
