<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

<html> 
	<head>
		<title>JSP for myEzBayForm form</title>
	</head>
	<body>
	<bean:message key="titre.application"/><br />
	<br />
	<br/>
	 
	 <bean:message key="myEzBay.menu.sectionClient.titre"/><br />
	 <html:link action="/membre.do?do=showInformations"><bean:message key="myEzBay.menu.sectionClient.link.infos"/></html:link><br>
	 <html:link action="#"><bean:message key="myEzBay.menu.sectionClient.link.achats"/></html:link><br>
	 <html:link action="#"><bean:message key="myEzBay.menu.sectionClient.link.encheres"/></html:link><br>
	 
<br /><br />
<bean:message key="myEzBay.menu.sectionVendeur.titre"/><br />
	<logic:notEmpty name="myEzBayForm" property="vendeurDTO"> 
		<html:link action="/vendeur.do?do=showInformations"><bean:message key="myEzBay.menu.sectionVendeur.link.infos"/></html:link><br/>
		<html:link action="/vendeur.do?do=showArticles"><bean:message key="myEzBay.menu.sectionClient.link.articles"/></html:link><br/>
		<html:link action="/article.do?do=showEdit"><bean:message key="myEzBay.menu.sectionClient.link.miseEnVente"/></html:link><br/>
	 </logic:notEmpty>
	 <logic:empty name="myEzBayForm" property="vendeurDTO"> 
		<html:link href="jsp/vendeurEdit.jsp"><bean:message key="myEzBay.menu.sectionVendeur.link.inscription"/></html:link><br/>
	</logic:empty>
	<html:link action="/myEzBay.do?do=deconnect"><bean:message key="link.deconnexion"/></html:link>
	</body>
</html>
