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
	<center><h:form>
		<H2 style="color: #666666">Search Cars</H2>
		<h:selectOneRadio value="#{searchMB.criteria}" id="carType"
			valueChangeListener="#{searchMB.searchCriteria}" onclick="fnSubmit()"
			required="true" requiredMessage="Select Search By Criteria">
			<f:selectItem itemLabel="ByModel" itemValue='m' />
			<f:selectItem itemLabel="ByPriceRange" itemValue='p' />
			<f:selectItem id="carType3" itemLabel="ByCarType" itemValue='t' />
		</h:selectOneRadio>
		<h:message for="carType"></h:message>
		<br>

		<h:selectOneMenu value="#{searchMB.modelName}" id="criteria"
			valueChangeListener="#{searchMB.searchCars}" onchange="fnSubmit()"
			required="true" requiredMessage="Select Criteria">
			<f:selectItem itemLabel="Select" />
			<f:selectItems value="#{searchMB.criteriaList}" />
		</h:selectOneMenu>
		<h:message for="criteria"></h:message>
		<br>
		<br>
		<h:dataTable border="1" style="color:#666666" var="row"
			value="#{searchMB.modelList}">
			<h:column id="modelName">
				<f:facet name="header">
					<h:outputText value="Model Name"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.modelName}"></h:outputText>
			</h:column>
			<h:column id="cubicCapacity">
				<f:facet name="header">
					<h:outputText value="Cubic Capacity"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.cubicCapacity}"></h:outputText>
			</h:column>
			<h:column id="fuelType">
				<f:facet name="header">
					<h:outputText value="Fuel type"></h:outputText>
				</f:facet>

				<h:outputText value="#{row.fuelType}"></h:outputText>
			</h:column>

			<h:column id="price">
				<f:facet name="header">
					<h:outputText value="Price"></h:outputText>
				</f:facet>

				<h:outputText value="#{row.price}"></h:outputText>
			</h:column>

			<h:column id="variant">
				<f:facet name="header">
					<h:outputText value="Variant"></h:outputText>
				</f:facet>

				<h:outputText value="#{row.variant}"></h:outputText>
			</h:column>


		</h:dataTable>
		<br>
		<table>
			<tr>

				<td><h:selectOneMenu id="model" value="#{searchMB.modelId}"
					required="true" requiredMessage="Select Model">
					<f:selectItem itemLabel="Select" />
					<f:selectItems value="#{searchMB.modelIdList}" />
				</h:selectOneMenu> <h:message for="model"></h:message></td>
			</tr>


		</table>
		<br>



		<h:commandButton type="submit" action="#{searchMB.raiseEnquiry}"
			value="Get Quotation"></h:commandButton>
		<br>

		<h:outputText value="#{searchMB.message}"></h:outputText>


	</h:form></center>
</f:view>

</body>
</html>