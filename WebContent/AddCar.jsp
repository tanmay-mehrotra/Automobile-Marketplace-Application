<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<h:form>

		<table width="779" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="113">
				<table width="779" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="504">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="left" class="name">Infy Auto Sell</td>
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
		<c:choose>
			<c:when test="${role=='D'}">
				<center>


				<h1>Add Car To Inventory</h1>

				<h:panelGrid border="1" columns="3">
					<h:outputText value="Enter Chassie No"></h:outputText>
					<h:inputText id="chassis" value="#{addCarMB.chassisNo}"
						required="true" requiredMessage="Enter Chassis No"
						validator="#{addCarMB.validateChassisNo}">
					</h:inputText>
					<h:message for="chassis"></h:message>
					<h:outputText value="Enter Model Id"></h:outputText>


					<h:selectOneMenu id="model" value="#{addCarMB.modelId}"
						valueChangeListener="#{addCarMB.getModelColor}"
						onchange="fnSubmit()" required="true"
						requiredMessage="Select Model Id">
						<f:selectItem itemLabel="-Select-" />
						<f:selectItems value="#{addCarMB.modelIdList}" />
					</h:selectOneMenu>
					<h:message for="model"></h:message>
					<h:outputText value="Enter Color"></h:outputText>
					<h:selectOneMenu id="color" value="#{addCarMB.colorId}"
						required="true" requiredMessage="Select Color">
						<f:selectItem itemLabel="-Select-" />
						<f:selectItems value="#{addCarMB.colorIdList}" />
					</h:selectOneMenu>
					<h:message for="color"></h:message>
				</h:panelGrid></center>
				<center><h:panelGroup>

					<h:commandButton value="Add Car" type="submit"
						action="#{addCarMB.addCar}"></h:commandButton>
					<h:commandButton value="Reset" type="reset"></h:commandButton>
				</h:panelGroup></center>

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