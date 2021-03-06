<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%--
<%@ page import="java.util.List"  %>
<%@ page import="com.model2.mvc.service.domain.Purchase" %>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>
<%@ page import="com.model2.mvc.common.Search" %>
<%@page import="com.model2.mvc.common.Page"%>

<%
	List<Purchase> list=(List<Purchase>)request.getAttribute("list");
	Search search=(Search)request.getAttribute("search");
	Page resultPage=(Page)request.getAttribute("resultPage");
	
%>
 --%>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
	function fncGetList(currentPage, priceCondition) {
		document.getElementById("priceCondition").value = priceCondition;
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/purchase/listPurchase" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">수량</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">구매취소</td>
		<td class="ct_line02"></td>
	</tr>
	<tr>
		<td colspan="13" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:set var="i" value="0" />
	<c:forEach var="purchase" items="${list}">
		<c:set var="i" value="${i+1}" />		
	
		<tr class="ct_list_pop">
			<td align="center">
				<a href="/getProduct.do?prodNo=${purchase.purchaseProd.prodNo}">${i}</a>
			</td>
			<td></td>
			<td align="left">
				<a href="/getUser.do?userId=${purchase.buyer.userId}">${purchase.buyer.userId}</a>
			</td>
			<td></td>
			<td align="left">${purchase.receiverName}</td>
			<td></td>
			<td align="left">${purchase.quantity+product.quantity}개</td>
			<td></td>
			<td align="left">현재 
			
				<c:if test="${fn:trim(purchase.tranCode) == '1'}">
					구매완료 
				</c:if>
				<c:if test="${fn:trim(purchase.tranCode) == '2'}">
					배송중인
				</c:if>
				<c:if test="${fn:trim(purchase.tranCode) == '3'}">
				 	배송완료 
				 </c:if>
				
			상태 입니다.</td>
			<td></td>
			<td align="left"> 
			<c:if test="${fn:trim(purchase.tranCode) == '1'}">
					<a href="/purchase/updatePurchase?tranNo=${purchase.tranNo}&prodNo=${purchase.purchaseProd.prodNo}">수정</a>
			</c:if>
				
			<c:if test="${fn:trim(purchase.tranCode) == '2'}">
					<a href="/purchase/updateTranCode?tranNo=${purchase.tranNo}&tranCode=3&menu=search">배송완료</a>
			</c:if>
			</td>
			<td></td>
			<c:if test="${fn:trim(purchase.tranCode) == '1'}">
				<td align="left">
					<a href="/purchase/deletePurchase?tranNo=${purchase.tranNo}">취소</a>
				</td>
			</c:if>
			<c:if test="${fn:trim(purchase.tranCode) != '1'}">
				<td align="left">
					취소가 불가능합니다.
				</td>
			</c:if>
		</tr>
		<tr>
			<td colspan="13" bgcolor="D6D7D6" height="1"></td>
		</tr>
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		<input type="hidden" id="currentPage" name="currentPage" value=""/>
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