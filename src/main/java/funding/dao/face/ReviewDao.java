package funding.dao.face;

import java.util.List;

import funding.dto.Review;
import funding.dto.ReviewFile;
import funding.util.Paging;

public interface ReviewDao {

	/**
	 * 페이징이 적용된 게시글 목록 조회
	 * 
	 * @param paging - 페이징 객체
	 * @return 페이징이 적용된 게시글 리스트
	 */
	public List<Review> selectList(Paging paging);

	/**
	 * 전체 게시글 수 조회
	 * 
	 * @param paramData - search,select 정보를 저장하고 있는 객체
	 * @return 총 게시글 수
	 */
	public int selectCntAll(Paging paramData);

	/**
	 * 조회된 게시글 조회수 증가
	 * 
	 * @param viewReview - 조회된 게시글 번호
	 */
	public void hit(Review viewReview);

	/**
	 * 게시글 조회
	 * 
	 * @param viewReview - 조회하려는 게시글 번호
	 * @return 조회된 게시글 정보
	 */
	public Review select(Review viewReview);

	/**
	 * 게시글 삽입
	 * 
	 * @param review - 삽입할 게시글 정보
	 */
	public void insertReview(Review review);

	/**
	 * 첨부파일 삽입
	 * 
	 * @param reviewFile - 삽입할 첨부파일 정보
	 */
	public void insertFile(ReviewFile reviewFile);

	/**
	 * ReviewNo를 통해 첨부파일 정보 조회
	 * 
	 * @param viewReview - 조회할 게시글 번호
	 * @return 조회된 첨부파일 정보
	 */
	public ReviewFile selectReviewFileByReviewNo(Review viewReview);

	/**
	 * FileNo를 통해 첨부파일 정보 조회
	 * 
	 * @param reviewFile - 조회할 첨부파일 번호
	 * @return 조회된 첨부파일 정보
	 */
	public ReviewFile selectReviewFileByFileNo(ReviewFile reviewFile);

	/**
	 * 게시글 수정
	 * 
	 * @param review - 수정할 게시글 정보
	 */
	public void update(Review review);

	/**
	 * 게시글을 참조하고 있는 모든 첨부파일을 삭제한다
	 * 
	 * @param review - 첨부파일을 삭제할 게시글 번호
	 */
	public void deleteFile(Review review);

	/**
	 * 게시글 삭제
	 * 
	 * @param review - 삭제할 게시글 번호
	 */
	public void delete(Review review);

}