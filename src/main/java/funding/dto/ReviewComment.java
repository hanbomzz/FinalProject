package funding.dto;

import java.util.Date;

public class ReviewComment {

	private int rnum;
	private int commentNo;
	private int reviewNo;
	private String id;
	private String commentContent;
	private Date writeDate;

	@Override
	public String toString() {
		return "Comment [rnum=" + rnum + ", commentNo=" + commentNo + ", reviewNo=" + reviewNo + ", id=" + id
				+ ", commentContent=" + commentContent + ", writeDate=" + writeDate + "]";
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

}