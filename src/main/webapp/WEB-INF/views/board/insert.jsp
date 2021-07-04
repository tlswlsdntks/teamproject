<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
	.fileDrop{
		width: 100%;
		height: 250px;
		border: 1px solid red;
		margin: 20px 0;
	}
	.ellipsisTarget{
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
</style>

</head>
<body>

	<div class="container">
		<div class="row">
			<h1 class="jumbotron">게시글 작성 화면</h1>
		</div>
		<div class="row">
			<form action="/board/insert" method="post">
				<div class="form-group">
					<label for="writer">writer</label>
					<input value="${login.userId}" id="writer" class="form-control" name="writer" type="text">
				</div>
				<div class="form-group">
					<label for="title">title</label>
					<input id="title" class="form-control" name="title" type="text">
				</div>
				<div class="form-group">
					<label for="content">content</label>
					<textarea rows="10" id="content" class="form-control" name="content" style="height: 400px;"></textarea>
				</div>
				</div>
				<button type="submit" class="btn btn-danger">게시글 작성</button>
				<button type="reset" class="btn btn-info">뒤로가기</button>
			</form>
		</div>
		<div class="row">
			<div class="fileDrop">파일을 드래그앤드랍 하세요</div>
			<div class="uploadedList"></div>
		</div>
	</div>
	
	
	<script type="text/javascript" src="/resources/js/test.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			$(".fileDrop").on("dragenter dragover", function(event) {
				event.preventDefault();
			});
			
			$(".fileDrop").on("drop", function(event) {
				event.preventDefault();
				var files = event.originalEvent.dataTransfer.files;
				var file = files[0];
				var formData = new FormData();
				formData.append("file",file);
				
				$.ajax({
					type : 'post',
					url : '/board/uploadfile',
					processData : false,
					contentType : false,
					data : formData,
					dataType : 'text',
					success : function(filename) {
						var msg = uploadUpdateForm(getLinkFileName(filename), filename, getOriginalName(filename));
						$(".uploadedList").append(msg);
					}
				});
			});
			
			$(".uploadedList").on("click", "small.delFile", function(event) {
				event.preventDefault();
				var that = $(this);
				$.ajax({
					type : 'post',
					url : '/board/deleteFile',
					data : {
						filename : that.attr("data-filename")
					},
					dataType : 'text',
					success : function(result) {
						alert(result);
						that.parent("p").parent("div").remove();
					}
				});
			});
			
			$("button[type='submit']").click(function(event) {
				event.preventDefault();
				var str = '';
				
				$(".delFile").each(function(index) {
					var filename = $(this).attr("data-filename")
					
					/* <input name="files[index]" type="hidden" value="filename"> */
					str += getFileUploadFileNameInput(index, filename); 
				});
				
				$("form").append(str);
				$("form").submit();
			});
			
			$("button[type='reset']").click(function(event) {
				event.preventDefault();
				
				$(".delFile").each(function(index) {
					var filename = $(this).attr("data-filename");
					$.ajax({
						
						type : 'post',
						url : '/board/deleteFile',
						data : {
							filename : filename
						},
						dataType : 'text'
					});
				});
				history.back();
			});
			
			
		});
		
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>