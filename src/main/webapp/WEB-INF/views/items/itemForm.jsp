<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../home/head.jsp" />

<link rel="stylesheet" href="/css/item.css">
<link rel="stylesheet" href="/css/summernote/summernote-lite.min.css">
<link rel="stylesheet" href="/css/datepicker/datepicker.min.css">
<script src="/js/summernote/summernote-lite.min.js"></script>
<script src="/js/datepicker/datepicker.min.js"></script>
<script src="/js/datepicker/datepicker.ko.js"></script>
<script src="/js/jquery.number.min.js"></script>

<c:set var="ticketCount" value="${ticketCount }" />

<div class="item_write">
	<div class="title">출품 등록하기</div>
	<form action="writePro.it" method="post" enctype="multipart/form-data" name="fi" onsubmit="return writeForm_submit();">
		<input type="text" class="subject" name="subject" maxlength="55" placeholder="상품명" required="required">
		<select class="category" name="category">
			<option value="">카테고리</option>
			<option value="의류">의류</option>
			<option value="앤티크">앤티크</option>
			<option value="명품">명품</option>
			<option value="연예">연예</option>
			<option value="전자기기">전자기기</option>
			<option value="가전제품">가전제품</option>
			<option value="도서">도서</option>
			<option value="스포츠">스포츠</option>
			<option value="뷰티">뷰티</option>
		</select>
		<div class="info">
			<input type="text" name="price_start" class="price_formatting" placeholder="시작가(원)" maxlength="11" required="required">
			<input type="text" name="price_end" class="price_formatting" placeholder="낙찰가(원)" maxlength="11" required="required">
			<span class="delivery">
				<input type="text" name="price_delivery" class="price_formatting" maxlength="6" placeholder="배송비(원)" required="required">
				<button onclick="freeDelivery();return false;">무료배송</button>
			</span>
			<input type="datetime" class="datepicker-here" data-language="ko" data-timepicker="true" name="datetime_end" placeholder="마감일시" data-position="left top" maxlength="20" readonly="readonly" required="required">
		</div>
		<textarea id="summernote" name="content"></textarea>
		<div class="ticket">
			<c:choose>
				<c:when test="${ticketCount.getTicket() == 0 }">
					<span>땅땅티켓을 보유하고 있지 않습니다. <strong>땅땅샵에서 티켓을 구매하여 리스트 최상단에 효율적으로 경매를 진행</strong>해보세요!</span>
				</c:when>
				<c:otherwise>
					<span class="chk_ticket"><input type="checkbox" id="chk_ticket" name="ticket" value="1"><label for="chk_ticket">사용하기</label></span>
					<span>땅땅티켓 <strong><c:out value="${ticketCount.getTicket() }" /></strong>개를 보유하고 있습니다.</span>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="guide">
			<p class="word">반드시 읽어보세요.</p>
			<ul>
				<li><span>대표 이미지로 지정되는 썸네일은 상품 설명의 <strong>제일 마지막에 들어간 이미지로 지정</strong></span>됩니다.</li>
				<li>한번 지정된 대표 이미지와 마감일시는 절대 수정할 수 없습니다.</li>
			</ul>
		</div>
		<div class="btn">
			<input type="button" class="back" onclick="history.back();" value="취소">
			<button class="submit">출품 등록하기</button>
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
	
	// air datepicker
	$(function() {
		var minDate = new Date();
		var maxDate = new Date(minDate.getFullYear(), minDate.getMonth()+2, minDate.getDay());
		
		$(".datetime_end").datepicker();
		$('.datepicker-here').datepicker({
		    language: 'ko',
		    minDate: minDate,
			maxDate: maxDate,
		    dateFormat: "yyyy-mm-dd"
		})
		
		$('.price_formatting').number(true).on("keyup", function() {
		    $(this).val($(this).val().replace(/[^0-9]/g,""));
		});
	});
</script>

<jsp:include page="../home/tail.jsp" />