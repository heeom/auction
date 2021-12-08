<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="/css/index.css" rel="stylesheet" type="text/css">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<jsp:include page="head.jsp"/>
<div class="random_category">
    <div class="set">
        <c:set var="itemList" value="${itemList}" />
        <c:choose>
            <c:when test="${not empty itemList }">
                <div class="subject">실시간 경매 상품</div>
                <c:forEach var="item" items="${itemList}">
                    <a href="/items/${item.itemId}">
                        <span class="category"></span>
                        <span class="thumbnail"><img src="upload/${item.itemThumbnail}"></span>
                        <span class="item_name"><c:out value="${item.itemName}"/></span>
                    </a>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <span class="empty">진행중인 실시간경매 상품이 없습니다.</span>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="index/6_faq.jsp"/>
<jsp:include page="tail.jsp"/>