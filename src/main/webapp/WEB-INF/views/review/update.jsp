<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style>

.btnUC {
    border-radius: 5px;
    border: 0px solid black;
    background-color: #39AEA9;
    color: white;
    width: 70px;
    height: 32px;
	font-weight: bold;
}

</style>

<!-- 스마트 에디터 2 로드 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js"></script>

<script type="text/javascript">
function submitContents(elClickedObj) {
	oEditors.getById["reviewContent"].exec("UPDATE_CONTENTS_FIELD", [])
	
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}

$(document).ready(function() {
	$("#btnUpdate").click(function() {
		submitContents($("#btnUpdate"))
		
		$("form").submit();
	})
})
</script>

<script type="text/javascript">
$(document).ready(function() {
	$("#cancel").click(function() {
		history.go(-1)
	})

	
	if( ${empty boardFile} ) {
		$("#newFile").show()
	} else {
		$("#originFile").show()
	}
	
	$("#deleteFile").click(function() {
		$("#originFile").toggleClass("through")
		$("#newFile").toggle();
	})
})
</script>

<style type="text/css">
.through {
	text-decoration: line-through;
}

#deleteFile {
	font-size: 1.5em;
	font-weight: bold;
	color: red;
}

#newFile, #originFile {
	display: none;
}
</style>

<div class="container">
<br>

<!-- 게시글+첨부파일 수정 form -->
<form action="/review/update" method="post" enctype="multipart/form-data">

<!-- 게시글 수정 -->
 <input type="hidden" name="reviewNo" value="${updateReview.reviewNo }">

<div class="form-group">
	<label for="write"></label>
	<input type="text" id="write" value="${nick }" class="form-control" readonly="readonly">
</div>

<div class="form-group">
	<label for="title"></label>
	<input type="text" id="reviewTitle" name="reviewTitle" class="form-control" value="${updateReview.reviewTitle }">
</div>
<div class="form-group">
	<label for="content"></label>
	<textarea rows="15" style="width: 100%;" id="reviewContent" name="reviewContent">${updateReview.reviewContent }</textarea>
</div>
<!-- 게시글 수정 -->

<!-- 첨부파일 수정 -->
<div class="form-group">

	<div id="fileBox">
		<div id="originFile">
			<a href="/review/download?reviewFileNo=${reviewFile.reviewFileNo }">${reviewFile.originName }</a>
			<span id="deleteFile">X</span>
		</div>

		<div id="newFile">
			<br>
			<label for="file"></label>
			<input type="file" id="file" name="file">
		</div>
	</div>

</div>
<!-- 첨부파일 수정 -->

<!-- 수정,취소 버튼 -->
<div class="text-center">
	<button id="btnUpdate" style="height: 32px; font-weight: bold;" class="btnUC">수정</button>
	<input type="reset" id="cancel" value="취소" class="btnUC">
</div>
<!-- 수정,취소 버튼 -->

</form>
<!-- 게시글+첨부파일 수정 form -->

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors
	, elPlaceHolder: "content"
	, sSkinURI: "/resources/se2/SmartEditor2Skin.html"
	, fCreator: "createSEditor2"
})
</script>

</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />
