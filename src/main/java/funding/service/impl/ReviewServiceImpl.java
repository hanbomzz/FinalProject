package funding.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import funding.dao.face.ReviewCommentDao;
import funding.dao.face.ReviewDao;
import funding.dao.face.ReviewRecommendDao;
import funding.dto.Review;
import funding.dto.ReviewComment;
import funding.dto.ReviewFile;
import funding.dto.ReviewRecommend;
import funding.service.face.ReviewService;
import funding.util.Paging;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private ServletContext context;
	@Autowired
	private ReviewRecommendDao reviewRecommendDao;
	@Autowired
	private ReviewCommentDao reviewCommentDao;

	@Override
	public List<Review> list(Paging paging) {

		return reviewDao.selectList(paging);
	}

	@Override
	public Paging getPaging(Paging paramData) {

		// 총 게시글 수 반환
		int totalCount = reviewDao.selectCntAll(paramData);

		// 페이징 계산
		Paging paging = new Paging(totalCount, paramData.getCurPage());

		// 검색 정보 가져와서 넣음
		paging.setSelect(paramData.getSelect());
		paging.setSearch(paramData.getSearch());

		return paging;
	}

	@Override
	public Review view(Review viewReview) {

		// 조회수 증가
		reviewDao.hit(viewReview);

		// 상세보기 조회 결과 반환
		return reviewDao.select(viewReview);
	}

	@Override
	@Transactional
	public void write(Review review, MultipartFile file) {

		// 게시글만 삽입
		// --------------------------------------------------
		// 게시글 제목이 없을때 처리
		if ("".equals(review.getReviewTitle())) {
			review.setReviewTitle("(제목없음)");
		}
		reviewDao.insertReview(review);
		// --------------------------------------------------

		// 게시글+첨부파일 삽입
		// --------------------------------------------------
		// 빈 파일 처리
		if (file.getSize() <= 0) {
			return;
		}

		// 파일이 저장될 경로
		String storedPath = context.getRealPath("upload");
		File storedFolder = new File(storedPath);
		if (!storedFolder.exists()) {
			storedFolder.mkdir();
		}

		// 파일이 저장될 이름
		String originName = file.getOriginalFilename(); // 업로드한 파일의 원본 이름
		String storedName = originName + UUID.randomUUID().toString().split("-")[4];

		// 저장될 파일 정보 객체
		File dest = new File(storedFolder, storedName);

		try {
			file.transferTo(dest); // 임시 업로드된 파일을 실제 파일로 저장(stored_name을 지정하여 저장)
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// -----------------------------------------------------

		// 파일 정보 객체 삽입
		ReviewFile reviewFile = new ReviewFile();
		reviewFile.setReviewNo(review.getReviewNo());
		reviewFile.setOriginName(originName);
		reviewFile.setStoredName(storedName);

		reviewDao.insertFile(reviewFile);

	}

	@Override
	public ReviewFile getAttachFile(Review viewReview) {
		return reviewDao.selectReviewFileByReviewNo(viewReview);
	}

	@Override
	public ReviewFile getFile(ReviewFile reviewFile) {
		return reviewDao.selectReviewFileByFileNo(reviewFile);
	}

	@Override
	public void update(Review review) {

		// 게시글 제목이 없을때 처리
		if ("".equals(review.getReviewTitle())) {
			review.setReviewTitle("(제목없음)");
		}
		reviewDao.update(review);

	}

	@Override
	@Transactional
	public void update(Review review, MultipartFile file) {

		// 게시글 수정
		reviewDao.update(review);

		// 빈 파일 처리
		if (file.getSize() <= 0) {
			return;
		}

		// 파일이 저장될 경로
		String storedPath = context.getRealPath("upload");
		File storedFolder = new File(storedPath);
		if (!storedFolder.exists()) {
			storedFolder.mkdir();
		}

		// 파일이 저장될 이름
		String originName = file.getOriginalFilename();
		String storedName = originName + UUID.randomUUID().toString().split("-")[4];

		// 저장될 파일 정보 객체
		File dest = new File(storedFolder, storedName);

		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 수정된 파일 객체 삽입
		ReviewFile reviewFile = new ReviewFile();
		reviewFile.setReviewNo(review.getReviewNo());
		reviewFile.setOriginName(originName);
		reviewFile.setStoredName(storedName);

		// 기존 파일 삭제+새로운 파일 업로드
		reviewDao.deleteFile(review);
		reviewDao.insertFile(reviewFile);

	}

	@Override
	@Transactional
	public void delete(Review review) {

		// 기존 게시글+첨부파일 삭제
		reviewDao.deleteFile(review);
		reviewDao.delete(review);

	}

	@Override
	public boolean isReviewRecommend(ReviewRecommend reviewRecommend) {
		int cnt = reviewRecommendDao.selectCntRecommend(reviewRecommend);

		if (cnt > 0) { // 추천 했음
			return true;

		} else { // 추천 안했음
			return false;

		}
	}

	@Override
	public boolean reviewRecommend(ReviewRecommend reviewRecommend) {
		if (isReviewRecommend(reviewRecommend)) { // 추천 했음
			reviewRecommendDao.deleteRecommend(reviewRecommend);

			return false;

		} else { // 추천 안했음
			reviewRecommendDao.insertRecommend(reviewRecommend);

			return true;
		}
	}

	@Override
	public int getTotalCntReviewRecommend(ReviewRecommend reviewRecommend) {
		return reviewRecommendDao.selectTotalCntRecommend(reviewRecommend);
	}

	@Override
	public void insertReviewComment(ReviewComment reviewComment) {
		reviewCommentDao.insertReviewComment(reviewComment);
	}

	@Override
	public List<ReviewComment> getReviewCommentList(Review review) {
		return reviewCommentDao.selectReviewComment(review);
	}

	@Override
	public boolean deleteReviewComment(ReviewComment reviewComment) {
		reviewCommentDao.deleteReviewComment(reviewComment);

		if (reviewCommentDao.countReviewComment(reviewComment) > 0) {
			return false;
		} else {
			return true;
		}
	}

}
