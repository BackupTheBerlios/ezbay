
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
					<bean:write name="articleForm" property="articleView.categorieDTO.libelle" />
				</td>
				<td>
					<bean:message key="articleFiche.vendeur.fiche" />
					&nbsp;:&nbsp;
					<html:link action="/membre.do?do=showMembreFiche" paramName="articleForm" paramProperty="articleView.vendeurMembreDTO.id" paramId="membreId">
						<bean:write name="articleForm" property="articleView.vendeurMembreDTO.pseudo" />
					</html:link>
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
					&nbsp;
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
					<bean:write name="articleForm" property="prixVente" />
					<bean:message key="general.label.devise" />
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
		<hr width="50%" align="center" />
		<logic:equal name="articleForm" property="articleView.enVente" value="true">
			<b>
				<bean:message key="articleFiche.encheres.historique" />
			</b>
			<br />
			<bean:write name="articleForm" property="articleView.nbEncheres" />&nbsp;<bean:message key="articleFiche.encheres.libelle" />
			<br />
			<logic:notEmpty name="articleForm" property="articleView.derniereEnchereView">
				<table>
					<tr>
						<td colspan="2" align="left">
							<b>
								<bean:message key="articleFiche.derniereEnchere.titre" />
							</b>
						</td>
					</tr>
					<tr>
						<td>
							<b>
								<bean:message key="articleFiche.derniereEnchere.date" />
								:
								<b>
						</td>
						<td>
							<bean:write name="articleForm" property="articleView.derniereEnchereView.formattedDate" />
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
							<bean:write name="articleForm" property="articleView.derniereEnchereView.montant" />
							<bean:message key="general.label.devise" />
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
							<html:link action="/membre.do?do=showMembreFiche" paramName="articleForm" paramProperty="articleView.derniereEnchereView.membreDTO.id" paramId="membreId">
								<bean:write name="articleForm" property="articleView.derniereEnchereView.membreDTO.pseudo" />
							</html:link>
						</td>
					</tr>
				</table>
			</logic:notEmpty>
			<br />
			<br />

			<html:link action="/enchere.do?do=showEnchereForm" paramName="articleForm" paramProperty="id" paramId="articleId">
				<bean:message key="link.articleEncherir" />
			</html:link>
		</logic:equal>
		<br />
		<br />
		</td>
		</tr>
		</table>
	</center>
</body>
</html:html>
