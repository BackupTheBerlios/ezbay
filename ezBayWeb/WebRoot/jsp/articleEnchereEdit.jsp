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
		<bean:message key="articleEnchereEdit.titre" />
	</title>
	<link rel="stylesheet" href="<html:rewrite page="/style.css"/>" type="text/css">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>

<body>
	<center>
		<h2>
			<bean:message key="articleEnchereEdit.titre" />
		</h2>
		<hr width="50%;" color=" #FDF3BF" size="3" />
		<html:errors />
		<%-- create a html form --%>
		<html:form action="enchereSave">
			<table class="body_affichage_donnees"> 
				<tr>
					<td class="intitule">
						<bean:message key="articleEnchereEdit.libelleArticle" /> :
					</td>
					<td>
						<bean:write name="enchereForm" property="libelle" />
					</td>	
				</tr>
				<logic:equal name="enchereForm" property="montantDerniereEnchere" value="0">
				<tr>
					<td class="intitule">
						<bean:message key="articleEnchereEdit.prixDepart" /> :
					</td>
					<td>
						<bean:write name="enchereForm" property="prixVente" />
					</td>
					<td>
						<bean:message key="general.label.devise" />
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual name="enchereForm" property="montantDerniereEnchere" value="0">
				<tr>
					<td class="intitule">
						<bean:message key="articleEnchereEdit.derniereEnchere" /> :
					</td>
					<td>
						<bean:write name="enchereForm" property="montantDerniereEnchere" />
					</td>
					<td>
						<bean:message key="general.label.devise" />
					</td>
				</tr>
				</logic:notEqual>
				<tr>	
					<td class="intitule">
						<bean:message key="articleEnchereEdit.montant" /> :
					</td>
					<td>
						<html:text property="stringMontantEnchereCourante" />
					</td>
					<td>
						<bean:message key="general.label.devise" />
					</td>
				</tr>
			</table>
			<br /><br />
			<html:hidden property="montantDerniereEnchere" />
			<html:hidden property="id" />
			<html:hidden property="prixVente" />
			<html:hidden property="libelle" />
			<html:submit><bean:message key="bouton.label.valider" /></html:submit>
		</html:form>
		<br />
		<html:link action="article.do?do=showArticleFiche" paramName="enchereForm" paramProperty="id" paramId="id">
			<bean:message key="articleEnchereEdit.link.articleFiche" />
		</html:link>
	</center>
</body>
</html>
