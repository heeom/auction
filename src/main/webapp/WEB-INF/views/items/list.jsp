<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../home/head.jsp" />

<link rel="stylesheet" href="/css/item.css">
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet">
<script src="/js/jquery.number.min.js"></script>

<c:set var="articleList" value="${articleList}" />
<c:set var="articleSize" value="${articleList.size()}" />
<c:set var="pageInfo" value="${pageInfo}" />
<c:set var="orderarticle" value="${orderarticle}"/>

<div class="item_list">
	<div class="title">실시간경매 목록</div>

	<div class="fixed_img_row">
		<ul>
			<c:forEach var="articleList" items="${articleList}">
				<li class="desc">
					<a href="view.it?no=${articleList.getNo()}">
						<span class="thumb">
							<img src="../upload/${articleList.getThumbnail()}" width="120">
							<em>상품 보기</em>
						</span>
						<span class="subject">${articleList.getName()}</span>
					</a>
					<p class="price_now">
						<span>현재가</span>
						<span class="price_formatting price">${articleList.getNowPrice()}</span>
						<span class="price">원</span>
					</p>
					<p class="datetime_end">마감일시 ${articleList.getEndTime()}</p>
					<p class="category">${articleList.getCategory()}</p>
					<p class="delivery">
						<span>| 배송비 </span>
						<span class="price_formatting">${articleList.getDeliveryPrice()}</span>
						<span>원</span>
					</p>
					<span class="member_info">
						<span>${articleList.getMemberNick()}</span>
						<input type="hidden" id="memberid" value="${articleList.getMemberID()}">
					</span>
				</li>
			</c:forEach>
			<c:if test="${empty articleList }">
				<li class="empty">
					<span>등록된 상품이 없습니다 :(</span>
				</li>
			</c:if>
		</ul>

		<div class="tail">
			<span>
				<a href="write.it" class="btn_write">출품하기</a>
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
				<form action="itemcategorySearchAction.it" method="post">
					<input type="hidden" name="freedelivery" value="0">
					<input type="submit" name="free" value="무료배송" style="border: 0; background-color: white;">
				</form>
			</li>
		</ul>
		<ul>
			<li class="title">가격대</li>
			<li>
				<form action="itemSearchAction.it" method="post">
					<input type="text" id="minprice" name="minprice" value="" placeholder="최소가">
					<span> ~ </span>
					<input type="text" id="maxprice" name="maxprice" value="" placeholder="최대가">
					<input type="submit" class="search_price" value="검색">
				</form>
			</li>
		</ul>
		<ul>
			<li>
				<form action="itemMyitemAction.it" name="memberID" method="post">
					<input type="submit" value="내 상품보기" style="border: 0; background-color: white;">
				</form>
			</li>
		</ul>
	</div>
	
	<!-- 페이징 처리 -->
	<div class="fixed_img_row">
		<c:choose>
			<c:when test="${pageInfo.getPage() <= 1}">
				<input type="button" id="listbutton" value="이전" style="border: 0; border-radius: 50%; min-height: 25px; width: 30px; background-color: #FF4000; font-family: 'Gamja Flower'; color: white;">&nbsp;
   		 	</c:when>
			<c:otherwise>
				<input type="button" id="listbutton" value="이전" style="border: 0; border-radius: 50%; min-height: 25px; width: 30px; background-color: #FF4000; font-family: 'Gamja Flower'; color: white;" onclick="location.href='list.it?page=${pageInfo.getPage() - 1}'">&nbsp;
 	  		 </c:otherwise>
		</c:choose>

		<c:forEach var="i" begin="${pageInfo.getStartPage()}" end="${pageInfo.getEndPage()}" step="1">
			<c:choose>
				<c:when test="${i == pageInfo.getPage()}">
					<span style="font-weight: bold; color: #FF4000;">${i}</span>&nbsp;&nbsp;&nbsp;&nbsp;
				</c:when>
				<c:otherwise>
					<a href="list.it?page=${i}">${i}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				 </c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${pageInfo.getPage() >= pageInfo.getMaxPage()}">
				<input type="button" id="listbutton" style="border: 0; border-radius: 50%; min-height: 25px; width: 30px; background-color: #FF4000; font-family: 'Gamja Flower'; color: white;" value="다음">
			</c:when>
			<c:otherwise>
				<input type="button" id="listbutton" style="border: 0; border-radius: 50%; min-height: 25px; width: 30px; background-color: #FF4000; font-family: 'Gamja Flower'; color: white;" value="다음" onclick="location.href='list.it?page=${pageInfo.getPage() + 1}'">
			</c:otherwise>
		</c:choose>
	</div>
	<!-- // 페이징 처리 -->
</div>

<script type="text/javascript">
$(function() {
	$('.price_formatting').number(true);
});
</script>

<jsp:include page="../home/tail.jsp" />
