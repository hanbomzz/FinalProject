package funding.service.face;

import funding.dto.ChatRoom;
import funding.dto.Project;

public interface ChatService {

	/**
	 * 채팅방 생성
	 * 
	 * @param projectNo - 생성하고자 하는 프로젝트넘버
	 */
	public ChatRoom createChatRoom(int projectNo);

	/**
	 * roomId 이용해서 채팅방 찾기
	 * 
	 * @param projectNo - 찾고자 하는 채팅방의 프로젝트넘버
	 */
	public ChatRoom findRoomById(int projectNo);

	/**
	 * 채팅방 프로젝트 제목 조회
	 * 
	 * @param projectNo - 해당 채팅방 프로젝트넘버
	 */
	public Project getProjectTitle(int projectNo);

}
