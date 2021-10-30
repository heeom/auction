<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../home/head.jsp" />

<c:set var="article" value="${requestScope.article }" />

<link rel="stylesheet" href="/css/item.css">
<link rel="stylesheet" href="/css/summernote/summernote-lite.min.css">
<link rel="stylesheet" href="/css/datepicker/datepicker.min.css">
<script src="/js/summernote/summernote-lite.min.js"></script>
<script src="/js/datepicker/datepicker.min.js"></script>
<script src="/js/datepicker/datepicker.ko.js"></script>
<script src="/js/jquery.number.min.js"></script>

<div class="item_write">
	<div class="title">출품정보 수정하기</div>
	<form action="modifyPro.it?no=${article.getNo() }" method="post" name="fi" onsubmit="return writeForm_submit();">
		<input type="text" class="subject" name="subject" maxlength="55" placeholder="상품명" value="${article.getName() }" required="required">
		<select class="category" name="category">
			<option value="">카테고리</option>
			<option value="의류" <c:if test="${article.getCategory() == '의류'}">selected</c:if>>의류</option>
			<option value="앤티크" <c:if test="${article.getCategory() == '앤티크'}">selected</c:if>>앤티크</option>
			<option value="명품" <c:if test="${article.getCategory() == '명품'}">selected</c:if>>명품</option>
			<option value="연예" <c:if test="${article.getCategory() == '연예'}">selected</c:if>>연예</option>
			<option value="전자기기" <c:if test="${article.getCategory() == '전자기기'}">selected</c:if>>전자기기</option>
			<option value="가전제품" <c:if test="${article.getCategory() == '가전제품'}">selected</c:if>>가전제품</option>
			<option value="도서" <c:if test="${article.getCategory() == '도서'}">selected</c:if>>도서</option>
			<option value="스포츠" <c:if test="${article.getCategory() == '스포츠'}">selected</c:if>>스포츠</option>
			<option value="뷰티" <c:if test="${article.getCategory() == '뷰티'}">selected</c:if>>뷰티</option>
		</select>
		<div class="info">
			<input type="text" name="price_start" class="price_formatting" placeholder="시작가(원)" maxlength="11" value="${article.getStartPrice() }" required="required">
			<input type="text" name="price_end" class="price_formatting" placeholder="낙찰가(원)" maxlength="11" value="${article.getMaxPrice() }" required="required">
			<span class="delivery">
				<input type="text" name="price_delivery" class="price_formatting" maxlength="6" placeholder="배송비(원)" value="${article.getDeliveryPrice() }" required="required">
				<button onclick="freeDelivery();return false;">무료배송</button>
			</span>
			<input type="datetime" class="datepicker-here" data-language="ko" data-timepicker="true" name="datetime_end" placeholder="마감일시" data-position="left top" maxlength="20" value="${article.getEndTime() }" readonly="readonly" disabled="disabled" required="required">
		</div>
		<textarea id="summernote" name="content">${article.getContent() }</textarea>
		<div id="guide">
			<p class="word">대표 썸네일 이미지</p>
			<ul>
				<li class="thumbnail_preview"><img src="../upload/${article.getThumbnail() }"></li>
				<li><span>한번 지정된 대표 이미지와 마감일시는 절대 수정할 수 없습니다.</span></li>
			</ul>
		</div>
		<div class="btn">
			<input type="button" class="back" onclick="history.back();" value="취소">
			<button class="submit">출품정보 수정하기</button>
		</div>
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('#summernote').summernote({
			height: 450,
			lang: 'ko-KR',
			placeholder: "출품자가 명시해야할 정보: <br><br>- 사이즈, 색상, 브랜드, 보증서 유무<br>- 사용감 (보풀, 스크래치, 터치감, 잔상)<br>- 사용기간 (제조일자, 구입시기)<br>- 고장, 파손, 얼룩 등...<br><br>구매자가 알아야할 정보는 반드시 기재하시어 사고 발생을 최소화해주시기 바랍니다.",
	        fontNames: ['Nanum Gothic', 'sans-serif', '돋움', 'Courier New', 'Helvetica', 'Tahoma', 'Verdana', 'Roboto'],
	        fontSizes: ['8', '11', '12', '14', '18', '24', '36'],
	        toolbar: [
	            ['font', ['bold', 'italic', 'underline', 'clear']],
	            ['fontname', ['fontname']],
	            ['color', ['color']],
	            ['fontsize', ['fontsize']],
	            ['para', ['paragraph']],
	            ['height', ['height']],
	            ['table', ['table']],
	            ['insert', ['link', 'picture']],
	            ['view', ['fullscreen', 'codeview']],
	            ['help', ['help']]
	          ]
		});
	});
	
	var checkUnload = 1;
	window.onbeforeunload = function() {
		if(checkUnload) {
			return "이 페이지를 벗어나면 작성중인 글은 사라집니다.";
		}
	}
	
	function writeForm_submit() {
		checkUnload = 0;
		
		if(!fi.subject.value) {
			alert("상품명을 입력해주세요.");
			fi.subject.focus();
			return false;
		} else if(!fi.category.value) {
			alert("카테고리를 선택해주세요.");
			return false;
		} else if(!fi.price_start.value) {
			alert("경매 시작가를 입력해주세요.");
			fi.price_start.focus();
			return false;
		} else if(!fi.price_end.value) {
			alert("경매 낙찰가를 입력해주세요.");
			fi.price_end.focus();
			return false;
		} else if(!fi.price_delivery.value) {
			alert("배송비를 입력해주세요.");
			fi.price_delivery.focus();
			return false;
		} else if(!fi.datetime_end.value) {
			alert("마감일시를 입력해주세요.");
			return false;
		}
		
		return true;
	}
	
	function freeDelivery() {
		fi.price_delivery.value = 0;
		alert("무료 배송을 위해 배송비를 0원으로 설정하였습니다.");
		return false;
	}
	
	$(function() {
		$(".datetime_end").datepicker();
		$('.datepicker-here').datepicker({
		    language: 'ko',
		    minDate: new Date(),
			dateFormat: "yyyy-mm-dd"
		});
		$('.datepicker-here').val('${article.getEndTime() }' . substr(0, 16));
		
		$('.price_formatting').number(true).on("keyup", function() {
		    $(this).val($(this).val().replace(/[^0-9]/g,""));
		});
	});
</script>

<jsp:include page="../home/tail.jsp" />