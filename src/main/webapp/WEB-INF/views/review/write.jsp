<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style>

.btnWC {
    border-radius: 5px;
    border: 0px solid black;
    background-color: #39AEA9;
    color: white;
    width: 70px;
    height: 32px;
	font-weight: bold;
}



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
	$("#cancel").click(function() {
		history.go(-1)
	})

	
	$("#btnWrite").click(function() {
		submitContents($("#btnWrite"))
		
		$("form").submit();
	})
})
</script>

<div class="container">
<br>

<!-- 게시글 작성 form -->
<form action="/review/write" method="post" enctype="multipart/form-data">
<div class="form-group">
	<label for="write"></label>
	<input type="text" id="write" value="${nick }" class="form-control" readonly="readonly">
</div>

<div class="form-group">
	<label for="title"></label>
	<input type="text" id="reviewTitle" name="reviewTitle" class="form-control">
</div>
<div class="form-group">
	<label for="content"></label>
	<textarea rows="10" style="width: 200%;" id="reviewContent" name="reviewContent"></textarea>
</div>

<div class="form-group">
	<label for="file"></label>
	<input type="file" id="reviewFile" name="reviewFile">
</div>

<div class="text-center">
	<button id="btnWrite" class="btnWC">작성</button>
	<input type="reset" id="cancel" value="취소" class="btnWC">
</div>
</form>
<!-- 게시글 작성 form -->

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






















