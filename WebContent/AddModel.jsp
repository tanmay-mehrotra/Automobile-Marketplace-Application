<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
		<c:choose>
			<c:when test="${role=='D'}">
				<center>

				<h1 class="name">Add Model</h1>
				<br>
				<br>

				<h:panelGrid border="1" columns="2">
					<h:outputText value="Enter Model Name" />
					<h:inputText value="#{addModelMB.modelName}" required="true"
						requiredMessage="Enter Model Name" />

					<h:outputText value="Enter Price" />
					<h:inputText value="#{addModelMB.price}" required="true"
						requiredMessage="Enter Price" />

					<h:outputText value="Enter Fuel Type" />
					<h:inputText value="#{addModelMB.fuelType}" required="true"
						requiredMessage="Enter Fuel Type" />

					<h:outputText value="Enter Cubic Capacity" />
					<h:inputText value="#{addModelMB.cubicCapacity}" required="true"
						requiredMessage="Enter Cubic Capacity" />

					<h:outputText value="Enter Car Type" />
					<h:inputText value="#{addModelMB.carType}" required="true"
						requiredMessage="Enter Car Type" />

					<h:outputText value="Enter Variant" />
					<h:inputText value="#{addModelMB.variant}" required="true"
						requiredMessage="Enter Variant Type" />

					<h:commandButton type="submit" value="Add This Model"
						action="#{addModelMB.addModel}"></h:commandButton>
					<h:commandButton type="reset" value="Reset"></h:commandButton>
				</h:panelGrid><br>
				<br>
				<h:messages></h:messages></center>

				<h:outputText value="#{addModelMB.message}"></h:outputText>

			</c:when>
			<c:otherwise>
				<center><br>
				<br>
				<br>
				<br>
				<h:outputLabel value="You are not authorized to view this page"
					style="font-size: 24px; font-weight: bolder"></h:outputLabel></center>
			</c:otherwise>
		</c:choose>
	</h:form>
</f:view>
</body>
</html>