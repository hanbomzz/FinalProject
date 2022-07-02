package funding.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import funding.dto.ChatRoom;
import funding.dto.Project;
import funding.repository.ChatRoomRepository;

@Controller
@RequestMapping("/chat")
public class RoomController {

	private static Logger log = LoggerFactory.getLogger(RoomController.class);

	@Autowired
	private ChatRoomRepository repository;

	// 채팅방 생성
	@PostMapping("/room")
	@ResponseBody
	public ChatRoom createRoom(int projectNo) {
		log.info("[/chat/room][POST]");
		log.info("요청파라미터: {}", projectNo);
		return repository.createChatRoom(projectNo);
	}

	// 채팅방 입장 화면
	@GetMapping("/room/enter/{projectNo}")
	public String roomDetail(Model model, Project project, @PathVariable int projectNo, HttpSession session) {

		ChatRoom room = repository.findRoomById(projectNo);
		project = repository.getProject(project);
		System.out.println("결과값 테스트: " + room);
		String sessionId = session.getId();
		model.addAttribute("projectNo", projectNo);
		model.addAttribute("room", room);
		model.addAttribute("sessionId", sessionId);
		model.addAttribute("project", project);
		return "chat/chattingroom";
	}

	// 특정 채팅방 조회
	@GetMapping("/room/{projectNo}")
	@ResponseBody
	public ChatRoom roomInfo(@PathVariable int projectNo) {
		log.info("[/chat/room/{}][GET]", projectNo);
		return repository.findRoomById(projectNo);
	}
}
