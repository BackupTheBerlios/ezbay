
<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<head>
	<title>
		<bean:message key="membre.titre.infos" />
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
			<bean:message key="titre.application" />
			-
			<bean:message key="membre.titre.infos" />
		</h2>
		<hr width="50%;" color=" #FDF3BF" size="3" />
		<table class="body_affichage_donnees">
			<tr>
				<td class="intitule">
					<bean:message key="inscription.label.nom" />
					:
				</td>
				<td>
					<bean:write name="membreForm" property="nom" />
				</td>
			</tr>
			<tr>
				<td class="intitule">
					<bean:message key="inscription.label.prenom" />
					:
				</td>
				<td>
					<bean:write name="membreForm" property="prenom" />
				</td>
			</tr>
			<tr>
				<td class="intitule">
					<bean:message key="inscription.label.pseudo" />
					:
				</td>
				<td>
					<bean:write name="membreForm" property="pseudo" />
				</td>
			</tr>
			<tr>
				<td class="intitule">
					<bean:message key="inscription.label.email" />
					:
				</td>
				<td>
					<bean:write name="membreForm" property="email" />
				</td>
			</tr>
			<tr>
				<td class="intitule">
					<bean:message key="inscription.label.adresse" />
					:
				</td>
				<td>
					<bean:write name="membreForm" property="adresse" />
				</td>
			</tr>
			<tr>
				<td class="intitule">
					<bean:message key="inscription.label.codepostal" />
					:
				</td>
				<td>
					<bean:write name="membreForm" property="codePostal" />
				</td>
			</tr>
			<tr>
				<td class="intitule">
					<bean:message key="inscription.label.ville" />
					:
				</td>
				<td>
					<bean:write name="membreForm" property="ville" />
				</td>
			</tr>
			<tr>
				<td class="intitule">
					<bean:message key="inscription.label.pays" />
					:
				</td>
				<td>
					<bean:write name="membreForm" property="pays" />
				</td>
			</tr>
			<logic:notEmpty name="membreForm" property="telephoneFixe">
				<tr>
					<td class="intitule">
						<bean:message key="inscription.label.telephonefixe" />
						:
					</td>
					<td>
						<bean:write name="membreForm" property="telephoneFixe" />
					</td>
				</tr>
			</logic:notEmpty>
			<logic:notEmpty name="membreForm" property="telephonePortable">
				<tr>
					<td class="intitule">
						<bean:message key="inscription.label.telephoneportable" />
						:
					</td>
					<td>
						<bean:write name="membreForm" property="telephonePortable" />
					</td>
				</tr>
			</logic:notEmpty>
			<tr>
				<td class="intitule">
					<bean:message key="inscription.label.dateNaissance" />
					:
				</td>
				<td>
					<bean:write name="membreForm" property="stringDateNaissance" />
				</td>
			</tr>
			<tr>
				<td>
					<br>
					<br>
				</td>
			</tr>
			<tr>
				<td align="center">
					<html:link action="/myEzBay.do?do=showMyEzBay">
						<bean:message key="link.retour" />
					</html:link>
				</td>
				<td align="center">
					<html:link action="/membre.do?do=showEditMembre">
						<bean:message key="link.membre.edit" />
					</html:link>
					<br>
				</td>
			</tr>
		</table>
	</center>
</body>
</html:html>
