 
<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for avisForm form</title>
	</head>
	<body>
		<html:form action="/avis">
			<bean:write name="avisForm" property="articleId"/>
			<html:select property="avis">
				<html:option value="tresBien"><bean:message key="clientAchats.avis.titre.tresBien" /></html:option>
				<html:option value="bien"><bean:message key="clientAchats.avis.titre.bien" /></html:option>
				<html:option value="passable"><bean:message key="clientAchats.avis.titre.passable" /></html:option>
				<html:option value="mauvais"><bean:message key="clientAchats.avis.titre.mauvais" /></html:option>				
			</html:select>
			<html:hidden property="articleId" />
			<html:hidden property="do" value="saveAvis"/>
			<html:submit/>
		</html:form>
	</body>
</html>
