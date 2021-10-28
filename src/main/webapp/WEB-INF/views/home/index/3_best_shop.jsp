<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="best_shop">
	<c:forEach items="${shopRank }" var="rank" varStatus="status">
		<table class="ranktable">
			<tr>
				<td>${status.count}등 상점:</td>
				<td>${rank.mb_nick}<img class="back" src="img/favicon.png"></td>
			</tr>
		</table>
	</c:forEach>
</div>
