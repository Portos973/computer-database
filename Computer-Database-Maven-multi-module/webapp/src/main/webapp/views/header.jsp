<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/pagination.tld" prefix="mylib"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a> <span style="float: right"> <a href="?lang=	en"><img
					src="images/en_flag.png"></a> | <a href="?lang=fr"><img
					src="images/fr_flag.png"></a>
			</span>
		</div>

	</header>


	<!-- Logout part -->

	<p style="float: left; color: #CCC;">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
                   Welcome : ${pageContext.request.userPrincipal.name} |
                </c:if>
		<a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
	</p>