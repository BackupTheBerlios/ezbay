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
		<meta http-equiv="refresh" content="30">
	</head>
	<body>
		<center>
			<h2>
				<bean:message key="titre.application" />
				-
				<bean:message key="vendeur.titre.infos" />
			</h2>
			<hr width="50%;" color=" #FDF3BF" size="3" />
			<i>
				<bean:message key="vendeur.titre.infosCB" />
			</i>
			<br />
			<br />
			<table class="body_affichage_donnees">
				<tr>
					<td class="intitule">
						<bean:message key="vendeur.label.numCB" />
						:
						</b>
					</td>
					<td>
						<bean:write name="vendeurForm" property="numCBSimpleFormat" />
					</td>
				</tr>
				<tr>
					<td class="intitule">
						<bean:message key="vendeur.label.nomProprioCB" />
						:
						</b>
					</td>
					<td>
						<bean:write name="vendeurForm" property="nomProprioCB" />
					</td>
				</tr>
				<tr>
					<td class="intitule">
						<bean:message key="vendeur.label.dateExpirCB" />
						:
						</b>
					</td>
					<td>
						<bean:write name="vendeurForm" property="stringDateExpirCB" />
					</td>
				</tr>
				<tr>
					<td class="intitule">
						<bean:message key="vendeur.label.codeSecuCB" />
						:
						</b>
					</td>
					<td>
						<bean:write name="vendeurForm" property="codeSecuCB" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<html:link action="/myEzBay.do?do=showMyEzBay">
							<bean:message key="link.retour" />
						</html:link>
					</td>
					<td align="center">
						<html:link action="/vendeur.do?do=showEditVendeur">
							<bean:message key="link.vendeur.edit" />
						</html:link>
					</td>
				</tr>
			</table>
			<br /><br />
		</center>
		<br />
		<br />
	</body>
</html>
