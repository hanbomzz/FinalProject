<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	$("#btnWrite").click(function() {
		location.href = "/review/write"
	})
	
})
</script>

<style type="text/css">
table {
	table-layout: fixed;
}

button {
    border-radius: 5px;
    border: 0px solid black;
    background-color: #39AEA9;
    color: white;
    width: 70px;
    height:32px; 
    font-weight: bold;
}

input  {
	
	border-radius: 5px;
	height: 30px;
	width : 300px;
	border: 1px solid #ccc;
	margin-right: 20px;

}

table, th {
	text-align: center;
}

select {
	border-radius: 5px;
	height: 30px;
	width : 80px;
	margin-left: 400px;
	
}

/* td:nth-child(2) {
	text-align: left;
} */

#btnWrite {
background-color: #39AEA9 !important;
}

 a:link { color: black; text-decoration: none;}
 a:visited { color: black; text-decoration: none;}
 a:hover { color: black; text-decoration: none;}

</style>

<div class="container">
<br><br><br>

<!-- 게시글 목록 table -->
<table class="table table-hover">
<thead>
	<tr>
		<th style="width: 10%;">글번호</th>
		<th style="width: 45%;">제목</th>
		<th style="width: 20%;">작성자</th>
		<th style="width: 10%;">조회수</th>
		<th style="width: 15%;">작성일</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${list }" var="review">
	<tr>
		<td>${review.reviewNo }</td>
		<td style="width: 45%;"><a href="/review/view?reviewNo=${review.reviewNo }" >${review.reviewTitle }</a></td>
		<td>${review.writerNick }</td>
		<td>${review.hit }</td>
		<td><fmt:formatDate value="${review.writeDate }" pattern="yy-MM-dd HH:mm:ss"/></td>
	</tr>
</c:forEach>
</tbody>
</table>
<!-- 게시글 목록 table -->

<!-- 글쓰기 버튼 -->
<button id="btnWrite" style="float: left; width: 80px;">글쓰기</button>
<div class="clearfix"></div>
<!-- 글쓰기 버튼 -->

<!-- 검색창 form -->
<div>
<form action="/review/list" method="get">

<div>
	<select name="select" id="select" >
		<option value="writerNick" id="writerNick" style="display: none;" <c:if test="${select eq 'writerNick' }">selected</c:if>>작성자</option>
		<option value="reviewTitle" id="reviewTitle" <c:if test="${select eq 'reviewTitle' }">selected</c:if>>제목</option>
		<option value="reviewContent" id="reviewContent" <c:if test="${select eq 'reviewContent' }">selected</c:if>>내용</option>
	</select>
	<input type="text" name="search" id="search" placeholder="검색어를 입력하세요"  value="${param.search}" />
	<button>검색</button>

</div>

</form>
</div>
<!-- 검색창 form -->


</div>

<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />








