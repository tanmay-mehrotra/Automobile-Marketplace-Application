<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript">
function fnSubmit(){
document.forms[0].submit();
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="style/style.css" rel="stylesheet" type="text/css" />
<style>
body {
	margin-bottom: 0px;
	margin-left: 0px;
	margin-right: 0px;
	margin-top: 0px;
	background-repeat: repeat-x;
	background-color: silver;
	font: normal small Arial, Helvetica, sans-serif;
	color: #4E4E4E;
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
</head>
<body>


<f:view>

	<table width="779" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="113">
			<table width="779" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="504">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="name">Infy Auto Sales</td>
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
			<table width="779" border="0" cellspacing="0" cellpadding="0">
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
			<td valign="top" id="main"></td>
		</tr>


	</table>
	<center><h:form>
		<c:choose>
			<c:when test="${role=='E'}">
				<H2>Quotation Report</H2>




				<h:panelGrid border="1" columns="2" style="color:#666666">
					<h:outputText value="Quotation Id"></h:outputText>
					<c:out value="${quotationId}"></c:out>
					<h:outputText value="Customer Name"></h:outputText>
					<h:outputText
						value="#{quotationMB.enquiryDetailsTO.prospectiveCustomerTO.customerName}"></h:outputText>
					<h:outputText value="Model ID"></h:outputText>
					<h:outputText
						value="#{quotationMB.enquiryDetailsTO.modelTO.modelId}"></h:outputText>
					<h:outputText value="Actual Price"></h:outputText>
					<h:outputText value="#{quotationMB.enquiryDetailsTO.modelTO.price}"></h:outputText>
					<h:outputText value="Quoted Price"></h:outputText>
					<h:outputText value="#{quotationMB.priceAfterDiscount}"></h:outputText>
				</h:panelGrid>
				<br>
				<h:commandButton type="submit" value="Send Quotation"
					action="#{quotationMB.sendQuotation}" onclick="fnSubmit()" />
				<br>
				<br>
				<h:outputText value="#{quotationMB.message}"></h:outputText>
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