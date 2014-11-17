<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

<script type="text/javascript">
function fnsubmit(){
document.forms[0].submit();}
</script>

<body>
<f:view>

	<h:form style="height: 362px; width: 724px">
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
						<td class="toplinks"><a href="">Logout</a></td>
						<td width="1"></td>

					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td valign="top" id="main" />
			</tr>


		</table>
		<center><c:choose>
			<c:when test="${role=='E'}">
				<h2>Follow Up Quotations</h2>
				<br>
				<br>
				<h5>Customer Details <h:dataTable border="1" id="table1"
					value="#{updateQuotationMB.enquiryQuotationTO.prospectiveCustomerTO}"
					var="prosCustTO">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="Customer Name"></h:outputText>

						</f:facet>
						<h:outputText value="#{prosCustTO.customerName}" />
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="Email Id"></h:outputText>
						</f:facet>
						<h:outputText value="#{prosCustTO.emailId}" />
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="Phone Number"></h:outputText>
						</f:facet>
						<h:outputText value="#{prosCustTO.phoneNumber}" />
					</h:column>


				</h:dataTable></h5>
				<br>
				<h5>Model Details <h:dataTable border="1" id="table2"
					value="#{updateQuotationMB.enquiryQuotationTO.modelTO}"
					var="modelTO">
					<h:column id="column11">
						<f:facet name="header">
							<h:outputText value="Model Name"></h:outputText>

						</f:facet>
						<h:outputText value="#{modelTO.modelName}" />
					</h:column>
					<h:column id="column22">
						<f:facet name="header">
							<h:outputText value="Price"></h:outputText>
						</f:facet>
						<h:outputText value="#{modelTO.price}" />
					</h:column>
				</h:dataTable></h5>

				<br>
				<h5>FollowUp Details <h:dataTable border="1" id="table3"
					value="#{updateQuotationMB.enquiryQuotationTO.quotaionTO}"
					var="quotationTO">
					<h:column id="column111">
						<f:facet name="header">
							<h:outputText value="Quotation Id"></h:outputText>

						</f:facet>
						<h:outputText value="#{quotationTO.quotationId}" />
					</h:column>
					<h:column id="column222">
						<f:facet name="header">
							<h:outputText value="Quoted Price"></h:outputText>
						</f:facet>
						<h:outputText value="#{quotationTO.quotedPrice}" />
					</h:column>
				</h:dataTable></h5>
				<br>
				<br>
				<h:panelGrid border="1" columns="2">
					<h:outputText value="Select Offer"></h:outputText>
					<h:selectOneMenu value="#{updateQuotationMB.offerId}"
						valueChangeListener="#{updateQuotationMB.priceAfterDiscount}"
						onchange="fnsubmit()">
						<f:selectItem itemLabel="Select" itemValue="0" />
						<f:selectItems value="#{updateQuotationMB.offerIdList}" />
					</h:selectOneMenu>
					<h:outputText value="Price After Discount"></h:outputText>

					<h:outputText value="#{updateQuotationMB.discountedPrice}"></h:outputText>

				</h:panelGrid>
				<br>
				<h:commandButton value="Update Quotation" type="submit"
					action="#{updateQuotationMB.updateQuotation}"></h:commandButton>
				<br>
				<br>
				<br>
				<h:outputText value="#{updateQuotationMB.message}"></h:outputText>
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