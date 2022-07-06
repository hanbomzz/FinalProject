package funding.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import funding.dto.ReviewComment;
import funding.service.face.ReviewService;

@Controller
@RequestMapping(value = "/rcomment")
public class ReviewCommentController {

	@Autowired
	private ReviewService reviewService;

	// 댓글 작성
	@RequestMapping(value = "/insert")
	public String insert(ReviewComment comment, Model model, HttpSession session) {

		comment.setId((String) session.getAttribute("id"));
		reviewService.insertReviewComment(comment);

		return "redirect:/review/view?reviewNo=" + comment.getReviewNo();
	}

	// 댓글 삭제
	@RequestMapping(value = "/delete")
	public void delete(ReviewComment comment, Writer writer, Model model) {

		boolean success = reviewService.deleteReviewComment(comment);

		try {
			writer.append("{ 댓글 삭제 성공:" + success + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
