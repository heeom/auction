<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/default.css" rel="stylesheet" type="text/css">
<jsp:include page="head.jsp" />

<!-- 회원가입 후 이메일 인증 확인 알림 -->
<c:if test="${not empty sessionScope.alertEmail }">
    <script type="text/javascript">
        alert('${alertEmail } 이메일로 인증 확인 메일이 전송되었습니다. 이메일을 확인하세요.');
    </script>
    <c:remove var="alertEmail" scope="session" />
</c:if>

<%--<jsp:include page="index/1_slide.jsp" />--%>
<jsp:include page="index/2_ad_item.jsp" />
<jsp:include page="index/3_best_shop.jsp" />
<jsp:include page="index/4_random_category.jsp" />
<jsp:include page="index/5_graph.jsp" />
<jsp:include page="index/6_faq.jsp" />

<jsp:include page="tail.jsp" />