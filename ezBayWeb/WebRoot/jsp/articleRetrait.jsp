<%@ page language="java" pageEncoding="UTF-8"%>

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
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  			
  </head>
  
	<body>
	<H1><bean:message key="articleRetrait.titre" /></H1>
	<logic:messagesPresent>
		<UL>
			<html:messages id="error">
				<LI><bean:write name="error"/></LI>
			</html:messages>
		</UL>
	<br />
		<html:link action="/vendeur.do?do=showArticles"><bean:message key="articleRetrait.erreurs.link.retour" /></html:link>
	</logic:messagesPresent>
	<logic:messagesNotPresent>
	<bean:message key="articleRetrait.messageConfirm" /> <bean:write name="articleForm" property="articleDTO.libelle" /> ?
			<html:link action="/article.do?do=confirmRetirerArticle" paramName="articleForm" paramProperty="articleDTO.id" paramId="id"><bean:message key="link.valider" /></html:link>
			&nbsp;<html:link href="javascript:history.back()"><bean:message key="link.annuler" /></html:link>
	</logic:messagesNotPresent>
	</body>
</html:html>
