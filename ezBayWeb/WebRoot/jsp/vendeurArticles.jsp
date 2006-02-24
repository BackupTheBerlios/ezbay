
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
		<bean:message key="vendeurArticles.titre" />
	</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>

<body>
	<font size="+1">
		<bean:message key="titre.application" /> - <bean:message key="vendeurArticles.titre" />
	</font>
	<br>
<html:errors/>
	<H2>
		<bean:message key="vendeurArticles.articlesEnAttente.titre" />		
	</H2>
<logic:empty name="vendeurForm" property="articlesEnAttente">
	<bean:message key="vendeurArticles.articlesEnAttente.noArticle" />
</logic:empty>
<logic:notEmpty name="vendeurForm" property="articlesEnAttente">	
<table border="1">
<tbody>
<%-- set the header --%>
<tr>
<td><bean:message key="articleList.libelle" /></td>
<td><bean:message key="articleList.marque" /></td>
<td><bean:message key="articleList.modele" /></td>
<td><bean:message key="articleList.prixVente" /></td>
<td><bean:message key="articleList.anneeFabrication" /></td>
<td><bean:message key="articleList.dateLimite" /></td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
	<logic:iterate name="vendeurForm" property="articlesEnAttente" id="article">
		<tr>
			<%-- article informations --%>
			<td>
				<html:link action="article.do?do=showEdit" paramName="article" paramProperty="id" paramId="id"><bean:write name="article" property="libelle" /></html:link>
			</td>
			<td>
				<bean:write name="article" property="marque" />
			</td>
			<td>
				<bean:write name="article" property="modele" />
			</td>
			<td>
				<bean:write name="article" property="prixVente" /> <bean:message key="general.label.devise" />
			</td>
			<td>
				<bean:write name="article" property="anneeFabrication" />
			</td>
			<td>
				<bean:write name="article" property="formattedDateLimite" />
			</td>
			<td>
			<html:link action="/article.do?do=supprimerArticle" paramName="article" paramProperty="id" paramId="id"><bean:message key="vendeurArticles.link.supprimer" /></html:link>
			</td>
			<td>
			<html:link action="/article.do?do=mettreEnVenteArticle" paramName="article" paramProperty="id" paramId="id"><bean:message key="vendeurArticles.link.mettreEnVente" /></html:link>
			</td>
		</tr>
	</logic:iterate>
	
	</tbody>
	</table>
</logic:notEmpty>	
<br /><br />
	<H2>
		<bean:message key="vendeurArticles.articlesEnVente.titre" />
	</H2>
<logic:empty name="vendeurForm" property="articlesEnVente">
	<bean:message key="vendeurArticles.articlesEnVente.noArticle" />
</logic:empty>
<logic:notEmpty name="vendeurForm" property="articlesEnVente">	
<table border="1">
<tbody>
<%-- set the header --%>
<tr>
<td>&nbsp;</td>
<td><bean:message key="articleList.libelle" /></td>
<td><bean:message key="articleList.marque" /></td>
<td><bean:message key="articleList.modele" /></td>
<td><bean:message key="articleList.prixVente" /></td>
<td><bean:message key="articleList.anneeFabrication" /></td>
<td><bean:message key="articleList.dateLimite" /></td>

</tr>
	<logic:iterate name="vendeurForm" property="articlesEnVente" id="article">
		<tr>
			<%-- article informations --%>
			<td>
				<html:link action="article.do?do=showEdit" paramName="article" paramProperty="id" paramId="id"><bean:write name="article" property="libelle" /></html:link>
			</td>
			<td>
				<bean:write name="article" property="marque" />
			</td>
			<td>
				<bean:write name="article" property="modele" />
			</td>
			<td>
				<bean:write name="article" property="prixVente" /> <bean:message key="general.label.devise" />
			</td>
			<td>
				<bean:write name="article" property="anneeFabrication" />
			</td>
			<td>
				<bean:write name="article" property="formattedDateLimite" />
			</td>
			<td>
			<html:link action="/article.do?do=retirerArticle" paramName="article" paramProperty="id" paramId="id"><bean:message key="vendeurArticles.link.retirer" /></html:link>
			</td>
		</tr>
	</logic:iterate>
	</tbody>
	</table>
</logic:notEmpty>
<br /><br />
	<H2>
		<bean:message key="vendeurArticles.articlesVendus.titre" />
	</H2>
<%-- set the header --%>
<logic:empty name="vendeurForm" property="articlesVendus">
	<bean:message key="vendeurArticles.articlesVendus.noArticle" />
</logic:empty>
<logic:notEmpty name="vendeurForm" property="articlesVendus">
<table border="1">
<tbody>
<tr>
<td>&nbsp;</td>
<td><bean:message key="articleList.libelle" /></td>
<td><bean:message key="articleList.marque" /></td>
<td><bean:message key="articleList.modele" /></td>
<td><bean:message key="articleList.prixVente" /></td>
<td><bean:message key="articleList.anneeFabrication" /></td>
<td><bean:message key="articleList.dateLimite" /></td>
</tr>
	<logic:iterate name="vendeurForm" property="articlesVendus" id="article">
		<tr>
			<%-- article informations --%>
			<td>
				<html:link action="article.do?do=showEdit" paramName="article" paramProperty="id" paramId="id"><bean:write name="article" property="libelle" /></html:link>
			</td>
			<td>
				<bean:write name="article" property="libelle" />
			</td>
			<td>
				<bean:write name="article" property="marque" />
			</td>
			<td>
				<bean:write name="article" property="modele" />
			</td>
			<td>
				<bean:write name="article" property="prixVente" /> <bean:message key="general.label.devise" />
			</td>
			<td>
				<bean:write name="article" property="anneeFabrication" />
			</td>
			<td>
				<bean:write name="article" property="formattedDateLimite" />
			</td>
		</tr>
	</logic:iterate>
	</tbody>
	</table>
</logic:notEmpty>
	
	<br /><br />
</body>
</html:html>
