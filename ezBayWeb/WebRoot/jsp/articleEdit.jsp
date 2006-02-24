<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<bean:define id="categories" name="categories" type="java.util.Collection" scope="session"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
   <title><bean:message key="articleEdit.title" /></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  			
  </head>
  
	<body>
	<center>
	<H1><bean:message key="articleEdit.title" /></H1>
	<html:errors />
		<%-- create a html form --%>
		<html:form action="articleSave">
			<%-- print out the form data --%>
			<table border="1">
				<tbody>
				<tr>
				<td><bean:message key="articleEdit.categorie" /> : </td>
				<td>
					<html:select property="categorieDTO.id">
						<html:options collection="categories" property="id" labelProperty="libelle" />
					</html:select>
				</td>
				</tr>
				
				<tr>
				<td><bean:message key="articleEdit.libelle" /> : </td>
				<td><html:text property="libelle" /></td>
				</tr>
				<tr>
				<td><bean:message key="articleEdit.marque" /> :</td>
				<td><html:text property="marque" /></td>
				</tr>
				<tr>
				<td><bean:message key="articleEdit.modele" /> :</td>
				<td><html:text property="modele" /></td>
				</tr>
				<tr>
				<td><bean:message key="articleEdit.prixVente" /> :</td>
				<td><html:text property="stringPrixVente" /></td>
				</tr>
				<tr>
				<td><bean:message key="articleEdit.anneeFabrication" /> :</td>
				<td><html:text property="anneeFabrication" /></td>
				</tr>
				<tr>
				<td><bean:message key="articleEdit.dateLimite" /> :</td>
				<td><html:text property="stringDateLimite" /></td>
				</tr>
				<tr>
				<td><bean:message key="articleEdit.description" /> :</td>
				<td><html:textarea property="description" rows="10" cols="50" /></td>
				</tr>
				</tbody>
			</table>
			<%-- set the parameter for the dispatch action --%>
			<html:hidden property="id" />
			<br>
			<%-- submit and back button --%>
			<html:submit><bean:message key="bouton.label.valider" /></html:submit>	
			</html:form>
	</center>
	</body>
</html:html>
