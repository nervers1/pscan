<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
	<div class="header">
		<ul>
			<li class="logo_area"><img class="img_logo" alt="logo" src="/resources/images/appliance/logo_green.png">
			
			</li>
			<li class="login_area">
			<c:choose>
			<c:when test="${Session == null || Session.userId == ''}">
				<div class="login_form">
					<ul>
						<li><label for="userId">ID : <input type="text" alt="아이디" class="userId" name="userId" id="userId"></label></li>
						<li><label for="userPassword">PW : <input type="password" alt="비밀번호" class="userId" name="password" id="password"></label></li>
						<li class="btn_login"><span class="txt_login">로그인</span></li>					
					</ul>
				</div>
			</c:when>
			<c:when test="${Session != null && Session.check_yn == 'N'}">
				<div class="init_password">
					<div>
						<div class="guide-init">*처음 로그인하시는 분은 초기 비밀번호를 지정해 주세요.</div>
						<div class="pw-init-con"><label class="label-init-pw" for="initPassword">PW : <input type="password" alt="비밀번호" class="userId" name="initPassword" id="initPassword"></label><div class="btn_pw_init">변경</div></div>
					</div>				
				</div>
			</c:when>
			<c:otherwise>
				<div class="logout_form">
					<ul>
						<li id="welcome"><c:out value="${Session.name }"/>님 환영합니다.</li>
						<li class="btn_logout"><span class="txt_logout">로그아웃</span></li>					
					</ul>
				</div>
			</c:otherwise>
			</c:choose>
			</li>
		</ul>
	</div>
