
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<head>
	<html:base />

	<title>
		<bean:message key="articleFiche.title" />
	</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>

<body>
	<table>
		<tr>
			<td>
				<h3>
					<bean:message key="articleFiche.categorie" />
					:
				</h3>
			</td>
			<td>
				<bean:write name="articleForm" property="categorieDTO.libelle" />
			</td>
		</tr>
		<tr>
			<td>
				<h3>
					<bean:message key="articleFiche.libelle" />
					:
				</h3>
			</td>
			<td>
				<bean:write name="articleForm" property="libelle" />
			</td>
		</tr>
		<tr>
			<td>
				<h3>
					<bean:message key="articleFiche.modele" />
					:
				</h3>
			</td>
			<td>
				<bean:write name="articleForm" property="marque" />
			</td>
		</tr>
		<tr>
			<td>
				<h3>
					<bean:message key="articleFiche.marque" />
					:
				</h3>
			</td>
			<td>
				<bean:write name="articleForm" property="modele" />
			</td>
		</tr>
		<tr>
			<td>
				<h3>
					<bean:message key="articleFiche.prixVente" />
					:
				</h3>
			</td>
			<td>
				<bean:write name="articleForm" property="prixVente" />
			</td>
		</tr>
		<tr>
			<td>
				<h3>
					<bean:message key="articleFiche.anneeFabrication" />
					:
				</h3>
			</td>
			<td>
				<bean:write name="articleForm" property="anneeFabrication" />
			</td>
		</tr>
		<tr>
			<td>

				<h3>
					<bean:message key="articleFiche.dateLimite" />
					:
				</h3>
			</td>
			<td>
				<bean:write name="articleForm" property="stringDateLimite" />
			</td>
		</tr>
		<tr>
			<td>

				<h3>
					<bean:message key="articleFiche.description" />
					:
				</h3>
			</td>
			<td>
				<bean:write name="articleForm" property="description" />
			</td>
		</tr>
	</table>
	<br />
	<br />
	<html:link action="/membre.do?do=showMembreFiche" paramName="articleForm" paramProperty="vendeurId" paramId="vendeurId">
		<bean:message key="link.vendeurFiche" />
	</html:link>:<bean:write name="articleForm" property="membrePseudo" />
	<br />
	<br />
	<html:link action="/membre.do?do=showMembreFiche" paramName="articleForm" paramProperty="vendeurId" paramId="vendeurId">
		<bean:message key="link.clientFiche" />
	</html:link>:<bean:write name="articleForm" property="membrePseudo" />
	</td>
	</tr>
	</table>
</body>
</html:html>
