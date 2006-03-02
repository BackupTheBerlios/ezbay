
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
		<bean:message key="clientAchats.titre" />
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
		<bean:message key="clientAchats.titre" />
	</h2>
	<hr width="50%;" color=" #FDF3BF" size="3" />
	<html:errors />
		<logic:empty name="clientForm" property="articlesView">
			<bean:message key="clientAchats.noArticle" />
		</logic:empty>
		<logic:notEmpty name="clientForm" property="articlesView">
			<table class="body_mes_articles" cellspacing="30">
				<tr>
					<td>
						<bean:message key="articleList.libelle" />
					</td>
					<td>
						<bean:message key="articleList.prix" />
					</td>
					<td>
						<bean:message key="clientAchats.article.date" />
					</td>
					<td>
						<bean:message key="clientAchats.vendeur" />
					</td>
					<td>
						<bean:message key="clientAchats.avis.titre" />
					</td>
				</tr>
				<logic:iterate name="clientForm" property="articlesView" id="article">
				<tr>
					<%-- article informations --%>
					<td>
						<html:link action="article.do?do=showArticleFiche" paramName="article" paramProperty="id" paramId="id">
							<bean:write name="article" property="libelle" />
						</html:link>
					</td>
					<td>
						<bean:write name="article" property="transactionMontant" />
						<bean:message key="general.label.devise" />
					</td>
					<td>
						<bean:write name="article" property="transactionFormattedDate" />
					</td>
					<td>
						<html:link action="/membre.do?do=showMembreFiche" paramName="article" paramProperty="vendeurMembreId" paramId="membreId">
							<bean:write name="article" property="vendeurPseudo" />
						</html:link>
					</td>
					<td>
						<logic:notEmpty name="article" property="transactionAvis">
							<bean:write name="article" property="transactionAvis" />
						</logic:notEmpty>
						<logic:empty name="article" property="transactionAvis">
							<html:link action="/avis.do?do=showDeposerAvisForm" paramName="article" paramProperty="id" paramId="articleId">
									<bean:message key="clientAchats.deposerAvis.link" />
							</html:link>
						</logic:empty>
					</td>
				</tr>
				</logic:iterate>
			</table>
		</logic:notEmpty>
		<html:hidden property="do" value="deposerAvis" />
	</center>
</body>
</html:html>
