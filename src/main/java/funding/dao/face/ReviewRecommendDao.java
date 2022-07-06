package funding.dao.face;

import funding.dto.ReviewRecommend;

public interface ReviewRecommendDao {

	/**
	 * 로그인된 회원이 해당 게시글을 추천한 적이 있는지 조회
	 * 
	 * @param reviewRecommend - 로그인된 회원과 게시글 정보를 가지고 있는 객체
	 * @return 1 - 이미 추천함, 0 - 아직 추천안함
	 */
	public int selectCntRecommend(ReviewRecommend reviewRecommend);

	/**
	 * 추천상태 삽입
	 * 
	 * @param reviewRecommend - 추천 정보 객체
	 */
	public void insertRecommend(ReviewRecommend reviewRecommend);

	/**
	 * 추천상태 삭제
	 * 
	 * @param reviewRecommend - 추천 정보 객체
	 */
	public void deleteRecommend(ReviewRecommend reviewRecommend);

	/**
	 * 게시글의 전체 추천 수 조회
	 * 
	 * @param reviewRecommend - 추천 수를 조회할 게시글 정보
	 * @return 총 추천 수
	 */
	public int selectTotalCntRecommend(ReviewRecommend reviewRecommend);

}
