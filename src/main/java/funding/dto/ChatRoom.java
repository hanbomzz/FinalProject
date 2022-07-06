package funding.dto;

public class ChatRoom {

	private int chatroomId;
	private int projectNo;

	@Override
	public String toString() {
		return "ChatRoom [chatroomId=" + chatroomId + ", projectNo=" + projectNo + "]";
	}

	public int getChatroomId() {
		return chatroomId;
	}

	public void setChatroomId(int chatroomId) {
		this.chatroomId = chatroomId;
	}

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

}
