<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var projectStep =${step};
function categorya(categoryNo){
	  var btn = document.getElementsByClassName("btncate");

      function handleClick(event) {
        console.log(event.target);
//         console.log(this);
        // 콘솔창을 보면 둘다 동일한 값이 나온다

        console.log(event.target.classList);

        if (event.target.classList[1] === "clicked") {
          event.target.classList.remove("clicked");
        } else {
          for (var i = 0; i < btn.length; i++) {
            btn[i].classList.remove("clicked");
          }

          event.target.classList.add("clicked");
        }
      }

      function init() {
        for (var i = 0; i < btn.length; i++) {
          btn[i].addEventListener("click", handleClick);
        }
      }

      init();
	console.log(categoryNo)	
	$.ajax({
		type : "post"
		,url : "/admin/project"
		,data : "categoryNo="+categoryNo+"&projectStep="+projectStep
		,dataType : "html"
		,success : function(res){
			$("#resultLayout").html(res)
		},error : function(){
			console.log("프로젝트 카테고리 error");
		}
	})
}
function step(stepe){
	$.ajax({
		type : "post"
		,url : "/admin/project" 
		,data : "projectStep="+stepe
		,datdType :"html"
		,success : function(res){
			console.log("등급별로");
			$("#resultLayout").html(res)
		}
		,error : function(res){
			console.log("오류")
		}
	})

}

var category=${categoryNo};
var endNo=${paging.endNo/10};
var startPage=${paging.startPage};
var endPage = ${paging.endPage};
var cnt = ${cnt};
var str = "project";

$(document).ready(function(){
	for(var i = startPage; i <= endPage; i++){
		(function(i){
			$(".paging"+i).click(function(){	
				$.ajax({
					type : "get"
					,url : "/layout/projectpaging"
					,data : "curPage="+i+"&projectStep="+projectStep+"&categoryNo="+category
					,dataType : "html"
					,success : function(res){
						console.log("성공");
						$("#resultLayout").html(res)
					},
					error : function(){
						console.log("실패");
					}
				});
			});
		})(i);
	}
	$(".pagingFirst").click(function(){	
		$.ajax({
			type : "get"
			,url : "/layout/projectpaging"
			,data : "curPage="+1+"&projectStep="+projectStep+"&categoryNo="+category
			,dataType : "html"
			,success : function(res){
				console.log("성공");
				$("#resultLayout").html(res)
			},
			error : function(){
				console.log("실패");
			}
		});
	});
	$(".pagingBackAll").click(function(){
		$.ajax({
			type : "get"
			,url : "/layout/projectpaging"
			,data : "curPage="+ ${paging.startPage - paging.pageCount }+"&projectStep="+projectStep+"&categoryNo="+category
			,success : function(res){
				console.log("성공");
				$("#resultLayout").html(res)
			},
			error : function(){
				console.log("실패");
			}
		});
	});
	
	$(".pagingGoAll").click(function(){	
		$.ajax({
			type : "get"
			,url : "/layout/projectpaging"
			,data : "curPage="+${paging.startPage + paging.pageCount }+"&projectStep="+projectStep+"&categoryNo="+category
			,dataType : "html"
			,success : function(res){
				console.log("성공");
				$("#resultLayout").html(res)
			},
			error : function(){
				console.log("실패");
			}
		});
	});
	$(".pagingGo").click(function(){	
		$.ajax({
			type : "get"
			,url : "/layout/projectpaging"
			,data : "curPage="+${paging.curPage + 1 }+"&projectStep="+projectStep+"&categoryNo="+category
			,dataType : "html"
			,success : function(res){
				console.log("성공");
				$("#resultLayout").html(res)
			},
			error : function(){
				console.log("실패");
			}
		});
	});
	$(".pagingBack").click(function(){	
		$.ajax({
			type : "get"
			,url : "/layout/projectpaging"
			,data : "curPage="+${paging.curPage - 1 }+"&projectStep="+projectStep+"&categoryNo="+category
			,dataType : "html"
			,success : function(res){
				console.log("성공");
				$("#resultLayout").html(res)
			},
			error : function(){
				console.log("실패");
			}
		});
	});
	$(".pagingLast").click(function(){
		$.ajax({
			type : "get"
			,url : "/layout/projectpaging"
			,data : "curPage="+${paging.totalPage}+"&projectStep="+projectStep+"&categoryNo="+category
			,dataType : "html"
			,success : function(res){
				console.log("성공");
				$("#resultLayout").html(res)
			},
			error : function(){
				console.log("실패");
			}
		});
	});
	
	
})

</script>
<style type="text/css">
 #project-status {
    color: white;
    background: red;
    padding: 3px 5px;
    font-weight: 700;
    font-size: 12px;
    border-radius: 15px;
  }
  #category-section .container {
    overflow: auto;
  }
  #category-section .row {
    flex-flow: nowrap;
  }

