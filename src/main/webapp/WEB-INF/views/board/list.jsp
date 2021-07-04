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
<style type="text/css">
	a.active{
		background-color : green !important;
	}
</style>
</head>
<body>
	
	<div class="container">
		<div class="row">
			<c:choose>
				<c:when test="${empty login}">
					<a href="/member/loginGet">로그인</a>
				</c:when>
				<c:otherwise>
					${login.userName}님, 반갑습니다! <a href="/member/logout">로그아웃</a>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="row">
			<a href="/board/insert">게시글 작성(${login.userName})</a>
		</div>
	
		<div class="row">
			<h1 class="jumbotron">게시글 목록 화면</h1>
		</div>
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>bno</th>
						<th>writer</th>
						<th>title</th>
						<th>readCnt</th>
						<th>updateDate</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${to.list}" var="vo">
						<tr>
							<td>${vo.bno}</td>
							<td>${vo.writer}</td>
							<td><a href="/board/read/${vo.bno}?curPage=${to.curPage}">${vo.title}</a></td>
							<td>${vo.readCnt}</td>
							<td>${vo.updateDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<jsp:include page="paging_part.jsp"></jsp:include>
		</div>
	</div>
	
	

</body>
</html>