<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../home/head.jsp" />

<link rel="stylesheet" href="/css/item.css">
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
<script src="/js/jquery.number.min.js"></script>


<c:set var="pageInfo" value="${PageCriteria}"/>
<c:set var="itemList" value="${itemList}"/>

<div class="item_list">
	<div class="title">실시간경매 목록</div>
	<div class="fixed_img_row">
		<ul>
			<c:forEach var="item" items="${itemList}" >
				<li class="desc">
					<a href="/items/${item.itemId}">
						<span class="thumb">
							<img src="../upload/${item.itemThumbnail}" width="120">
							<em>상품 보기</em>
						</span>
						<span class="subject">${item.itemName}</span>
					</a>
					<p class="price_now">
						<span>현재가</span>
						<span class="price_formatting price">${item.nowBidPrice}</span>
						<span class="price">원</span>
					</p>
					<p class="datetime_end">마감일시 ${item.endItemDate}</p>
					<p class="category">${item.itemCategory}</p>
					<p class="delivery">
						<span>| 배송비 </span>
						<span class="price_formatting">${item.deliveryPrice}</span>
						<span>원</span>
					</p>
					<span class="member_info">
						<span>${item.username}</span>
						<input type="hidden" id="username" value="${item.username}">
					</span>
				</li>
			</c:forEach>
			<c:if test="${empty itemList }">
				<li class="empty">
					<span>등록된 상품이 없습니다 :(</span>
				</li>
			</c:if>
		</ul>

		<div class="tail">
			<span>
				<a href="/items/create" class="btn_write">출품하기</a>
			</span>
		</div>
	</div>

	<div class="list_side_tab">
		<ul>
			<li class="title">카테고리</li>
			<li>
				<form action="itemcategorySearchAction.it" method="post">
					<input type="submit" name="dress" value="의류" style="border: 0; background-color: white;">
					<input type="submit" name="Antique" value="앤티크" style="border: 0; background-color: white;">
					<input type="submit" name="Luxury" value="명품" style="border: 0; background-color: white;">
					<input type="submit" name="entertainments" value="연예" style="border: 0; background-color: white;">
					<input type="submit" name="Electronics" value="전자기기" style="border: 0; background-color: white;">
					<input type="submit" name="product" value="가전제품" style="border: 0; background-color: white;">
					<input type="submit" name="books" value="도서" style="border: 0; background-color: white;">
					<input type="submit" name="sports" value="스포츠" style="border: 0; background-color: white;">
					<input type="submit" name="beauty" value="뷰티" style="border: 0; background-color: white;">
				</form>
			</li>
		</ul>
		<ul>
			<li>
				<form action="#" method="post">
					<input type="hidden" name="freedelivery" value="0">
					<input type="submit" name="free" value="무료배송" style="border: 0; background-color: white;">
				</form>
			</li>
		</ul>
		<ul>
			<li class="title">가격대</li>
			<li>
				<form action="#" method="post">
					<input type="text" id="minprice" name="minprice" value="" placeholder="최소가">
					<span> ~ </span>
					<input type="text" id="maxprice" name="maxprice" value="" placeholder="최대가">
					<input type="submit" class="search_price" value="검색">
				</form>
			</li>
		</ul>
		<ul>
			<li>
				<form action="#" name="username" method="post">
					<input type="submit" value="내 상품보기" style="border: 0; background-color: white;">
				</form>
			</li>
		</ul>
	</div>
	
	<!-- 페이징 처리 -->
	<div class="fixed_img_row">
		<c:choose>
			<c:when test="${pageCriteria.currentPageNum <= 1}">
				<input type="button" id="listbutton" value="이전" style="border: 0; border-radius: 50%; min-height: 25px; width: 30px; background-color: #FF4000; font-family: 'Gamja Flower'; color: white;">&nbsp;
   		 	</c:when>
			<c:otherwise>
				<input type="button" id="listbutton" value="이전" style="border: 0; border-radius: 50%; min-height: 25px; width: 30px; background-color: #FF4000; font-family: 'Gamja Flower'; color: white;" onclick="location.href='/items?pageCriteria=${pageCriteria.currentPageNum - 1}'">&nbsp;
 	  		 </c:otherwise>
		</c:choose>
		<c:forEach var="pageNo" begin="${pageCriteria.startPage}" end="${pageCriteria.endPage}" step="1">
			<c:choose>
				<c:when test="${pageNo == pageCriteria.currentPageNum}">
					<span style="font-weight: bold; color: #FF4000;">${pageNo}</span>&nbsp;&nbsp;&nbsp;&nbsp;
				</c:when>
				<c:otherwise>
					<a href="/items?currentPageNum=${pageNo}">${pageNo}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				 </c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${pageCriteria.currentPageNum >= pageCriteria.maxPage}">
				<input type="button" id="listbutton" style="border: 0; border-radius: 50%; min-height: 25px; width: 30px; background-color: #FF4000; font-family: 'Gamja Flower'; color: white;" value="다음">
			</c:when>
			<c:otherwise>
				<input type="button" id="listbutton" onclick="location.href='/items?currentPageNum=${pageCriteria.currentPageNum + 1}'" style="border: 0; border-radius: 50%; min-height: 25px; width: 30px; background-color: #FF4000; font-family: 'Gamja Flower'; color: white;" value="다음" >
			</c:otherwise>
		</c:choose>
	</div>
	<!-- // 페이징 처리 -->
</div>

<script type="text/javascript">
	$(function () {
		$('.price_formatting').number(true);
	});

	function btn(itemId) {
		const token = localStorage.getItem("accessToken");
		$.ajax({
			url : "/items/"+itemId,
			method : "GET",
			dataType : "json",
			headers: {"Authentication" : token}
		})
	}
</script>

<jsp:include page="../home/tail.jsp" />
