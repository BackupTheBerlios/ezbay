
<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title><bean:message key="titre.application"/> - <bean:message key="inscription.label"/></title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<font size="+1">
<bean:message key="titre.application"/> - <bean:message key="vendeur.titre"/>
</font><br>
<html:errors />
<bean:message key="vendeur.titre.infosCB"/>
<html:form action="/vendeurSave">
<table>
<tr>
<td align="right"><bean:message key="vendeur.label.numCB"/>:</td>
<td><html:text property="numCB" maxlength="16" size="16"/></td>
</tr>
<tr>
<td align="right"><bean:message key="vendeur.label.nomProprioCB"/>:</td>
<td><html:text property="nomProprioCB" /></td>
</tr>
<tr>
<td align="right"><bean:message key="vendeur.label.dateExpirCB"/>:</td>
<td><html:text property="stringDateExpirCB" maxlength="10" size="10"/></td>
</tr>
<tr>
<td align="right"><bean:message key="vendeur.label.codeSecuCB"/>:</td>
<td><html:text property="codeSecuCB" maxlength="3" size="3"/></td>
</tr>
<tr>
<td colspan="2">
<html:submit><bean:message key="bouton.label.valider"/></html:submit>
</td>
</tr>
</table>
<html:hidden property="id" />
</html:form>

</body>
</html>