#ing {
    float: left;
    width:33%;
    box-sizing: border-box;
}
#end {
    float: left;
    width:33%;
    box-sizing: border-box;
}


#All{
	float: right;
    width: 33%;
    box-sizing: border-box;

}

#center{
	margin:0 auto; 
}
.category{
	float: left;
}
.btncate {
        border: 1px solid;
        width: 100px;
        height: 50px;
        display: flex;
        align-items: center;
        justify-content: center;
}


</style>
</head>

<body>
<div class="row container py-3 text-center0" id="center">
	<div class="col text-center" id="ing">
		<a onclick="step(3)"><i class="fa-regular fa-circle-check fa-5x"></i></a>
		<br>
		<span>진행중인 펀딩</span>
	</div>
	<div class="col text-center" id="end">
		<a onclick="step(4)"><i class="fa-solid fa-sack-xmark fa-5x"></i></a>
		<br>
		<span>종료된 펀딩</span>
	</div>
	<div class="col text-center" id="All">
		<a onclick="step(5)"><i class="fa-solid fa-scale-balanced fa-5x"></i></a>
		<br>
		<span>전체</span>
	</div>
	
</div>
<div class="text-center" style="width:800px; margin : 50px auto;">

<c:forEach items="${catelist }" var="c">
<a onclick="categorya(${c.categoryNo})" class="btncate category">${c.categoryName}</a>
</c:forEach>
</div>
<div style="clear:both"></div>
<br>
<!-- <div class="text-center" style="width:800px; margin : 50px auto;"> -->
<!-- <table class="table table-striped table-hover table-condensed"> -->
<!-- <tr> -->
<!-- 	<th>No</th> -->
<!-- 	<th>프로젝트 이름</th> -->
<!-- 	<th>펀딩률</th> -->
<!-- 	<th>진행 단계</th> -->
<!-- 	<th>카테고리</th> -->
<!-- </tr> -->

<%-- 	<c:forEach items="${project }" var="i"> --%>
<!-- 	<tr> -->
<%-- 	<td>${i.projectNo }</td> --%>
<%-- 	<td>${i.projectTitle}</td> --%>

<%-- 	<td>${percent}%</td> --%>
<%-- 	<td><c:choose> --%>
<%-- 	<c:when test="${i.projectStep eq '3'}">진행중</c:when> --%>
<%-- 	<c:when test="${i.projectStep eq '4'}">펀딩 종료</c:when> --%>
<%-- 	</c:choose></td> --%>
<%-- 	<td><c:choose> --%>
<%-- 	<c:when test="${i.categoryNo eq '1'}">홈·리빙</c:when> --%>
<%-- 	<c:when test="${i.categoryNo eq '2'}">테크·가전</c:when> --%>
<%-- 	<c:when test="${i.categoryNo eq '3'}">향수·뷰티</c:when> --%>
<%-- 	<c:when test="${i.categoryNo eq '4'}">잡화</c:when> --%>
<%-- 	<c:when test="${i.categoryNo eq '5'}">의류</c:when> --%>
<%-- 	<c:when test="${i.categoryNo eq '6'}">디자인·예술</c:when> --%>
<%-- 	<c:when test="${i.categoryNo eq '7'}">푸드</c:when> --%>
<%-- 	<c:when test="${i.categoryNo eq '8'}">반려동물</c:when> --%>
<%-- 	</c:choose></td> --%>
<!-- 	</tr> -->
<%-- 	</c:forEach> --%>

<!-- </table> -->
<!-- </div> -->


<div class="container text-center" style="display: flex; justify-content: center;">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3" style="min-width : 800px; max-width : 800px; float:center; ">

		<c:forEach var="p" items="${project}">
      <div class="col" style="max-width: 250px;">
        <div class="card shadow-sm">
          <img src="${p.projectImage }" style="min-height: 250px;">
          <div class="card-body">
            <strong>${p.projectTitle}</strong>
            <c:if test="${p.projectStep eq 4}">
              <span id="project-status">마감</span>
            </c:if>
            <p class="card-text">${p.projectIntro}</p>
            <div class="row fs-6">
              <div class="col d-flex">
                <span style="margin-right: 10px;">
<%--                 	<fmt:formatNumber value="${p.sum / p.projectPrice}" type="percent"/> --%>
                	<fmt:formatNumber type="percent" value="${p.fundPrice/p.projectPrice}"  />
                </span>
                <span>
	                <fmt:formatNumber value="${p.projectPrice}" pattern="#,###"/>
                </span>
              </div>
              <div class="col text-end">
                <span>
                <fmt:formatDate value="${p.closeDate}" pattern="yy/MM/dd"/>
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
      </c:forEach>

    </div>
  </div>
</body>
  
</html>
<%@ include file="../../layout/paging.jsp" %>