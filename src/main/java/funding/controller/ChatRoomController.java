package funding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import funding.dto.ChatRoom;
import funding.dto.Project;
import funding.service.face.ChatService;

@Controller
@RequestMapping("/chat")
public class ChatRoomController {

	private static Logger log = LoggerFactory.getLogger(ChatRoomController.class);

	@Autowired
	private ChatService chatService;

	// 채팅방 생성
	@PostMapping("/room")
	public ChatRoom createRoom(int projectNo) {

		log.info("/chat/room 확인 : {}", projectNo);
		return chatService.createChatRoom(projectNo);
	}

	// 채팅방 입장 화면
	@GetMapping("/room/enter/{projectNo}")
	public String roomDetail(Model model, @PathVariable int projectNo) {

		ChatRoom room = chatService.findRoomById(projectNo);
		Project project = chatService.getProjectTitle(projectNo);
		log.info("결과값 테스트: {}", room);
		model.addAttribute("projectNo", projectNo);
		model.addAttribute("room", room);
		model.addAttribute("project", project);
		return "chat/chattingroom";
	}

	// 채팅방 찾기
	@GetMapping("/room/{projectNo}")
	public ChatRoom roomInfo(@PathVariable int projectNo) {

		log.info("/chat/room/{} 확인", projectNo);
		return chatService.findRoomById(projectNo);
	}
}
