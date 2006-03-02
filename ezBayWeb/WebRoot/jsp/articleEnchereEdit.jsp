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
	<meta http-equiv="refresh" content="30">
</head>

<body>
	<center>
		<h2>
			<bean:message key="articleEnchereEdit.titre" />
		</h2>
		<br />
		<hr width="50%;" color=" #FDF3BF" size="3" />
		<br />
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
				<logic:empty name="enchereForm" property="enchereView">
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
				</logic:empty>
				<logic:notEmpty name="enchereForm" property="enchereView">
				<tr>
					<td class="intitule">
						<bean:message key="articleEnchereEdit.derniereEnchere" /> :
					</td>
					<td>
						<bean:write name="enchereForm" property="enchereView.montant" />
					</td>
					<td>
						<bean:message key="general.label.devise" />
					</td>
				</tr>
				</logic:notEmpty>
				<tr>	
					<td class="intitule">
						<bean:message key="articleEnchereEdit.montant" /> :
					</td>
					<td>
						<html:text property="stringMontantEnchereCourante" />
					</td>
				</tr>
			</table>
			<br /><br />
			<html:hidden property="montant" />
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
