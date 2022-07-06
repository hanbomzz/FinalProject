package funding.dto;

public class ReviewFile {
	private int reviewFileNo;
	private int reviewNo;
	private String originName;
	private String storedName;

	@Override
	public String toString() {
		return "ReviewFile [reviewFileNo=" + reviewFileNo + ", reviewNo=" + reviewNo + ", originName=" + originName
				+ ", storedName=" + storedName + "]";
	}

	public int getReviewFileNo() {
		return reviewFileNo;
	}

	public void setReviewFileNo(int reviewFileNo) {
		this.reviewFileNo = reviewFileNo;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

}
