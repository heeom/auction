<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="username" value="${username}"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>땅땅땅 - 신개념 쇼핑의 시작</title>
		<link rel="icon" type="image/png" sizes="32x32" href="/img/favicon.png">
		<link href="/css/default.css" rel="stylesheet" type="text/css">
		<link href="/css/font/NotoSansKR.css" rel="stylesheet" type="text/css">
		<script src="/js/jquery-3.4.1.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				// 검색
				$('.txt_search').click(function(){
					var more_menu = $('.header_more_menu'),
						search_tab = $('.search_tab'),
						login_tab = $('.login_tab');

					if(!search_tab.hasClass('show')) {
						if(more_menu.hasClass('show')) {
							more_menu.animate({top:-350}, 300).removeClass('show');
							$('.back_drop').css('display', 'none');
						} else if(login_tab.hasClass('show')) {
							login_tab.fadeOut(100).removeClass('show');
						}

						$.post("../main/search_tab.jsp", {}, function(data) {
							$(".load_search_tab").html(data);
							$('.back_drop').css({'display': 'block', 'opacity': 0}).animate({'opacity': 1}, 100);
						});
					}
				});
				// 로그인
				$('.dig_login').click(function(){
					var more_menu = $('.header_more_menu'),
						search_tab = $('.search_tab'),
						login_tab = $('.login_tab');

					if(!login_tab.hasClass('show')) {
						if(more_menu.hasClass('show')) {
							more_menu.animate({top:-350}, 300).removeClass('show');
							$('.back_drop').css('display', 'none');
						} else if(search_tab.hasClass('show')) {
							search_tab.fadeOut(100).removeClass('show');
						}

						$.post("../member/login.jsp", {}, function(data) {
							$(".load_login_tab").html(data);
							$('.back_drop').css({'display': 'block', 'opacity': 0}).animate({'opacity': 1}, 100);
						});
					}
				});
			});
			//검색창 엔터 검색
			function press(f){
				if(f.keyCode == 13){
				 search.submit();
			}
			}
		</script>
	</head>
	<body>
		<header>
			<div class="header_wrap">
				<div class="logo"><a href="/home"><img src="/img/logo.png"></a></div>
				<nav>
					<ul>
						<li><a href="/items">실시간 경매</a></li>
					</ul>
				</nav>
				<div class="btn_header">
					<c:choose>
						<c:when test="${username eq null}">
							<a href="#"><button>땅땅땅 이용방법</button></a>
						</c:when>
						<c:otherwise>
							<a href="/items/create"><button>출품하기</button></a>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="member_section">
					<c:choose>
						<c:when test="${username eq null}">
							<span>
								<a class="dig_login" href="/members/login">로그인</a>
								<span class="login_tab load_login_tab">
								</span>
							</span>
							<span><a href="/members/join">회원가입</a></span>
						</c:when>
						<c:otherwise>
							<span><a href="/members/logout">로그아웃</a></span>
							<span><a href="#">나의경매장</a></span>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="search">
				  <form method="get" action="searchPro.sr" name="search">
					<input type="text" class="txt_search" name="keyword" autocomplete="off" placeholder="어떤 상품을 찾고 계신가요?">
				  </form>
				</div>
				<div class="load_search_tab">
				</div>
			</div>
		</header>
		<div class="load_more">
		</div>
		<section>
			<article>
