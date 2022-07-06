package funding.dto;

import java.util.Date;

public class Review {
	private int reviewNo;
	private String reviewTitle;
	private String reviewContent;
	private String writerId;
	private String writerNick;
	private int hit;
	private Date writeDate;

	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent
				+ ", writerId=" + writerId + ", writerNick=" + writerNick + ", hit=" + hit + ", writeDate=" + writeDate
				+ "]";
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getWriterNick() {
		return writerNick;
	}

	public void setWriterNick(String writerNick) {
		this.writerNick = writerNick;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

}
