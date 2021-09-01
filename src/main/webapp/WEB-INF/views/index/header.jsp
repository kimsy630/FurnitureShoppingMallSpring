<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>

<html>
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />
</head>
<body>
    <header>
       <div class="gnb">
            <ul>
            	<li>
                	<form action="category.cc" method="get"><!--검색하면 넘어갈 페이지  -->
						<input type="text" name="search" placeholder="검색"><!-- 힌트 -->
						<button type="submit">검색</button>
					</form>
                </li>
	               <li><a href="settlement.mc" alt="">사업자페이지</a></li>
                <c:if test="${sessionScope.mb_classifi=='사업자'}">
	                <li><a href="settlement.mc" alt="">사업자페이지</a></li>
               	</c:if>
               	<c:if test="${sessionScope.mb_classifi=='관리자'}">
	                <li><a href="adminSettlement.mc" alt="">관리자페이지</a></li>
               	</c:if>
               	<c:if test="${sessionScope.mb_classifi==null}">
	                <li><a href="login.cc" alt="">장바구니</a></li>
	                <li><a href="login.cc" alt="">마이페이지</a></li>
	                <li><a href="login.cc" alt="">로그인</a></li>
	                <li><a href="join.cc" alt="">회원가입</a></li>
               	</c:if>
               	<c:if test="${sessionScope.mb_classifi!=null}">
	                <li><a href="cart.do" alt="">장바구니</a></li>
	                <li><a href="mypage.do" alt="">마이페이지</a></li>
	                <li><a href="qna.do" alt="">QnA</a></li>
	                <li>
	                	<a href="#" onclick="document.logoutForm.submit()"> 로그아웃 </a>
	                	<form action = "logout.do" name="logoutForm" method = 'post'>
							<input type = "hidden" name = "${_csrf.parameterName }" value = "${_csrf.token }"/>
						</form>
					</li>
	               <li> ${sessionScope.mb_id}님 환영합니다.</li>
               	</c:if>
            </ul>
        </div>
        <div class="logo"> <a href="index.cc"> <img src="/web_project/images/logo/logo2.jpg"></a></div>
        <div class="lnb">
            <ul>
            	<c:forEach var="i" items="${menu}">
            	<li><a href="category.cc?category=${i.category_id}">${i.category_id}</a>
		          	<ul class ="menu2_s submenu">
            		<c:forEach var="j" items="${i.getChild()}">
            			<li>
            				<a href="category.cc?category=${j.category_id}">${j.category_id}</a>
            			</li>	
            		</c:forEach>
            		</ul>
            	</li>
            	</c:forEach> 
	      	</ul>
        </div>
    </header>
</body>
</html>