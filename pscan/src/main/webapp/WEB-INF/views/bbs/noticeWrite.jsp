<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="main_block2">
	<div class="memberHeader">
		<h2 class="memberTitle">공지사항</h2> 
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
				<div class="btn-notice-save">저장</div>
				<div class="btn-notice-cancel">취소</div>
			</div>
		</div>
	</div>
</div>