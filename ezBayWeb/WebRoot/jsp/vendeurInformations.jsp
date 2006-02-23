<%@ page language="java" pageEncoding="UTF-8"%>
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
<bean:message key="titre.application"/> - <bean:message key="vendeur.titre.infos"/>
</font><br>
<hr width="100%" noshade="true" />
<bean:message key="vendeur.titre.infosCB"/>
<table>
<tr>
<td align="right"><bean:message key="vendeur.label.numCB"/>:</td>
<td><bean:write name="vendeurForm" property="numCB" /></td>
</tr>
<tr>
<td align="right"><bean:message key="vendeur.label.nomProprioCB"/>:</td>
<td><bean:write name="vendeurForm" property="nomProprioCB" /></td>
</tr>
<tr>
<td align="right"><bean:message key="vendeur.label.dateExpirCB"/>:</td>
<td><bean:write name="vendeurForm" property="dateExpirCB" /></td>
</tr>
<tr>
<td align="right"><bean:message key="vendeur.label.codeSecuCB"/>:</td>
<td><bean:write name="vendeurForm" property="codeSecuCB" /></td>
</tr>
<tr>
<td colspan="2">
<html:link action="/myEzBay.do?do=showMyEzBay"><bean:message key="link.retour"/></html:link><br />
<html:link action="/vendeur.do?do=showEditVendeur"><bean:message key="link.vendeur.edit"/></html:link><br>
</td>
</tr>
</table>

</body>
</html>