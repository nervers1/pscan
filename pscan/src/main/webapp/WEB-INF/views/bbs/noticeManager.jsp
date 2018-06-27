<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="main_block2">
	<div class="memberHeader">
		<h2 class="memberTitle">공지사항관리</h2> 
	</div>
	<div class="controll_area">
		<div class="find_option">
			<select id="searchOption">
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="text" class="search_txt" id="searchText" name="searchText" >
		</div>
		<div class="btn_search">검색</div>
	</div>
	<div id="gridWrapper"></div>
	<div id="gridPaging"></div>
	
</div>