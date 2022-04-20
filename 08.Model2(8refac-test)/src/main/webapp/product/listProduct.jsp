<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
<%@ page import="java.util.List"  %>

<%@ page import="com.model2.mvc.service.domain.Product" %>
<%@ page import="com.model2.mvc.service.domain.User" %>
<%@ page import="com.model2.mvc.common.Search" %>
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>

<%
	List<Product> list=(List<Product>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("resultPage");
	String menu = (String)request.getAttribute("menu");
	if(menu == null || menu.equals("")){
		menu = request.getParameter("menu");
	}
	
	Search search=(Search)request.getAttribute("search");
	User user=(User)session.getAttribute("user");
	
	
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
%>
 --%>


<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
	function fncGetList(currentPage, priceCondition) {
		var minCharge = document.detailForm.minCharge.value;
		var maxCharge = document.detailForm.maxCharge.value;
		
		if(parseInt(minCharge) > parseInt(maxCharge)) {
			alert("가격입력이 잘못되었습니다.");
			return;
		}
		
		document.getElementById("priceCondition").value = priceCondition;
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();		
	}
	
	function notLogin(){
		alert("로그인을 하셔야 합니다.");
		document.detailForm.action='/user/loginView.jsp';
		document.detailForm.submit();
	}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/product/listProduct" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
						<c:if test="${param.menu == 'manage'}">
							상품 관리
						</c:if>
						<c:if test="${param.menu == 'search'}">
							상품 목록
						</c:if>					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
				<option value="1" ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
				<option value="2" ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option>
			</select>
			<input 	type="text" name="searchKeyword" value="${! empty search.searchKeyword ? search.searchKeyword : ""}"  
							class="ct_input_g" style="width:200px; height:20px" >
		</td>
	</tr>
	
	<tr>
		<td align="right">
			가격 
			<input type="text" name="minCharge" value="${! empty search.minCharge ? search.minCharge : ""}"
							class="ct_input_g" style="width:100px; height:20px" > 
							~
			<input type="text" name="maxCharge" value="${! empty search.maxCharge ? search.maxCharge : ""}"
							class="ct_input_g" style="width:100px; height:20px" >
		</td>
	</tr>
	<tr>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetList('1','0');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table border="1">
	<tr>
		<td>
			<a href="javascript:fncGetList('1', '1');">가격높은순</a>
		</td>
		<td>
			<a href="javascript:fncGetList('1', '2');">가격낮은순</a>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:set var="i" value="0" />
	<c:forEach var="product" items="${list}">
		<c:set var="i" value="${i+1}"/>
		<tr class="ct_list_pop">
			<td align="center">${i}</td>
			<td></td>
				
				<td align="left">
					
					<c:if test="${!empty user}">
						<a href="/product/getProduct?prodNo=${product.prodNo}&menu=${param.menu}">${product.prodName}</a>
						
					</c:if>
					
					<c:if test="${empty user}">
						<a href="javascript:notLogin();">${product.prodName}</a>
					</c:if>
					
				</td>
			
			<td></td>
			<td align="left">${product.price}</td>
			<td></td>
			<td align="left">${product.manuDate}</td>
			<td></td>
			<td align="left">
			
				<jsp:include page="/product/transCodeProduct.jsp">
					<jsp:param value="${product.quantity}" name="quantity"/>
					<jsp:param value="${menu}" name="menu"/>
				</jsp:include>
				
			</td>	
		</tr>
		<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>	
	</c:forEach>
</table>

<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
			<input type="hidden" id="menu" name="menu" value="${param.menu}"/>
			<input type="hidden" id="priceCondition" name="priceCondition" value=""/>
			
			<jsp:include page="../common/pageNavigator.jsp"/>
			
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>