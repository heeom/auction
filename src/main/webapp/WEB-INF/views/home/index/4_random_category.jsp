<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="random_category">
	<div class="set">
		<c:set var="randomCategory" value="${randomCategory }" />
		<c:choose>
			<c:when test="${not empty randomCategory }">
				<div class="subject">실시간 경매 상품</div>
				<c:forEach var="i" items="${randomCategory }">
					<a href="../item/view.it?no=${i.getItemNumber() }">
						<span class="category"><c:out value="${i.getItemCategory() }" /></span>
						<span class="thumbnail"><img src="upload/${i.getItemThumbnail() }"></span>
						<span class="item_name"><c:out value="${i.getItemName() }" /></span>
					</a>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<span class="empty">진행중인 실시간경매 상품이 없습니다.</span>
			</c:otherwise>
		</c:choose>
	</div>
</div>
