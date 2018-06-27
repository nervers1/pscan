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
<div class="main_block2">
	<div class="memberHeader">
		<h2 class="memberTitle">공지사항등록</h2> 
	</div>
	<div class="noticeWrapper">
		<div class="notice-title">제목</div>
		<div class="notice-title-input">
			<input type="text" id="title" name="title">
		</div>
		<div class="notice-content">내용 </div>
		<div class="notice-content-input">
			<textarea id="notice_content" name="notice_content" class="notice-input-area"></textarea>
		</div>
		<div class="notice-button-area">
			<div class="notice-button-wrapper">
				<div class="btn-notice-save" id="btn-notice-save">저장</div>
				<div class="btn-notice-cancel" id="btn-notice-cancel">취소</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script type="text/javascript" src="/resources/jQueryFileUpload/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="/resources/js/appliance/main.js"></script>
<script type="text/javascript" src="/resources/js/appliance/noticeWrite.js"></script>