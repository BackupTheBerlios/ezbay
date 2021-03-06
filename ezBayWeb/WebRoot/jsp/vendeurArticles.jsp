
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
		<bean:message key="vendeurArticles.titre" />
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
	<h2><bean:message key="titre.application" /> - <bean:message key="vendeurArticles.titre" />
	</h2>
		<hr width="50%;" color=" #FDF3BF" size="3" />
	<br />	
	</center>
	
	<html:errors />
	<h3>
		<bean:message key="vendeurArticles.articlesEnAttente.titre" />
	</h3>
	<logic:empty name="vendeurForm" property="articlesEnAttente">
		<div><bean:message key="vendeurArticles.articlesEnAttente.noArticle" /></div><br />
	</logic:empty>
	<logic:notEmpty name="vendeurForm" property="articlesEnAttente">
		<table class="body_mes_articles">
			<tr class="body_mes_articles_ligne_foncee">
				<td>
					<bean:message key="articleList.libelle" />
				</td>
				<td>
					<bean:message key="articleList.prixVente" />
				</td>
				<td>
					<bean:message key="articleList.dateLimiteSimple" />
				</td>
			</tr>
			<logic:iterate name="vendeurForm" property="articlesEnAttente" id="article">
				<tr>
					<%-- article informations --%>
					<td align="left">
						<html:link action="article.do?do=showEdit" paramName="article" paramProperty="id" paramId="id">
							<bean:write name="article" property="libelle" />
						</html:link>
					</td>
					<td>
						<bean:write name="article" property="prixVente" />
						<bean:message key="general.label.devise" />
					</td>
					<td>
						<bean:write name="article" property="formattedDateLimite" />
					</td>
					<td>
						<html:link action="/article.do?do=supprimerArticle" paramName="article" paramProperty="id" paramId="id">
							<bean:message key="vendeurArticles.link.supprimer" />
						</html:link>
					</td>
					<td>
						<html:link action="/article.do?do=mettreEnVenteArticle" paramName="article" paramProperty="id" paramId="id">
							<bean:message key="vendeurArticles.link.mettreEnVente" />
						</html:link>
					</td>
				</tr>
			</logic:iterate>
		</table>
	</logic:notEmpty>
	<br />
	<h3>
		<bean:message key="vendeurArticles.articlesEnVente.titre" />
	</h3>
	<logic:empty name="vendeurForm" property="articlesEnVente">
		<div><bean:message key="vendeurArticles.articlesEnVente.noArticle" /></div><br />
	</logic:empty>
	<logic:notEmpty name="vendeurForm" property="articlesEnVente">
		<table class="body_mes_articles">
				<tr class="body_mes_articles_ligne_foncee">
					<td>
						<bean:message key="articleList.libelle" />
					</td>
					<td>
						<bean:message key="articleList.prixDepart" />
					</td>
					<td>
						<bean:message key="articleList.dateLimiteSimple" />
					</td>
					<td>
						<bean:message key="articleList.nbEncheres" />
					</td>
				</tr>
				<logic:iterate name="vendeurForm" property="articlesEnVente" id="article">
					<tr>
						<%-- article informations --%>
					<td align="left">
						<html:link action="article.do?do=showArticleFiche" paramName="article" paramProperty="id" paramId="id">
							<bean:write name="article" property="libelle" />
						</html:link>
					</td>
					<td>
						<bean:write name="article" property="prixVente" />
						<bean:message key="general.label.devise" />
					</td>
					<td>
						<bean:write name="article" property="formattedDateLimite" />
					</td>
					<td>
						<bean:write name="article" property="nbEncheres" />
					</td>
					<logic:empty name="article" property="derniereEnchereMontant">
					<td></td><td></td><td></td>
					</logic:empty>
					<logic:notEmpty name="article" property="derniereEnchereMontant">
					<td align="left">
						<bean:write name="article" property="encherisseurPseudo" /><br />
						<bean:write name="article" property="derniereEnchereFormattedDate" /><br />
						<bean:write name="article" property="derniereEnchereMontant" />
						<bean:message key="general.label.devise" />
					</td>
					</logic:notEmpty>
					<logic:empty name="article" property="derniereEnchereMontant">
					<td>
						<html:link action="/article.do?do=retirerArticle" paramName="article" paramProperty="id" paramId="id">
							<bean:message key="vendeurArticles.link.retirer" />
						</html:link>
					</td>
					</logic:empty>
				</tr>
			</logic:iterate>
		</table>
	</logic:notEmpty>
	<br />
	<h3>
		<bean:message key="vendeurArticles.articlesVendus.titre" />
	</h3>
	<%-- set the header --%>
	<logic:empty name="vendeurForm" property="articlesVendus">
		<div><bean:message key="vendeurArticles.articlesVendus.noArticle" /></div><br />
	</logic:empty>
	<logic:notEmpty name="vendeurForm" property="articlesVendus">
		<table class="body_mes_articles">
			<tr class="body_mes_articles_ligne_foncee">
				<td>
					<bean:message key="articleList.libelle" />
				</td>
				<td>
					<bean:message key="vendeurArticles.articlesVendus.vente.titre" />
				</td>
				<td>
					<bean:message key="vendeurArticles.articlesVendus.avis.titre" />
				</td>				
			</tr>
			<logic:iterate name="vendeurForm" property="articlesVendus" id="article">
				<tr>
					<%-- article informations --%>
					<td align="left">
						<html:link action="article.do?do=showArticleFiche" paramName="article" paramProperty="id" paramId="id">
							<bean:write name="article" property="libelle" />
						</html:link>
					</td>
					<td align="left">
						<bean:write name="article" property="transactionFormattedDate" /><br />
						<bean:write name="article" property="transactionMontant" /><br />
						<html:link action="/membre.do?do=showMembreFiche" paramName="article" paramProperty="acheteurMembreId" paramId="membreId">
							<bean:write name="article" property="acheteurPseudo" /><br />
						</html:link>
					</td>
					<td>
					<logic:empty name="article" property="transactionAvis">
						<bean:message key="vendeurArticles.articlesVendus.avis.noAvis" />
					</logic:empty>
					<logic:notEmpty name="article" property="transactionAvis">
						<bean:write name="article" property="transactionAvis" />
					</logic:notEmpty>
					</td>
				</tr>
			</logic:iterate>
		</table>
	</logic:notEmpty>
</body>
</html>
