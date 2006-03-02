<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<html>
	<head>
		<title>
			<bean:message key="categorieList.title" />
		</title>
		<meta http-equiv="refresh" content="30">
	</head>
	<body>
		<center>
			<h2>
				<bean:message key="categorieList.title" />
			</h2>
			<hr width="50%;" color=" #FDF3BF" size="3" />
			<html:errors />

			<%-- start with an iterate over the array categoriesDTO --%>
			<logic:iterate name="categorieForm" property="listeCategories" id="categorie">
				<br />
				<html:link action="/articleList.do?do=showArticlesByCategorie" paramName="categorie" paramProperty="id" paramId="categorieId">
					<bean:write name="categorie" property="libelle" />
				</html:link>
			</logic:iterate>
			<logic:notPresent name="categorie">
				<bean:message key="categorieList.noCategories" />
			</logic:notPresent>
			<br />
			<br />
		</center>
	</body>
</html>
