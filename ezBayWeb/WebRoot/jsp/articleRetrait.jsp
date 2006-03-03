<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
  <title><bean:message key="articleRetrait.titre" /></title>
    <link rel="stylesheet" href="<html:rewrite page="/style.css"/>" type="text/css">
	<meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  			
  </head>
  
	<body>
	<center>
	<h2><bean:message key="articleRetrait.titre" /></h2>
	<hr width="50%;" color=" #FDF3BF" size="3" />
	<table>
	<logic:messagesPresent>
		<tr>
			<td>
			<UL>
			<html:messages id="error">
				<LI><bean:write name="error"/></LI>
			</html:messages>
			</UL>
			</td>
		</tr>
		<tr>
			<td>
				<html:link action="/vendeur.do?do=showArticles"><bean:message key="articleRetrait.erreurs.link.retour" /></html:link>
			</td>
		</tr>
	</logic:messagesPresent>
	<logic:messagesNotPresent>
		<tr>
			<td colspan="2">
				<bean:message key="articleRetrait.messageConfirm" /> <bean:write name="articleForm" property="libelle" /> ?
			</td>
		</tr>
		<tr>
			<td><br /></td>
		</tr>
		<tr>
			<td align="center">
				<html:link action="/article.do?do=confirmSupprimerArticle" paramName="articleForm" paramProperty="id" paramId="id"><bean:message key="link.valider" /></html:link>
			</td>
			<td align="center">
				<html:link href="javascript:history.back()"><bean:message key="link.annuler" /></html:link>
			</td>
		</tr>
	</logic:messagesNotPresent>
	</table>
	</center>
	</body>
</html:html>
