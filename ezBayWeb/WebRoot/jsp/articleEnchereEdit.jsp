<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
   <title><bean:message key="articleEnchereEdit.titre" /></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  			
  </head>
  
	<body>
	<H1><bean:message key="articleEnchereEdit.titre" /></H1>
	<html:errors />
		<%-- create a html form --%>

		<bean:message key="articleEnchereEdit.libelleArticle" /> : <bean:write name="enchereForm" property="libelle" />
		<br />
		<logic:equal name="enchereForm" property="montantDerniereEnchere" value="0">
			<bean:message key="articleEnchereEdit.prixDepart" /> : <bean:write name="enchereForm" property="prixVente" />		
		</logic:equal>
		<logic:notEqual name="enchereForm" property="montantDerniereEnchere" value="0">
			<bean:message key="articleEnchereEdit.derniereEnchere" /> : <bean:write name="enchereForm" property="montantDerniereEnchere" />		
		</logic:notEqual>		
	<br />
		<html:form action="enchereSave">
			<bean:message key="articleEnchereEdit.montant" /> : <html:text property="stringMontantEnchereCourante" />
			<br>
			<html:hidden property="montantDerniereEnchere" />
			<html:hidden property="id" />
			<html:hidden property="prixVente" />
			<html:hidden property="libelle" />
			<html:submit><bean:message key="bouton.label.valider" /></html:submit>	
			</html:form>
	</body>
</html:html>
