package funding.dao.face;

import org.springframework.stereotype.Repository;

import funding.dto.ChatRoom;
import funding.dto.Project;

@Repository
public interface ChatDao {

	/**
	 * 신규 프로젝트 채팅방 생성
	 * 
	 * 
	 * @param projectNo - 신규 프로젝트 번호
	 * @return
	 */
	public ChatRoom insertChatRoom(int projectNo);

	/**
	 * roomId를 이용해 채팅방 조회
	 * 
	 * 
	 * @param projectNo - 조회하려는 프로젝트 번호
	 * @return
	 */
	public ChatRoom selectChatRoomByroomId(int projectNo);

	/**
	 * 채팅방 프로젝트 제목 조회
	 * 
	 * 
	 * @param project - 조회하려는 프로젝트 번호
	 * @return
	 */
	public Project selectProjectTitle(int projectNo);

}
