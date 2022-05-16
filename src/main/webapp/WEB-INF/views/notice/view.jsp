<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

function noticeDownload(fileNo){
	$.ajax({
		type : "post"
		,url : "/download"
		,data : "fileNo="+fileNo
		,dataType : "html"
		,contentType : 'application/x-www-form-urlencoded; charset=UTF-8' //중요
		,success : function(res){
			console.log("성공?")
			window.open("/download/?fileNo="+fileNo);
		},error : function(request, error){
	
			console.log("다운로드 실패")
			
		}	
	})
}
</script>
<style type="text/css">
.noticeView{
	margin: 2px 0;
	padding: 1px 3px;
	border-width: 2px;
	border-color: #aaa;
	border-style: solid;
		
}
.notice{
	margin : 60px 100px;
}
.noticeTitle{
	font-size : 24px;
	float : left;
	width : 50%
}
.noticeDate{
	float: right;
    width: 50%;
    height : 100%;
    text-align : right;
 	vertical-align:middle;
}
.noticeimg{
	width : 500px;
}
</style>

</head>

<body>
<div class="noticeView">
<div style="height:20px;">
	<div class="noticeTitle" >${notice.title}</div>

	<div class="noticeDate" style="color :#ccc;">작성일자 : ${notice.date} | 작성자  : 관리자</div>
</div>


<hr>
	<c:forEach items="${listfile}" var="i">
	<c:if test="${not empty i.fileOrigin}"> 
	<img src="/upload/${i.fileStorage}" class="noticeimg"> 
	
	<a onclick="noticeDownload(${i.fileNo})">${i.fileOrigin}</a><br>
	
	</c:if>
	</c:forEach> 
	<div class="notice Content">${notice.content}</div>
</div>
<div class="text-center">
<button onclick="noticeManagement()">목록</button>
<button onclick="noticeUpdate(${notice.noticeNo})">수정</button>
<button onclick="noticeDelete(${notice.noticeNo})">삭제</button>
</div>

</body>
</html>