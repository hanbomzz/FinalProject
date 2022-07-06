package funding.dao.face;

import java.util.List;

import funding.dto.Review;
import funding.dto.ReviewComment;

public interface ReviewCommentDao {

	/**
	 * 댓글 목록 조회
	 * 
	 * @param review - 조회할 게시글
	 * @return 댓글 리스트
	 */
	public List<ReviewComment> selectReviewComment(Review review);

	/**
	 * 댓글 삽입
	 * 
	 * @param reviewComment - 작성할 댓글 정보
	 */
	public void insertReviewComment(ReviewComment reviewComment);

	/**
	 * 댓글 삭제
	 * 
	 * @param reviewComment - 삭제할 댓글 정보
	 */
	public void deleteReviewComment(ReviewComment reviewComment);

	/**
	 * 게시글에 작성된 댓글 개수 구함
	 * 
	 * @param reviewComment - 게시글에 작성된 댓글 개수
	 * @return 댓글 개수
	 */
	public int countReviewComment(ReviewComment reviewComment);

}
