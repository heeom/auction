<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(document).ready(function(){
		$('.header_more_menu').animate({top:59}, 300).addClass('show');
	});
</script>

<div class="more_wrap">
	<div class="header_more_menu">
		<div class="menu_list">
			<span class="menu_subject">바로가기</span>
			<a href="../talk/list.ta?type=recv">땅땅톡</a>
		</div>
		<div class="menu_list">	
			<span class="menu_subject">안전거래</span>
			<a href="../item/write.it">출품하기</a>
			<a href="../report/main.re">신고하기</a>
			<a href="../report/inquiry.re">신고조회</a>
			<a href="../order/order_list.ol">참여내역</a>
		</div>
		<div class="menu_list">
			<span class="menu_subject">고객센터</span>
			<a href="../notice/ntList.nt">공지사항</a>
			<a href="../qna/qnaList.qa">문의사항</a>
			<a href="../faq/fqaList.fq">자주묻는질문</a>
			<a href="../mypage/MarketDetail.mk">마이페이지</a>
		</div>
		<div id="category">
			<form action="../item/itemcategorySearchAction.it" method="post">
				<span><input type="submit" name="dress" value="의류"></span>
				<span><input type="submit" name="Antique" value="앤티크"></span>
				<span><input type="submit" name="Luxury" value="명품"></span>
				<span><input type="submit" name="entertainments" value="연예"></span>
				<span><input type="submit" name="Electronics" value="전자기기"></span>
				<span><input type="submit" name="product" value="가전제품"></span>
				<span><input type="submit" name="books" value="도서"></span>
				<span><input type="submit" name="sports" value="스포츠"></span>
				<span><input type="submit" name="beauty" value="뷰티"></span>
			</form>
		</div>
	</div>
</div>
