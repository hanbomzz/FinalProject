package funding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import funding.dao.face.ChatDao;
import funding.dto.ChatRoom;
import funding.dto.Project;
import funding.service.face.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

	private static Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

	@Autowired
	private ChatDao chatDao;

	@Override
	public ChatRoom createChatRoom(int projectNo) {

		return chatDao.insertChatRoom(projectNo);
	}

	@Override
	public ChatRoom findRoomById(int projectNo) {
		log.info("조회된 채팅방: {}", chatDao.selectChatRoomByroomId(projectNo));
		return chatDao.selectChatRoomByroomId(projectNo);
	}

	@Override
	public Project getProjectTitle(int projectNo) {

		return chatDao.selectProjectTitle(projectNo);
	}

}
