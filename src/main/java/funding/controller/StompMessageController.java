package funding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import funding.dto.ChatMessage;

@Controller
public class StompMessageController {

	private static Logger log = LoggerFactory.getLogger(StompMessageController.class);

	@Autowired
	private SimpMessageSendingOperations sendingOperations; // 특정 브로커로 메세지를 전달

	// 실질적인 경로는 /app/chat/message
	@MessageMapping("/chat/message")
	public void enter(ChatMessage message) {

		log.info("/app/chat/message : {}", message);
		sendingOperations.convertAndSend("/topic/chat/room/" + message.getChatroomId(), message);
		log.info("구독 클라이언트에게 메시지 전달");
	}

}