<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function fnSubmit()
{
	document.forms[0].submit();
}
</script>
<style>
body {
	margin-bottom: 0px;
	margin-left: 0px;
	margin-right: 0px;
	margin-top: 0px;
	background-repeat: repeat-x;
	background-color: silver;
	font: normal small Arial, Helvetica, sans-serif;
	color: #ffffff;
	text-align: justify;
	font-size: 16px;
}

.heading {
	font-weight: bold;
	color: #4477AA;
	height: 35px;
	padding-left: 15px;
}

.footerlinks {
	font-family: tahoma;
	font-size: 11px;
	color: #ffffff;
	text-align: center;
	text-decoration: none;
}

.footerlinks:hover {
	font-family: tahoma;
	font-size: 11px;
	color: #FFA727;
	text-decoration: none;
}

a {
	color: #899193;
	font-weight: bold;
	text-decoration: none;
}

a:hover {
	color: #727272;
	text-decoration: underline;
}

.border {
	border: 1px solid #4477AA;
}

.toplinks {
	width: 155px;
	text-align: center;
	font-size: 12px;
	font-weight: bold;
	line-height: 30px;
	height: 30px;
}

.toplinks a {
	background-color: #878F91;
	color: #ffffff;
	display: block;
	height: 30px;
	text-transform: uppercase;
	text-decoration: none;
}

.toplinks a:hover {
	background-color: #7596C3;
	color: #ffffff;
	text-decoration: none;
}

#main {
	padding-left: 10px;
	padding-right: 10px;
	padding-top: 10px;
	padding-bottom: 10px;
	background-color: #ffffff;
}

#quickheading {
	font-weight: bold;
	background-color: #5B83B6;
	height: 32px;
	padding-left: 15px;
	text-transform: uppercase;
	color: #ffffff;
}

.quicklinks {
	color: #293A72;
	padding-left: 15px;
	text-decoration: underline;
}

.quicklinks:hover {
	color: #000000;
	text-decoration: underline;
}

.name {
	font-family: "century Gothic";
	font-size: 32px;
	color: #ffffff;
	padding-bottom: 5px;
	padding-left: 15px;
}

.tag {
	color: #ffffff;
	padding-left: 17px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>
	<center><h:form>
		<table style="border: 0; width: 100%">

			<tr>
				<td colspan="5">
				<H1>Infy Auto Sales</H1>
				</td>
			</tr>
			<tr style="text-decoration: none">
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
		<c:choose>
			<c:when test="${role=='D'}">

				<h1>Withdraw Model</h1>
				<br>
				<br>
				<h:panelGrid border="1" columns="3">
					<h:outputText value="Select Model"></h:outputText>
					<h:selectOneMenu id="selectmodel"
						value="#{withdrawModelMB.modelId}"
						valueChangeListener="#{withdrawModelMB.getModel}"
						onchange="fnSubmit()" required="true"
						requiredMessage="Select Particular model">
						<f:selectItem itemLabel="-Select-" />
						<f:selectItems value="#{withdrawModelMB.modelIdList}" />
					</h:selectOneMenu>
					<h:message for="selectmodel"></h:message>
					<h:outputText value="Model Name"></h:outputText>
					<h:inputText value="#{withdrawModelMB.modelTO.modelName}"
						disabled="true"></h:inputText>
					<h:outputText value=""></h:outputText>
					<h:outputText value="Variant"></h:outputText>
					<h:inputText value="#{withdrawModelMB.modelTO.variant}"
						disabled="true"></h:inputText>
					<h:outputText value=""></h:outputText>
					<h:outputText value="Fuel Type"></h:outputText>
					<h:inputText value="#{withdrawModelMB.modelTO.fuelType}"
						disabled="true"></h:inputText>
					<h:outputText value=""></h:outputText>
					<h:outputText value="Cubic Capacity"></h:outputText>
					<h:inputText value="#{withdrawModelMB.modelTO.cubicCapacity}"
						disabled="true"></h:inputText>
					<h:outputText value=""></h:outputText>
					<h:outputText value="Enter Price"></h:outputText>
					<h:inputText id="modelprice"
						value="#{withdrawModelMB.modelTO.price}" disabled="true"></h:inputText>
					<h:message for="modelprice"></h:message>
				</h:panelGrid>
				<h:commandButton action="#{withdrawModelMB.withdrawModel}"
					type="submit" value="Withdraw">
				</h:commandButton>
				<h:commandButton type="reset" value="Reset">
				</h:commandButton><br>
				<h:outputText value="#{withdrawModelMB.message}"></h:outputText>
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


	</h:form></center>

</f:view>
</body>
</html>