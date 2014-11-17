<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
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
	<center><h:form>

		<table width="779" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="113">
				<table width="779" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="504">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="name">Infy Auto Sell</td>
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

		<H2 style="color: #666666">Generate Quotation</H2>
		<H4 style="color: #666666">Customer Details</H4>

		<h:dataTable border="1" style="color:#666666" var="row"
			value="#{quotationMB.enquiryDetailsTO}">
			<h:column id="customerName">
				<f:facet name="header">
					<h:outputText value="CustomerName"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.prospectiveCustomerTO.customerName}"></h:outputText>
			</h:column>
			<h:column id="emailId">
				<f:facet name="header">
					<h:outputText value="Email Id"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.prospectiveCustomerTO.emailId}"></h:outputText>
			</h:column>
			<h:column id="phone">
				<f:facet name="header">
					<h:outputText value="Phone Number"></h:outputText>
				</f:facet>

				<h:outputText value="#{row.prospectiveCustomerTO.phoneNumber}"></h:outputText>
			</h:column>
		</h:dataTable>

		<H4 style="color: #666666">Model Details</H4>

		<h:dataTable border="1" style="color:#666666"
			value="#{quotationMB.enquiryDetailsTO}" var="row1">
			<h:column id="modelName">
				<f:facet name="header">
					<h:outputText value="Customer Name"></h:outputText>
				</f:facet>
				<h:outputText value="#{row1.modelTO.modelName}"></h:outputText>
			</h:column>
			<h:column id="price">
				<f:facet name="header">
					<h:outputText value="Price"></h:outputText>
				</f:facet>
				<h:outputText value="#{row1.modelTO.price}"></h:outputText>
			</h:column>

		</h:dataTable>

		<table>
			<tr>
				<td><h:outputLabel value="Select Offer :" style="color:#666666"></h:outputLabel></td>
				<td><h:selectOneMenu id="offer" value="#{quotationMB.offerId}"
					valueChangeListener="#{quotationMB.priceAfterDiscount1}"
					onchange="fnSubmit()" required="true"
					requiredMessage="Select Offer">
					<f:selectItem itemLabel="Select" itemValue="select" />
					<f:selectItems value="#{quotationMB.offerIdList}" />
				</h:selectOneMenu> <h:message for="offer"></h:message></td>
			</tr>
			<tr>
				<td><h:outputText value="Price After Discount:">
				</h:outputText></td>
				<td><h:outputText value="#{quotationMB.priceAfterDiscount}"
					style="background-color: White"></h:outputText></td>
			</tr>
		</table>

		<h:commandButton type="submit"
			action="#{quotationMB.generateQuotation}" value="Generate Quotation">
		</h:commandButton>
		<br>

		<h:outputText value="#{newJobsMB.message}"></h:outputText>
	</h:form></center>
</f:view>

</body>
</html>