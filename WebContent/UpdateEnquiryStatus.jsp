<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javaScript">
function fnsubmit()
{
 document.forms[0].submit();
}
</script>
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
								<td class="name" align="left">Infy Auto Sell</td>
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



		<h1>Update Status</h1>
		<br>
		<br>

		<h:outputLabel value="Select Quotation Id:"></h:outputLabel>

		<h:selectOneMenu id="select"
			value="#{updateEnquiryStatusMB.quotationId}"
			valueChangeListener="#{updateEnquiryStatusMB.getQuotationDetails}"
			onchange="fnsubmit()" required="true"
			requiredMessage="Please enter valid value">
			<f:selectItem itemLabel="-select-" />
			<f:selectItems value="#{updateEnquiryStatusMB.quotationIdList}" />
		</h:selectOneMenu>

		<br>
		<br>
		<h:panelGrid border="1" columns="2">
			<h:outputLabel value="Customer Name"></h:outputLabel>
			<h:outputText
				value="#{updateEnquiryStatusMB.enquiryQuotationTO.prospectiveCustomerTO.customerName}"></h:outputText>
			<h:outputLabel value="Model name"></h:outputLabel>
			<h:outputText
				value="#{updateEnquiryStatusMB.enquiryQuotationTO.modelTO.modelName}"></h:outputText>
			<h:outputLabel value="Quotation Amount"></h:outputLabel>
			<h:outputText
				value="#{updateEnquiryStatusMB.enquiryQuotationTO.quotaionTO.quotedPrice}"></h:outputText>
		</h:panelGrid>
		<br>
		<br>
		<h:panelGroup>
			<h:commandButton value="Accept" type="submit"
				action="#{updateEnquiryStatusMB.acceptBooking}"></h:commandButton>
			<h:commandButton value="Reject" type="submit"
				action="#{updateEnquiryStatusMB.refuseBooking}"></h:commandButton>

		</h:panelGroup>
		<br>
		<br>
		<h:outputText value="#{updateEnquiryStatusMB.message}" />
		<br>
		<h:message for="select" />
	</h:form></center>
</f:view>
</body>
</html>