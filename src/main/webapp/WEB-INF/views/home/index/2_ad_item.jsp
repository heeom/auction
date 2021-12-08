<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="ad_item">
    <div class="set">
        <c:set var="articleList" value="${itemList }" />
        <c:set var="articleSize" value="${itemList.size() }" />

        <c:if test="${not empty articleList }">
            <c:forEach var="i" items="${articleList }">
                <a href="../item/view.it?no=${i.getNo() }">
					<span class="info">
						<span class="ico_hot">HOT</span>
						<span>현재가 ${i.getNowPrice() }원</span>
						<span>|</span>
						<span>${i.getCategory() }</span>
					</span>
                    <span class="thumbnail"><img src="../upload/${i.getThumbnail() }"></span>
                    <span class="item_name"><c:out value="${i.getName() }" /></span>
                    <span class="datetime_end">마감 ${i.getEndTime() }</span>
                </a>
            </c:forEach>
        </c:if>
    </div>
</div>
