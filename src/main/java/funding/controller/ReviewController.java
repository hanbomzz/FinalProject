package funding.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import funding.dto.Review;
import funding.dto.ReviewComment;
import funding.dto.ReviewFile;
import funding.dto.ReviewRecommend;
import funding.service.face.ReviewService;
import funding.util.Paging;

@Controller
@RequestMapping(value = "/review")
public class ReviewController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	private ReviewService reviewService;

	// 목록 조회
	@RequestMapping(value = "/list")
	public void list(Paging paramData, Model model) {

		// 페이징 계산
		Paging paging = reviewService.getPaging(paramData);
		logger.info("{}", paging);

		List<Review> list = reviewService.list(paging);

		model.addAttribute("paging", paging);
		model.addAttribute("list", list);
	}

	// 게시글 상세보기
	@RequestMapping("/view")
	public String view(Review viewReview, Model model, HttpSession session) {

		// 잘못된 게시글 리다이렉트 처리
		if (viewReview.getReviewNo() < 1) {
			return "redirect:/review/list";
		}

		// 게시글 조회
		viewReview = reviewService.view(viewReview);
		logger.info("게시글view {}", viewReview);

		model.addAttribute("viewReview", viewReview);

		// 첨부파일 정보 조회
		ReviewFile reviewFile = reviewService.getAttachFile(viewReview);
		model.addAttribute("reviewFile", reviewFile);

		// 추천 여부 조회
		ReviewRecommend recommend = new ReviewRecommend();
		recommend.setReviewNo(viewReview.getReviewNo());
		recommend.setId((String) session.getAttribute("id"));

		boolean isRecommend = reviewService.isReviewRecommend(recommend);
		model.addAttribute("isRecommend", isRecommend);

		// 전체 추천 수 조회
		model.addAttribute("cntRecommend", reviewService.getTotalCntReviewRecommend(recommend));

		// 댓글 리스트 조회
		List<ReviewComment> commentList = reviewService.getReviewCommentList(viewReview);
		model.addAttribute("commentList", commentList);

		return "review/view";
	}

	// 게시글 작성
	@GetMapping("/write")
	public void write() {
	}

	// 게시글+파일 작성form 처리
	@PostMapping("/write")
	public String writeProcess(Review review, MultipartFile file, HttpSession session) {

		review.setWriterId((String) session.getAttribute("id"));
		review.setWriterNick((String) session.getAttribute("nick"));
		logger.info("{}", review);

		reviewService.write(review, file);

		return "redirect:/review/list";
	}

	// 첨부파일 다운로드
	@RequestMapping("/download")
	public String download(ReviewFile reviewFile, Model model) {

		reviewFile = reviewService.getFile(reviewFile);
		model.addAttribute("downFile", reviewFile);

		return "down";
	}

	// 게시글 수정
	@GetMapping("/update")
	public String update(Review review, Model model) {

		// 잘못된 게시글 번호 처리
		if (review.getReviewNo() < 1) {
			return "redirect:/review/list";
		}

		// 수정할 게시글의 상세보기
		review = reviewService.view(review);
		model.addAttribute("updateReview", review);

		// 게시글의 첨부파일 정보
		ReviewFile reviewFile = reviewService.getAttachFile(review);
		model.addAttribute("reviewFile", reviewFile);

		return "review/update";
	}

	// 게시글+파일 수정form 처리
	@PostMapping("/update")
	public String updateProcess(Review review, MultipartFile file) {

		reviewService.update(review, file);

		return "redirect:/review/view?reviewNo=" + review.getReviewNo();
	}

	// 게시글 삭제
	@RequestMapping("/delete")
	public String delete(Review review) {

		reviewService.delete(review);

		return "redirect:/review/list";
	}

	// 추천 상태 관리
	@RequestMapping(value = "/recommend")
	public ModelAndView recommend(ReviewRecommend recommend, ModelAndView mav, HttpSession session) {

		// 추천 정보 토글
		recommend.setId((String) session.getAttribute("id"));
		boolean result = reviewService.reviewRecommend(recommend);

		// 추천 수 조회
		int cnt = reviewService.getTotalCntReviewRecommend(recommend);

		mav.addObject("result", result); // 추천 했으면 false, 안했으면 true
		mav.addObject("cnt", cnt);
		mav.setViewName("jsonView"); // ajax요청에서 json형식으로 리턴받기 위함

		return mav;
	}

}
