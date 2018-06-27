<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="main_block2">
	<div class="policyHeader">
		<h2 class="policyTitle">정책관리</h2> 
	</div>
	<div class="controll_area">
		<div class="find_option">
			<select id="searchOption">
				<option value="bgitmNm">대항목명</option>
				<option value="smitmNm">소항목명</option>
			</select>
			<input type="text" class="search_txt" id="searchText" name="searchText" >
		</div>
		<div class="btn_search">검색</div>
	</div>
	<div id="gridWrapper"></div>
	<div id="gridPaging"></div>

</div>