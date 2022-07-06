<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">

.btnLUD {
    border-radius: 5px;
    border: 0px solid black;
    background-color: #39AEA9;
    color: white;
    width: 70px;
    height: 32px;
    font-weight: bold;
    
}


#btnCommInsert {
	 border-radius: 5px;
	 color: white;
	 border: 0px solid black;
	 background-color: #cfcdcd;
	 width: 75px;
   	 height: 100px;
     font-weight: bold;
	float: right;
	 padding: 8px 20px;

}

.form-control {
	float: left;
    width: 700px;
    height: 100px;
    padding: 8px 10px;
    font-size: 16px;
    text-align: left;
 /*    margin-right: 200px; */
}

#btnRecommend {
	float: left;
	margin-right: 285px;
}


</style>

<script type="text/javascript">
$(document).ready(function() {
	$("#btnList").click(function() {
		location.href = "/review/list"
	})
	
	$("#btnUpdate").click(function() {
		location.href = "/review/update?reviewNo=${viewReview.reviewNo}"
	})
	
	$("#btnDelete").click(function() {
		location.href = "/review/delete?reviewNo=${viewReview.reviewNo}"
	})
})
</script>

<script type="text/javascript">
$(document).ready(function() {
	if(${isRecommend}) { //true면 추천 이미 한 상태임
		$("#btnRecommend")
			.addClass("btnc")
			.css('background-color', '#A2D5AB')
			.css('color', 'white')
			.css('width', '30%')
			.html('추천 취소');
	} else { //false면 추천 안한 상태
		$("#btnRecommend")
			.addClass("btns")
			.css('background-color', '#39AEA9')
			.css('color', 'white')
			.css('width', '20%')
			.html('추천');
	}
	
	$("#btnRecommend").click(function() {
		
		$.ajax({
			type: "get"
			, url: "/review/recommend"
			, data: { "reviewNo": '${viewReview.reviewNo }' }
			, dataType: "json"
			, success: function( data ) {
					console.log("성공");
	
				if( data.result ) { //추천
					$("#btnRecommend")
					.removeClass("btns")
					.addClass("btnc")
					.css('background-color', '#A2D5AB')
					.css('color', 'white')
					.css('width', '30%')
					.html('추천 취소');
				
				} else { //추천 취소
					$("#btnRecommend")
					.removeClass("btnc")
					.addClass("btns")
					.css('background-color', '#39AEA9')
					.css('color', 'white')
					.css('width', '20%')
					.html('추천');
				
				}
				
				//총 추천 수
				$("#recommend").html('추천 ' + data.cnt);
			}
			, error: function() {
				console.log("추천 실패");
			}
		}); 
		
	});
});
</script>

<script type="text/javascript">
$(document).ready(function() {
	// 댓글 입력
	$("#btnCommInsert").click(function() {
		
		$form = $("<form>").attr({
			action: "/rcomment/insert",
			method: "post"
		}).append(
			$("<input>").attr({
				type:"hidden",
				name:"reviewNo",
				value:"${viewReview.reviewNo }"
			})
		).append(
			$("<textarea>")
				.attr("name", "content")
				.css("display", "none")
				.text($("#commentContent").val())
		);
		$(document.body).append($form);
		$form.submit();
		
	}); 
});

function deleteComment(commentNo) {
	$.ajax({
		type: "post"
		, url: "/rcomment/delete"
		, dataType: "json"
		, data: {
			commentNo: commentNo
		}
		, success: function(data){
			if(data.success) {
				
				$("[data-commentno='"+commentNo+"']").remove();
				
			} else {
				alert("댓글 삭제 실패");
			}
		}
		, error: function() {
			console.log("댓글 삭제 실패");
		}
	});
}
</script>




<div class="container">
<br>

<!-- 추천 버튼 -->
<div style="float: right;">
<button id="btnRecommend" class="btnLUD">추천</button>
</div>
<!-- 추천 버튼 -->

