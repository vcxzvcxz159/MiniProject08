<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%--
<%@ page import="com.model2.mvc.service.domain.Product" %>
<%@ page import="com.model2.mvc.service.purchase.dao.*" %>

<%
	String proTranCode = request.getParameter("proTranCode");
	String prodNo = request.getParameter("prodNo");
	String menu = request.getParameter("menu");
	
	System.out.println("transCodeProduct proTranCode : " + proTranCode);
%>
 --%>


<!DOCTYPE html>
<html>
<head>
<title>상품 상태</title>
</head>
<body>
	<c:if test="${param.quantity > 0}">
		판매중 
	</c:if>
	
	<c:if test="${param.quantity < 1}">
		재고없음 
	</c:if>
	
	<c:if test="${param.menu == 'manage'}">[수량 : ${param.quantity}]</c:if>
</body>
</html>