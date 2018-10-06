package com.niit.controllers;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.niit.models.Message;
import com.niit.models.OutputMessage;

@RestController
public class ChatController {
 @MessageMapping("/chat")
 @SendTo("/topic/message")
 public OutputMessage sendMessage(Message message) {
	 System.out.println("Message Received");
	 return new OutputMessage(message,new Date());
	 
 }
}