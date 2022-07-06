package funding.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import lombok.RequiredArgsConstructor;

@EnableWebSocketMessageBroker // STOMP를 사용한다는 설정
@RequiredArgsConstructor // 의존성 주입의 편의성을 위해 사용
@Configuration // 스프링 설정 클래스를 선언
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

	private static Logger log = LoggerFactory.getLogger(StompWebSocketConfig.class);

	// 웹소켓 세션 접속
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry.addEndpoint("/ws/chat").addInterceptors(new HttpSessionHandshakeInterceptor())
				.setAllowedOriginPatterns("http://localhost:8081").withSockJS();

		log.info("웹소켓 엔드포인트 설정");
	}

	// STOMP 메세지 브로커
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {

		// 채팅방을 구독하고 있는 클라이언트에게 전달
		registry.enableSimpleBroker("/topic");
		log.info("subscribe 메세지 전달");

		// @MessageMapping 메소드로 전달
		registry.setApplicationDestinationPrefixes("/app");
		log.info("/app prefix 적용");

	}
}
