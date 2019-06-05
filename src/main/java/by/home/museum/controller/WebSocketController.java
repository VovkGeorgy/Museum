package by.home.museum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
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
    public void onReceivedMessage(@Header("simpSessionId") String sessionId, @Payload String message) {
        String processedMessage = message.replace("\"", "");
        if (processedMessage.equals("test")) {
            counter.set(0L);
        }
        System.out.println("New message received : " + processedMessage + ", from session - " + sessionId);
        template.convertAndSendToUser(sessionId, SENDING_URL, buildNextMessage(), createHeaders(sessionId));
    }

    @SubscribeMapping("/user" + SENDING_URL)
    public String onSubscribe(@Header("simpSessionId") String sessionId) {
        return "Backend carefully listens to you.";
    }

    private String buildNextMessage() {
        String message = "Message from back - " + counter.getAndIncrement();
        System.out.println("Response to front - " + message);
        return message;
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
