package funding.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ReviewInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(ReviewInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info(" 리뷰 게시판 인터셉터 시작");

		HttpSession session = request.getSession();

		if (null == session.getAttribute("memberNo")) {
			logger.info(" 게시글 작성 불가 - 비 로그인 상태");

			response.sendRedirect("/member/login");

			return false;
		} else {
			logger.info(" 로그인 상태");

			if ((int) session.getAttribute("grade") != 0) {
				logger.info(" 게시글 작성 불가 - 일반 회원이 아님");

				response.sendRedirect("/member/main");

				return false;
			}

		}

		logger.info(" 게시글 작성 가능 - 일반 회원");

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		logger.info(" 리뷰 게시판 인터셉터 종료");

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

	}

}
