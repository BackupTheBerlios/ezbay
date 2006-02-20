
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>rechercheArticle.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
	<html:errors/>
		<%-- create a html form --%>
		<html:form action="rechercheArticle">	
						
				<br />
				<td align="right"><bean:message key="articleFiche.libelle"/>:</td>
				<td><html:text property="libelle" /></td>
				<br />
				<td align="right"><bean:message key="articleFiche.marque"/>:</td>
				<td><html:text property="marque" /></td>
				<br />
				<td align="right"><bean:message key="articleFiche.modele"/>:</td>
				<td><html:text property="modele" /></td>
				<br />				
				<td align="right"><bean:message key="articleFiche.anneeFabrication"/>:</td>
				<td><html:text property="anneeFabrication" /></td>
				<br />
				<td align="right"><bean:message key="articleFiche.dateLimite"/>:</td>
				<td><html:text property="dateLimite" /></td>
				<br />
				
				
							
			<br>
			
			<html:submit><bean:message key="boutton.recherche.article"/></html:submit>	
		</html:form>
			
	</body>
</html:html>
