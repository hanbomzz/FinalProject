<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/WEB-INF/views/layout/header.jsp" />


<style>

th{
	text-align: center;
}

th, td {
	border-bottom: 1px #DDD solid;
}

td {
	height: 40px;
}

input  {
	
	border-radius: 1px;
	height: 30px;
	width : 300px;
	border: 1px solid black;
	margin-right: 20px;

}


button {
	border-radius: 3px;
	border: 1px solid black;
}

select {
	
	height: 30px;
	width : 80px;
	margin-right: 10px;
	
}

 a:link { color: black; text-decoration: none;}
 a:visited { color: black; text-decoration: none;}
 a:hover { color: black; text-decoration: none;}

</style>


<div class="container">

<div id="qnalist" style="text-align: center;" >

<!-- <h1>고객센터</h1> -->
<!-- <hr> -->


<table style="margin: auto; width: 100%;  border-radius: 10px; margin-top: 100px;" >

	<tr style="border-bottom: 2px #DDD solid;">
		<th style="width: 5%;">글번호</th>
		<th style="width: 45%">제목</th>
		<th style="width: 10%">작성자</th>
<!-- 		<th style="width: 5%">조회수</th> -->
		<th style="width: 10%">작성일</th>
	</tr>
	
	
	<c:forEach items="${list }" var="qna">
	<tr style=" height: 20px;">
		<td>${qna.qnaNo} </td>
		<td><a href="/qna/view?qnaNo=${qna.qnaNo }" style="text-decoration: none;">${qna.qnaTitle }</a></td>
		<td>${qna.nick } </td>
<%-- 		<td>${qna.hit } </td> --%>
		<td><fmt:formatDate value="${qna.qnaCreateDate }" pattern="yy-MM-dd HH:mm:ss"/>  </td>
	</tr>	
	</c:forEach>

</table><br>
<a href="/qna/qnawrite" ><button class="pull-left">글쓰기</button></a>
<div class="pull-right">
<span >총 게시글 수 : ${paging.totalCount }</span>
</div>
<br><br>
<div><!-- searchform -->
<form action="/qna/list" method="get">

<div>
	<select name="select" id="select" style="border-radius: 1px; ">
		<option value="nick" id="nick" style="display: none;" <c:if test="${select eq 'nick' }">selected</c:if>>작성자</option>
		<option value="qnaTitle" id="qnaTitle" <c:if test="${select eq 'qnaTitle' }">selected</c:if>>제목</option>
		<option value="qnaContent" id="qnaContent" <c:if test="${select eq 'qnaContent' }">selected</c:if>>내용</option>
	</select>
<!-- 	<select name="select"  style="border-radius: 1px;"> -->
<!-- 		<option value="nick" id="nick" >작성자</option> -->
<!-- 		<option value="qnaTitle" id="qnaTitle" >제목</option> -->
<!-- 		<option value="qnaContent" id="qnaContent">내용</option> -->
<!-- 	</select> -->
	<input type="text" name="search" id="search" placeholder="검색어를 입력하세요"  value="${param.search}" />
	<button>검색</button>

</div>

</form>
</div><!-- searchform -->
<br>

</div><!-- main --><br><br>
<c:import url="/WEB-INF/views/qna/listpaging.jsp" />
</div><!-- container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />