<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<nav aria-label="Page navigation">
  <ul class="pagination">
    <li>
      <a href="/board/list/${to.curPage-1>0 ? to.curPage-1 : 1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
		<c:forEach begin="${to.beginPageNum}" end="${to.finishedPageNum}" var="curPage">
			<li class="${to.curPage==curPage ? 'active' : ''}"><a href="/board/list/${curPage}">${curPage}</a></li>
		</c:forEach>
    <li>
      <a href="/board/list/${to.curPage+1<=to.totalPage ? to.curPage+1 : to.curPage}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>