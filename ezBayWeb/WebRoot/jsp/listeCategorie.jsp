<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<html>
<head>
<title>
<bean:message key="categorieList.title" />
</title>
</head>
<body>
<font size="+1">
<bean:message key="categorieList.title"/> 
</font><br>
<hr width="100%" noshade="true" />
<html:errors/>


<%-- start with an iterate over the array categoriesDTO --%>
<logic:iterate name="categorieForm" property="listeCategories" id="categorie">
<br />	
 	<html:link action="/articleList.do?do=showArticlesByCategorie" paramName="categorie" paramProperty="id" paramId="categorieId"><bean:write name="categorie" property="libelle" /></html:link>
 <br />
</logic:iterate>
<logic:notPresent name="categorie">
<tr>
<td colspan="5"><bean:message key="categorieList.noCategories" /></td>
</tr>
</logic:notPresent>

 <html:button property="back" onclick="location.href='default.do'">Back to menu</html:button>
</body>
</html>