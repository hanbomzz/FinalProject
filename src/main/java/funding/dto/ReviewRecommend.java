package funding.dto;

public class ReviewRecommend {

	private String id;
	private int reviewNo;

	@Override
	public String toString() {
		return "ReviewRecommend [id=" + id + ", reviewNo=" + reviewNo + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

}
