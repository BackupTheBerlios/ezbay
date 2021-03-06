
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
			<bean:message key="titre.application" />
			-
			<bean:message key="inscription.label" />
		</title>
		<link rel="stylesheet" href="<html:rewrite page="/style.css"/>" type="text/css">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body>
		<center>
			<h2>
				<bean:message key="titre.application" />
				-
				<bean:message key="vendeur.titre" />
			</h2>
			<hr width="50%;" color=" #FDF3BF" size="3" />
			<html:errors />
			<i><bean:message key="vendeur.titre.infosCB" /></i>
			<html:form action="/vendeurSave">
				<table class="body_affichage_donnees">
					<tr>
						<td class="intitule">
							<bean:message key="vendeur.label.numCB" />
							:
						</td>
						<td>
							<html:text property="numCB" maxlength="16" size="16" />
						</td>
					</tr>
					<tr>
						<td class="intitule">
							<bean:message key="vendeur.label.nomProprioCB" />
							:
						</td>
						<td>
							<html:text property="nomProprioCB" />
						</td>
					</tr>
					<tr>
						<td class="intitule">
							<bean:message key="vendeur.label.dateExpirCB" />
							:
						</td>
						<td>
							<html:text property="stringDateExpirCB" maxlength="10" size="10" />
						</td>
					</tr>
					<tr>
						<td class="intitule">
							<bean:message key="vendeur.label.codeSecuCB" />
							:
						</td>
						<td>
							<html:text property="codeSecuCB" maxlength="3" size="3" />
						</td>
					</tr>
				</table>
				<br />
				<br />
				<html:submit>
					<bean:message key="bouton.label.valider" />
				</html:submit>

				<html:hidden property="id" />
			</html:form>
		</center>
	</body>
</html>
