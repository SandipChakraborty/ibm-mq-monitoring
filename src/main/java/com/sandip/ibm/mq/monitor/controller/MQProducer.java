package com.sandip.ibm.mq.monitor.controller;

import com.sandip.ibm.mq.monitor.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq")
public class MQProducer {

    private final MessageService  messageService;

    public MQProducer(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/home")
    public String home() {
        return "Hello World!";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        return messageService.sendMessage(message);
    }

    @PostMapping("/send/failure")
    public ResponseEntity<String> sendFailureMessage(@RequestParam int count) {
        String message = "fail";
        messageService.sendMessages(count, message);
        return ResponseEntity.accepted().body(count + " failure messages sent");
    }

    @PostMapping("/send/messages")
    public ResponseEntity<String> sendMessages(@RequestParam int count) {
        messageService.sendMessages(count);
        return ResponseEntity.accepted().body(count + " messages sent");
    }
}
