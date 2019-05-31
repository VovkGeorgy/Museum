package by.home.museum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class WebSocketController {
    private static final String SENDING_URL = "/topic/front-receiver";
    private static final String RECEIVING_URL = "/server-receiver";

    private final SimpMessagingTemplate template;

    private AtomicLong counter = new AtomicLong(0);

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping(RECEIVING_URL)
    public void onReceivedMessage(String message) {
        System.out.println("New message received : " + message);
        sendMessage();
    }

    @SubscribeMapping(SENDING_URL)
    public String onSubscribe() {
        return "Backend carefully listens to you.";
    }

    private void sendMessage() {
        template.convertAndSend(SENDING_URL, buildNextMessage());
    }

    private String buildNextMessage() {
        String message = "Message from back - " + counter.getAndIncrement();
        System.out.println("Response to front - " + message);
        return message;
    }
}
