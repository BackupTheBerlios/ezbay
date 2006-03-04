
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
		<bean:message key="membreFiche.title" />
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
			<bean:message key="membreFiche.title" />
		</h2>
		<hr width="50%;" color=" #FDF3BF" size="3" />
		
		<table class="body_affichage_donnees">
			<tr>
				<td class="intitule">
					<bean:message key="membreFiche.pseudo" /> : 
				</td>
				<td>
					<bean:write name="membreForm" property="pseudo" />
				</td>
			</tr>
		</table>
		<br /><br />
		
		<hr width="50%;" color=" #FDF3BF" size="3" />
		<logic:notEmpty name="membreForm" property="articlesViewEnVente">
		<h2><bean:message key="membreFiche.mesArticles" /></h2>
		<table class="body_recherche_article">
			<tr class="body_recherche_article">
				<td>
					<bean:message key="articleList.libelle" />
				</td>
				<td>
					<bean:message key="articleList.marque" />
				</td>
				<td>
					<bean:message key="articleList.modele" />
				</td>
				<td>
					<bean:message key="articleList.anneeFabrication" />
				</td>
				<td>
					<bean:message key="articleList.prixActuel" />
				</td>
				<td>
					<bean:message key="articleList.nbEncheres" />
				</td>
				<td>
					<bean:message key="articleList.dateLimiteSimple" />
				</td>
			</tr>
			<logic:iterate name="membreForm" property="articlesViewEnVente" id="article">
			<tr>
				<td>
					<html:link action="article.do?do=showArticleFiche" paramName="article" paramProperty="id" paramId="id">
					<bean:write name="article" property="libelle" />
					</html:link>
				</td>
				<td>
					<bean:write name="article" property="marque" />
				</td>
				<td>
					<bean:write name="article" property="modele" />
				</td>
				<td>
					<bean:write name="article" property="anneeFabrication" />
				</td>
				<td>
					<logic:empty name="article" property="derniereEnchereMontant">
						<bean:write name="article" property="prixVente" />
					</logic:empty>
					<logic:notEmpty name="article" property="derniereEnchereMontant">
						<bean:write name="article" property="derniereEnchereMontant" />
					</logic:notEmpty>
					<bean:message key="general.label.devise" />
				</td>
				<td>
					<bean:write name="article" property="nbEncheres" />
				</td>
				<td>
					<bean:write name="article" property="formattedDateLimite" />
				</td>
			</tr>
			</logic:iterate>
			</table>
			<br />
			<hr width="50%;" color=" #FDF3BF" size="3" />
			<logic:notEmpty name="membreForm" property="articlesViewVendus">
				<h2><bean:message key="membreFiche.mesAvis" /></h2>
				<table class="body_recherche_article">
					<tr class="body_recherche_article">
						<td>
							<bean:message key="articleList.libelle" />
						</td>
						<td>
							<bean:message key="articleList.transaction.dateAcquisition" />
						</td>
						<td>
							<bean:message key="articleList.transaction.client.nom" />
						</td>
						<td>
							<bean:message key="articleList.transaction.avis" />
						</td>
					</tr>
					<logic:iterate name="membreForm" property="articlesViewVendus" id="article">
					<tr>
						<td>
							<bean:write name="article" property="libelle" />
						</td>
						<td>
							<bean:write name="article" property="transactionFormattedDate" />
						</td>
						<td>
							<bean:write name="article" property="acheteurPseudo" />
						</td>
						<td>
							<bean:write name="article" property="transactionAvis" />
						</td>
					</tr>
					</logic:iterate>
				</table>
			</logic:notEmpty>
			
		<br />
		</logic:notEmpty>
		<html:link href="javascript:history.back()">
			<bean:message key="link.retour" />
		</html:link>
	</center>
</body>
</html>
