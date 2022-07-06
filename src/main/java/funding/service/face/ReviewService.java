package funding.service.face;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import funding.dto.Review;
import funding.dto.ReviewComment;
import funding.dto.ReviewFile;
import funding.dto.ReviewRecommend;
import funding.util.Paging;

public interface ReviewService {

	/**
	 * 페이징이 적용된 게시글 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 페이징이 적용된 게시글 리스트
	 */
	public List<Review> list(Paging paging);

	/**
	 * 게시글 목록을 위한 페이징 객체 생성함 curPage, totalCount 두 값으로 페이징 계산
	 * 
	 * @param paramData - curPage,search,select 정보를 저장하고 있는 객체
	 * @return 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(Paging paramData);

	/**
	 * 게시글 상세보기
	 * 
	 * @param viewReview - 상세 조회할 게시글 번호
	 * @return 조회된 상세 게시글 정보
	 */
	public Review view(Review viewReview);

	/**
	 * 게시글+첨부파일 삽입
	 * 
	 * @param review - 게시글 정보 DTO
	 * @param file   - 첨부파일 정보 DTO
	 */
	public void write(Review review, MultipartFile file);

	/**
	 * 게시글 번호를 통해 업로드된 파일 정보 조회
	 * 
	 * @param viewReview - 조회할 게시글 번호가 담겨있는 객체
	 * @return 첨부파일 정보
	 */
	public ReviewFile getAttachFile(Review viewReview);

	/**
	 * 파일 번호를 통해 업로드된 파일 정보 조회
	 * 
	 * @param reviewFile - 조회할 파일 번호가 담겨있는 객체
	 * @return 첨부파일 정보
	 */
	public ReviewFile getFile(ReviewFile reviewFile);

	/**
	 * 게시글 수정
	 * 
	 * @param review - 게시글 정보 객체
	 */
	public void update(Review review);

	/**
	 * 게시글+첨부파일 수정
	 * 
	 * @param review - 게시글 정보 객체
	 * @param file   - 파일업로드 객체
	 */
	public void update(Review review, MultipartFile file);

	/**
	 * 게시글+첨부파일 삭제
	 * 
	 * @param review - 삭제할 게시글의 글번호
	 */
	public void delete(Review review);

	/**
	 * 추천 상태확인
	 * 
	 * @param reviewRecommend - 추천 상태를 확인할 게시글과 정보
	 * @return true - 추천함, false - 추천안함
	 */
	public boolean isReviewRecommend(ReviewRecommend reviewRecommend);

	/**
	 * 추천 상태를 확인하고 추천을 토글 작업
	 * 
	 * @param reviewRecommend - 추천 대상 정보
	 * @return true - 추천함, false - 추천 취소함
	 */
	public boolean reviewRecommend(ReviewRecommend reviewRecommend);

	/**
	 * 총 추천 수를 구함
	 * 
	 * @param reviewRecommend - 추천수를 파악할 게시글 정보
	 * @return 총 추천 수
	 */
	public int getTotalCntReviewRecommend(ReviewRecommend reviewRecommend);

	/**
	 * 댓글 삽입
	 * 
	 * @param reviewComment - 입력된 댓글 정보
	 */
	public void insertReviewComment(ReviewComment reviewComment);

	/**
	 * 댓글 목록 조회
	 * 
	 * @param review - 댓글을 조회할 게시글 정보
	 * @return 조회된 댓글 목록
	 */
	public List<ReviewComment> getReviewCommentList(Review review);

	/**
	 * 댓글 삭제
	 * 
	 * @param reviewComment - 삭제하려는 댓글의 정보
	 * @return 삭제 성공 여부
	 */
	public boolean deleteReviewComment(ReviewComment reviewComment);

}
