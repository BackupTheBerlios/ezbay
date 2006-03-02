
<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>
		<bean:message key="membreFiche.title" />
	</title>
	<link rel="stylesheet" href="<html:rewrite page="/style.css"/>" type="text/css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="refresh" content="30">
</head>

<body>
	<center>
		<h2>
			<bean:message key="membreFiche.title" />
		</h2>
		<hr width="50%;" color=" #FDF3BF" size="3" />
		
		<table class="body_affichage_donnees">
			<tr>
				<td class="intitule">
					<bean:message key="membreFiche.pseudo" /> : 
				</td>
				<td>
					<bean:write name="membreForm" property="pseudo" />
				</td>
			</tr>
		</table>
		<br /><br />
		
		<hr width="50%;" color=" #FDF3BF" size="3" />
		<logic:notEmpty name="membreForm" property="articlesViewEnVente">
		<h2><bean:message key="membreFiche.mesArticles" /></h2>
		<table>
			<logic:iterate name="membreForm" property="articlesViewEnVente" id="article">
			<tr>
				<td colspan="2">
					<html:link action="article.do?do=showArticleFiche" paramName="article" paramProperty="id" paramId="id">
					<bean:write name="article" property="marque" />
					</html:link>
				</td>
			</tr>
			</logic:iterate>
		</table>
		</logic:notEmpty>
	</center>
</body>
</html>
