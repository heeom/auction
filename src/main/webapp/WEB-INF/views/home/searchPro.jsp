<%@page import="java.net.URLEncoder"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../main/head.jsp" />

<link rel="stylesheet" href="../css/item.css">
<script src="js/jquery.number.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap"
	rel="stylesheet">
<c:set var="articleList" value="${articleList}" />
<c:set var="pageInfo" value="${pageInfo}" />
<c:set var="articleSize" value="${articleList.size()}" />
<c:set var="orderarticle" value="${orderarticle}"/>

<div class="item_list">
	<div class="title">실시간경매 목록</div>

	<div class="fixed_img_row">
		<ul>
			<c:forEach var="articleList" items="${articleList}">
				<li class="desc"><a href="../item/view.it?no=${articleList.getNo()}">
						<span class="thumb"> <img
							src="../upload/${articleList.getThumbnail()}" width="120">
							<em>상품 보기</em>
					</span> <span class="subject">${articleList.getName()}</span>
				</a>
					<p class="price_now">
						현재가<span class="price_formatting price">${articleList.getNowPrice()}</span><span
							class="price">원</span> <%-- <span class="ing">${orderarticle}명 입찰중</span> --%>
					</p>
					<p class="datetime_end">마감일시 ${articleList.getEndTime()}</p>
					<p class="category">${articleList.getCategory()}</p>
					<p class="delivery">
						| 배송비 <span class="price_formatting">${articleList.getDeliveryPrice()}</span>원
					</p> <span class="member_info"> <a href="상점주소" class="writer">${articleList.getMemberNick()}</a>
						<input type="hidden" id="memberid"
						value="${articleList.getMemberID()}">
				</span></li>
			</c:forEach>
			<c:if test="${articleSize == null }">
				<li class="empty"><span>등록된 상품이 없습니다 :(</span></li>
			</c:if>
		</ul>

		<div class="tail">
			<span><a href="../item/write.it" class="btn_write">출품하기</a></span>
		</div>
	</div>

	<div class="list_side_tab">
		<ul>
			<li class="title">카테고리</li>
			<li><form action="itemcategorySearchAction.it" method="post">
					<li><input type="submit" name="dress" value="의류"
						style="border: 0; background-color: white;"></li>
					<li><input type="submit" name="Antique" value="앤티크"
						style="border: 0; background-color: white;"></li>
					<li><input type="submit" name="Luxury" value="명품"
						style="border: 0; background-color: white;"></li>
					<li><input type="submit" name="entertainments" value="연예"
						style="border: 0; background-color: white;"></li>
					<li><input type="submit" name="Electronics" value="전자기기"
						style="border: 0; background-color: white;"></li>
					<li><input type="submit" name="product" value="가전제품"
						style="border: 0; background-color: white;"></li>
					<li><input type="submit" name="books" value="도서"
						style="border: 0; background-color: white;"></li>
					<li><input type="submit" name="sports" value="스포츠"
						style="border: 0; background-color: white;"></li>
					<li><input type="submit" name="beauty" value="뷰티"
						style="border: 0; background-color: white;"></li>
				</form></li>
		</ul>
		<ul>
			<li><form action="itemcategorySearchAction.it" method="post">
					<input type="hidden" name="freedelivery" value="0"> <input
						type="submit" name="free" value="무료배송"
						style="border: 0; background-color: white;">
				</form></li>
		</ul>
		<ul>
			<li class="title">가격대</li>
			<li>
				<form action="itemSearchAction.it" method="post">
					<input type="text" id="minprice" name="minprice" value=""
						placeholder="최소가"> ~ <input type="text" id="maxprice"
						name="maxprice" value="" placeholder="최대가"> <input
						type="submit" class="search_price" value="검색">
				</form>
			</li>
		</ul>
		<ul>
			<li><form action="itemMyitemAction.it" name="memberID"
					method="post">
					<input type="submit" value="내 상품보기"
						style="border: 0; background-color: white;">
				</form></li>
		</ul>
	</div>
</div>

<script type="text/javascript">
	/*
	 * UI Pattern Script
	 */

	$(function() {
		$('.price_formatting').number(true);
	});
</script>

<jsp:include page="../main/tail.jsp" />
