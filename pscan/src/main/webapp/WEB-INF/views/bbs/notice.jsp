<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>P-SCAN 공지사항</title>
<link rel="shortcut icon" href="/resources/favicon.ico">
<link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/nanumgothic.css">
<link href="/resources/css/normalize.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/jquery-ui.structure.min.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/jquery-ui.theme.min.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/appliance/style.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="/resources/jsgrid-1.5.3/jsgrid.min.css" />
<link type="text/css" rel="stylesheet" href="/resources/jsgrid-1.5.3/jsgrid-theme.min.css" />
</head>
<body>
<div id="content">
<!-- 헤더영역 -->
<header class="loginHeader">
<c:import url="/appliance/header"/>
</header>
<!-- 헤더영역 -->

<!-- 메인 Block -->
<section class="main_contents">
<c:choose>
	<c:when test="${contents eq 'notice'}">
		<c:import url="/bbs/noticeContent"/>
	</c:when>
	<c:when test="${contents eq 'noticeManager'}">
		<c:import url="/bbs/noticeManager"/>
	</c:when>
	<c:when test="${contents eq 'noticeList'}">
		<c:import url="/bbs/noticeList"/>
	</c:when>
	<c:when test="${contents eq 'noticeView'}">
		<c:import url="/bbs/noticeView"/>
	</c:when>
	<c:when test="${contents eq 'noticeWrite'}">
		<c:import url="/bbs/noticeWrite"/>
	</c:when>
</c:choose>
</section>
<!-- 메인 Block -->
<!-- 풋터 영역 -->
<c:import url="/appliance/footer"/>
<!-- 풋터 영역  -->
</div>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script type="text/javascript" src="/resources/jQueryFileUpload/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="/resources/js/appliance/main.js"></script>
<script type="text/javascript" src="/resources/jsgrid-1.5.3/jsgrid.min.js"></script>
<c:choose>
	<c:when test="${contents eq 'notice'}">
<script type="text/javascript" src="/resources/js/appliance/notice.js"></script>
	</c:when>
	<c:when test="${contents eq 'noticeManager'}">
<script type="text/javascript" src="/resources/js/appliance/noticeManager.js"></script>
	</c:when>
	<c:when test="${contents eq 'noticeList'}">
<script type="text/javascript" src="/resources/js/appliance/noticeList.js"></script>
	</c:when>
	<c:when test="${contents eq 'noticeView'}">
<script type="text/javascript" src="/resources/js/appliance/noticeView.js"></script>
	</c:when>
</c:choose>
</body>
</html>