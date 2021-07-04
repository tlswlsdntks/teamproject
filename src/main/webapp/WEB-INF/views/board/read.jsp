<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<h1 class="jumbotron">게시글 상세 화면</h1>
		</div>
		<div class="row">
			<div class="panel panel-success">
				<div class="panel-heading">
					<span>No.${vo.bno}</span>
					<span><span class="glyphicon glyphicon-user" aria-hidden="true"></span>${vo.writer}</span>
					<span class="pull-right"><span class="glyphicon glyphicon-time" aria-hidden="true"></span>${vo.updateDate}</span>
				</div>
				
				<div class="panel-body" style="height: 50px;">
					<h3> ${vo.title} </h3>
				</div>
				
				<div class="panel-body" style="height: 400px;">
					<textarea class="form-control" readonly style="width: 100%; height: 100%;">${vo.content}</textarea>
					
				</div>
				
				<div class="panel-body" style="height: 200px;">
					<div class="uploadedList"></div>
				</div>
				
			</div>
			
			<div class="panel-footer">
				<a class="btn btn-info list">목록</a>
				<a class="btn btn-warning update ${vo.writer!=login.userId ? 'disabled' : ''}">수정</a>
				<a class="btn btn-danger delete ${vo.writer!=login.userId ? 'disabled' : ''}">삭제</a>
				<a class="btn btn-primary reply ${empty login.userId ? 'disabled' : ''}">댓글</a>
			</div>
		</div>
		<div class="row">
			<div class="collapse" id="myCollapse">
				<div class="well">
					<div class="form-group">
						<label for="replyer">작성자</label>
						<input readonly value="${login.userId}" id="replyer" class="form-control">
					</div>
					<div class="form-group">
						<label for="replyText">내용</label>
						<input id="replyText" class="form-control">
					</div>
					<div class="form-group">
						<button class="btn btn-success reply_insert_btn">댓글 등록</button>
					</div>
				</div>			
			</div>
		</div>
		
		<div class="row" id="repliesList">
		</div>
		
		<!-- Modal -->
		<!-- tabindex="-1" 를 지우면 esc 키도 꺼지지 않는다 -->
		<div class="modal fade" data-backdrop="static" id="myModal" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="modal_rno"></h4>
		      </div>
		      <div class="modal-body">
		        <div class="form-group">
						<label for="replyer_update">작성자</label>
						<input id="replyer_update" class="form-control">
					</div>
					<div class="form-group">
						<label for="replyText_update">댓글 내용</label>
						<input id="replyText_update" class="form-control">
					</div>
		      </div>
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				<button data-dismiss="modal" id="reply_update_btn" type="button" class="btn btn-primary">댓글 수정 완료</button>
				<!-- data-dismiss="modal" (접기) -->
		      </div>
		    </div>
		  </div>
		</div>
		
		
	</div>
	
	<form action="/board/delete/${vo.bno}?curPage=${curPage}" method="post"></form>
	<script type="text/javascript" src="/resources/js/test.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			var bno = ${vo.bno};
			
			$(".list").click(function(event) {
				event.preventDefault();
				location.assign("/board/list/${curPage}");
			});
			
			$(".update").click(function(event) {
				event.preventDefault();
				location.assign("/board/update/${vo.bno}?curPage=${curPage}");
			});
			
			$(".delete").click(function(event) {
				event.preventDefault();
				$("form").submit();
			});
			$(".reply").click(function(event) {
				event.preventDefault();
				$("#myCollapse").toggle();
			});
			$(".reply_insert_btn").click(function(event) {
				event.preventDefault();
				$.ajax({
					type : 'post',
					url : '/replies/reply',
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'POST'
					},
					data : JSON.stringify({
						bno : bno,
						replyer : $("#replyer").val(),
						replyText : $("#replyText").val()
					}),
					dataType : 'text',
					success : function(result) {
						alert(result);
						$("#repliesList").html("");
						getReplyList(bno);
						$("#replyer").val("");
						$("#replyText").val("");
					}
				});
			});
			
			getReplyList(bno);
			getAttach(bno);
			
			$("#repliesList").on("click", ".reply_btn_delete", function(event) {
				event.preventDefault();
				var that = $(this);
				var rno = $(this).parent().attr("data-rno");
				$.ajax({
					type : "delete",
					url : "/replies/reply",
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'DELETE'
					},
					data : JSON.stringify({
						rno : rno
					}),
					dataType : 'text',
					success : function(result) {
						if(result==1){
							alert("삭제 성공!");
							that.parent().parent().parent().remove();
						}
						else
							alert("삭제 실패!");
					}
				});
			});
			
			$("#repliesList").on("click", ".reply_btn_update_form", function(event) {
				event.preventDefault();
				var that = $(this);
				var rno = that.parent().attr("data-rno");
				var replyer = that.parent().attr("data-replyer");
				var replyText = that.parent().prev().text();
				
				$("#modal_rno").text(rno);
				$("#replyer_update").val(replyer);
				$("#replyText_update").val(replyText);
			});
			
			$("#reply_update_btn").click(function(event) {
				event.preventDefault();
				
				$.ajax({
					type : 'put',
					url : '/replies/reply',
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'PUT'
					},
					data : JSON.stringify({
						rno : $("#modal_rno").text(),
						replyer : $("#replyer_update").val(),
						replyText : $("#replyText_update").val()
					}),
					dataType : 'text',
					success : function(result) {
						if(result==1){
							alert("수정 성공!");
							$("#repliesList").html("");
							getReplyList(bno);
						}
						else
							alert("수정 실패!");
					}
				});
			});
			
		});
		
		function getReplyList(bno) {
			$.getJSON("/replies/reply/" + bno, function(result) {
				/* 배열을 가져온다! */
				for(var i=0; i<result.length; i++){
					var obj = result[i];
					var msg = reply_list(obj.rno, obj.replyer, obj.replyText, obj.updateDate, "${login.userId}");
					$("#repliesList").append(msg);
				}
			});
		};
		
		function getAttach(bno) {
			$.getJSON("/board/getAttach/"+bno, function(result) {
				// attach를 가져오기 위한 메소드!
				for(var i=0; i<result.length; i++){
					var filename = result[i];
					var msg = uploadViewForm(getLinkFileName(filename), filename, getOriginalName(filename));
					$(".uploadedList").append(msg);
				}
			});
		};
		
	</script>

</body>
</html>