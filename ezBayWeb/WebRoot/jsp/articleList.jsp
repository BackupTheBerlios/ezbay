<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<html>
<head>
 <title>
<bean:message key="articleList.title" />
</title><br />
</head>
<body>
<center>
<H2><bean:message key="articleList.categorie.titre" /> <bean:write name="articleListForm" property="categorieDTO.libelle" /> </H2>
<logic:empty name="articleListForm" property="articlesDTO">
<bean:message key="articleList.noArticle" />
</logic:empty>
<logic:notEmpty name="articleListForm" property="articlesDTO">
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
<%-- start with an iterate over the array articlesDTO --%>
<logic:iterate name="articleListForm" property="articlesDTO" id="article">
<tr>
<%-- article informations --%>

<td><bean:write name="article" property="libelle" /></td>
<td><bean:write name="article" property="marque" /></td>
<td><bean:write name="article" property="modele" /></td>
<td><bean:write name="article" property="prixVente" /></td>
<td><bean:write name="article" property="anneeFabrication" /></td>
<td><bean:write name="article" property="dateLimite" /></td>
<td>
<html:link action="article.do?do=showArticleFiche" paramName="article" paramProperty="id" paramId="id"><bean:message key="link.articleFiche" /></html:link>
</td>
</tr>

</logic:iterate>

</tbody>
</table>
</logic:notEmpty>
<br /><br /><br />

 <html:button property="back" onclick="history.back()">Back to menu</html:button>
</center>
</body>
</html>