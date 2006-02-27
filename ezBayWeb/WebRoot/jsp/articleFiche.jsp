
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
		<bean:message key="articleFiche.title" />
	</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="refresh" content="30">
</head>

<body>
	<center>
		<table>
			<tr>
				<td>
					<b>
						<bean:message key="articleFiche.categorie" />
						:
						<b>
				</td>
				<td>
					<bean:write name="articleForm" property="categorieDTO.libelle" />
				</td>
				<td>
					<html:link action="/membre.do?do=showMembreFiche" paramName="articleForm" paramProperty="vendeurId" paramId="vendeurId">
						<bean:message key="link.vendeurFiche" />
					</html:link>
					:
					<bean:write name="articleForm" property="membrePseudo" />
				</td>
			</tr>
			<tr>
				<td>
					<b>
						<bean:message key="articleFiche.libelle" />
						:
					</b>
				</td>
				<td>
					<bean:write name="articleForm" property="libelle" />
				</td>
				<td>
					<html:link action="/membre.do?do=showMembreFiche" paramName="articleForm" paramProperty="vendeurId" paramId="vendeurId">
						<bean:message key="link.clientFiche" />
					</html:link>
					:
					<bean:write name="articleForm" property="membrePseudo" />
				</td>
			</tr>
			<tr>
				<td>
					<b>
						<bean:message key="articleFiche.modele" />
						:
					</b>
				</td>
				<td>
					<bean:write name="articleForm" property="marque" />
				</td>
			</tr>
			<tr>
				<td>
					<b>
						<bean:message key="articleFiche.marque" />
						:
					</b>
				</td>
				<td>
					<bean:write name="articleForm" property="modele" />
				</td>
			</tr>
			<tr>
				<td>
					<b>
						<bean:message key="articleFiche.prixVente" />
						:
					</b>
				</td>
				<td>
					<bean:write name="articleForm" property="prixVente" /> <bean:message key="general.label.devise" />
				</td>
			</tr>
			<tr>
				<td>
					<b>
						<bean:message key="articleFiche.anneeFabrication" />
						:
					</b>
				</td>
				<td>
					<bean:write name="articleForm" property="anneeFabrication" />
				</td>
			</tr>
			<tr>
				<td>

					<b>
						<bean:message key="articleFiche.dateLimite" />
						:
					</b>
				</td>
				<td>
					<bean:write name="articleForm" property="stringDateLimite" />
				</td>
			</tr>
			<tr>
				<td>

					<b>
						<bean:message key="articleFiche.description" />
						:
					</b>
				</td>
				<td>
					<bean:write name="articleForm" property="description" />
				</td>
			</tr>
		</table>
		<hr width="50%" align="center"/>
<logic:notEmpty name="articleForm" property="enchereView">
		<b><bean:message key="articleFiche.derniereEnchere.titre" /></b>
		<table>
			<tr>
				<td>
					<b>
						<bean:message key="articleFiche.derniereEnchere.date" />
						:
						<b>
				</td>
				<td>
					<bean:write name="articleForm" property="enchereView.formattedDate" />
				</td>
			</tr>
			<tr>
				<td>
					<b>
						<bean:message key="articleFiche.derniereEnchere.montant" />
						:
						<b>
				</td>
				<td>
					<bean:write name="articleForm" property="enchereView.montant" /> <bean:message key="general.label.devise" />
				</td>
			</tr>			
			<tr>
				<td>
					<b>
						<bean:message key="articleFiche.derniereEnchere.encherisseur" />
						:
						<b>
				</td>
				<td>
					<bean:write name="articleForm" property="enchereView.membreDTO.pseudo" />
				</td>
			</tr>
			</table>
</logic:notEmpty>
<logic:empty name="articleForm" property="enchereView">
	<bean:message key="articleFiche.noEnchere" />
</logic:empty>


	<br />
		<br />
			<html:link action="/enchere.do?do=showEnchereForm" paramName="articleForm" paramProperty="id" paramId="articleId">
				<bean:message key="link.articleEncherir" />
			</html:link>
		<br />
		<br />
		</td>
		</tr>
		</table>
	</center>
</body>
</html:html>
