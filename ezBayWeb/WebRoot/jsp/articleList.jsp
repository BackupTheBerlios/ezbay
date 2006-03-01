<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html>
	<head>
		<title>
			<bean:message key="articleList.title" />
		</title>
	<link rel="stylesheet" href="<html:rewrite page="/style.css"/>" type="text/css">
    <meta http-equiv="refresh" content="30">
	</head>
	<body>
		<center>
			<H2>
				<bean:message key="articleList.categorie.titre" />
				<bean:write name="articleListForm" property="categorieDTO.libelle" />
			</H2>
			<br /><br />
			<logic:empty name="articleListForm" property="articlesView">
				<bean:message key="articleList.noArticle" />
			</logic:empty>
			<logic:notEmpty name="articleListForm" property="articlesView">
				<table class="body_recherche_article" cellspacing="15">
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
							<bean:message key="articleList.dateLimite" />
						</td>
						<td>
							<bean:message key="articleList.derniereEnchere.titre" />
						</td>
					</tr>
					<%-- start with an iterate over the array articlesView --%>
					<logic:iterate name="articleListForm" property="articlesView" id="article">
						<tr>
							<%-- article informations --%>
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
								<bean:write name="article" property="prixVente" />
							</td>
							<td>
								<bean:write name="article" property="nbEncheres" />
							</td>
							<td>
								<bean:write name="article" property="formattedDateLimite" />
							</td>
							<logic:notEmpty name="article" property="derniereEnchereDate">
								<td>
									<bean:write name="article" property="derniereEnchereMontant" />
									<bean:message key="general.label.devise" />
								</td>
							</logic:notEmpty>
						</tr>
					</logic:iterate>
				</table>
			</logic:notEmpty>
		</center>
	</body>
</html>