<div class="clearfix"></div>
<br>

<!-- 게시글 상세보기 창 -->
<div id="review"  style="border: 1px solid #ccc; border-radius: 10px; margin-bottom: 30px; width: 60%; margin-left: 20%; margin-right: 20%;">
<div style="margin-left: 20px; margin-bottom: 20px;"><h2 style="font-weight: bold; margin-top: 15px;">${viewReview.reviewTitle }</h2></div>
<div style="float: left; margin-left: 20px; margin-bottom: 10px; color: #9e9e9e; font-size:10pt; ">${viewReview.writerNick }</div>
<div style="float: right;  margin-bottom: 20px; margin-left: 20px; margin-right: 20px; color: #9e9e9e; font-size:10pt;" >조회 ${viewReview.hit }</div>
<div style="float: right;  margin-bottom: 20px; margin-left: 20px; margin-right: 20px; color: #9e9e9e; font-size:10pt;" id="recommend" >추천 ${cntRecommend }</div>
<div style="float: left;  margin-bottom: 10px; margin-left: 25px; color: #9e9e9e; font-size:10pt;" ><fmt:formatDate value="${viewReview.writeDate }" pattern="yy-MM-dd HH:mm:ss"/></div>
<hr style="border: 1px solid #ccc; width: 100%">
<div style="clear: both; min-height: 400px; margin-left: 20px; color:#878585">${viewReview.reviewContent }</div>
<div style="clear: both; min-height: 50px; margin-left: 20px;"><a href="/review/download?reviewFileNo=${reviewFile.reviewFileNo }">${reviewFile.originName }</a></div>
</div>
<!-- 게시글 상세보기 창 -->

<!-- 목록,수정,삭제 버튼 -->
<div class="text-center">
	<button id="btnList"  class="btnLUD">목록</button>
	<c:if test="${id eq viewreview.writerId }">
		<button id="btnUpdate"  class="btnLUD">수정</button>
		<button id="btnDelete"  class="btnLUD">삭제</button>
	</c:if>
</div>
<!-- 목록,수정,삭제 버튼 -->

<br><br><br>
<div>
	<!-- 댓글 목록 -->
	<div id="comments"  style="border: 1px solid #ccc; border-radius: 10px; margin-bottom: 30px; width: 60%; margin-left: 20%; margin-right: 20%;">
	<c:forEach items="${commentList }" var="comment">	
	<div style="margin-left: 20px; margin-bottom: 20px;">${comment.commentContent }</div>
<div style="float: left; margin-left: 20px; margin-bottom: 10px; color: #9e9e9e; font-size:10pt; ">${comment.id }</div>
<div style="float: left;  margin-bottom: 10px; margin-left: 25px; color: #9e9e9e; font-size:10pt;" ><fmt:formatDate value="${comment.writeDate }" pattern="yy-MM-dd HH:mm:ss"/></div>
<c:if test="${sessionScope.id eq comment.id }">
			<button class="btn btn-default btn-xs"
				onclick="deleteComment(${comment.commentNo });" style="color: #9e9e9e; font-size:10pt;">삭제</button>
			</c:if>
			</c:forEach>
	<!-- 댓글 목록 -->
			
	<!-- 댓글 작성란,버튼 -->
	<!-- 비로그인 처리 -->
	<c:if test="${not login }">
	<strong>로그인이 필요합니다</strong><br>
	<button onclick='location.href="/member/login";'>로그인</button>
	<button onclick='location.href="/member/join";'>회원가입</button>
	</c:if>
	
	<c:if test="${login }">
	<div class="form-inline text-center">
		<textarea rows="2" cols="60" class="form-control" id="commentContent"></textarea>
		<button id="btnCommInsert" class="btn">등록</button>
	</div>
	</c:if>
	<!-- 댓글 작성란,버튼 -->
	
</div>
</div>	

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />















