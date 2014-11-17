<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link type="text/css" rel="stylesheet" href="style.css"></link>
</head>
<body class="body">
<f:view>
	<h:form>
		<table width="779" border="0" align="center" cellpadding="0"
			cellspacing="0" align="left">
			<tr>
				<td height="113">
				<table width="779" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="504">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="name" align="left" class="heading">Infy Auto
								Sell</td>
							</tr>

						</table>
						</td>
						<td width="275"></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>
				<table width="779" border="0" cellspacing="0" cellpadding="0"
					class="border">
					<tr>
						<td class="toplinks"><a href="Home.jsp">Homepage</a></td>
						<td width="1"></td>
						<td class="toplinks"><a href="SearchCar.jsp">Search Cars</a></td>
						<td width="1"></td>
						<td class="toplinks"><a href="Services.jsp">Services</a></td>
						<td width="1"></td>
						<td class="toplinks"><a href="AboutUs.jsp">About Us</a></td>
						<td width="1"></td>
						<td class="toplinks"><a href="LogOut.jsp">Logout</a></td>
						<td width="1"></td>

					</tr>
				</table>
				</td>
			</tr>
			<tr>
			</tr>

		</table>
		<center><c:choose>
			<c:when test="${role=='C'}">
				<h1>Check Status</h1>
				<h:selectOneMenu id="cs" value="#{carStatusMB.chassisNo}"
					valueChangeListener="#{carStatusMB.getCar}"
					onchange="javascript:submit();">
					<f:selectItem itemLabel="-Select-" />
					<f:selectItems value="#{carStatusMB.chassisNoList}" />
				</h:selectOneMenu>

				<br>
				<br>
				<br>
				<h:panelGrid border="1" columns="2">
					<h:outputText value="Chassis No" />
					<h:outputText value="#{carStatusMB.carTo.chassisNo}"></h:outputText>
					<h:outputText value="Registration No" />
					<h:outputText value="#{carStatusMB.carTo.registrationNo}"></h:outputText>
					<h:outputText value="Date Of Delivery" />
					<h:outputText value="#{carStatusMB.carTo.dateOfDelivery}">
						<f:convertDateTime type="date" pattern="MMM d, yyyy" />
					</h:outputText>

				</h:panelGrid>
				<br>
				<br>
				<h:outputText value="#{carStatusMB.message}"></h:outputText>
			</c:when>
			<c:otherwise>
				<center><br>
				<br>
				<br>
				<br>
				<h:outputLabel value="You are not authorized to view this page"
					style="font-size: 24px; font-weight: bolder"></h:outputLabel></center>
			</c:otherwise>
		</c:choose></center>
	</h:form>
</f:view>
</body>
</html>