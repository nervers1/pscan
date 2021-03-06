<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>P-SCAN Appliance</title>
<link rel="shortcut icon" href="/resources/favicon.ico">
<link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/nanumgothic.css">
<link href="/resources/css/normalize.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/jquery-ui.structure.min.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/jquery-ui.theme.min.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/appliance/style.css" rel="stylesheet" type="text/css" />
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
<c:import url="/appliance/historyMain"/>
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
</body>
</html>