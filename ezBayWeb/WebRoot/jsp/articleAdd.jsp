
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
    
    <title><bean:message key="articleAdd.title" /></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
	<body>
		<%-- create a html form --%>
		<html:form action="articleEdit">
			<%-- print out the form data --%>
			<table border="1">
				<tbody>
				<tr>
				<td>Libbellé : </td>
				<td><html:text property="libelle" /></td>
				</tr>
				<tr>
				<td>Marque :</td>
				<td><html:text property="marque" /></td>
				</tr>
				<tr>
				<td>Modèle :</td>
				<td><html:checkbox property="modele" /></td>
				</tr>
				<tr>
				<td>Prix de Vente :</td>
				<td><html:checkbox property="prixVente" /></td>
				</tr>
				<tr>
				<td>Année de fabrication :</td>
				<td><html:checkbox property="anneeFabrication" /></td>
				</tr>
				<tr>
				<td>date limite :</td>
				<td><html:checkbox property="dateLimite" /></td>
				</tr>
				</tbody>
			</table>
			<%-- set the parameter for the dispatch action --%>
			<html:hidden property="do" value="saveArticle" />
			<br>
			<%-- submit and back button --%>
			<html:button property="back" onclick="history.back();">Back</html:button>
			&nbsp;
			<html:submit>Save</html:submit>	
			</html:form>
	</body>
</html:html>
