
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
    <html:base />
    
    <title>membreInformations.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
<font size="+1">
<bean:message key="titre.application"/> - <bean:message key="membre.titre.infos"/>
</font><br>
<hr width="100%" noshade="true" />
<table>
<tr>
<td align="right"><bean:message key="inscription.label.nom"/>:</td>
<td><bean:write name="membreForm" property="nom" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.prenom"/>:</td>
<td><bean:write name="membreForm" property="prenom" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.pseudo"/>:</td>
<td><bean:write name="membreForm" property="pseudo" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.email"/>:</td>
<td><bean:write name="membreForm" property="email" /></td>
</tr>
<td align="right"><bean:message key="inscription.label.adresse"/>:</td>
<td><bean:write name="membreForm" property="adresse" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.codepostal"/>:</td>
<td><bean:write name="membreForm" property="codePostal" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.ville"/>:</td>
<td><bean:write name="membreForm" property="ville" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.pays"/>:</td>
<td><bean:write name="membreForm" property="pays" /></td>
</tr>
<td align="right"><bean:message key="inscription.label.telephonefixe"/>:</td>
<td><bean:write name="membreForm" property="telephoneFixe" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.telephoneportable"/>:</td>
<td><bean:write name="membreForm" property="telephonePortable" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.dateNaissance"/>:</td>
<td><bean:write name="membreForm" property="dateNaissance" /></td>
</tr>
<tr>
<td colspan="2">
<html:link action="/myEzBay.do?do=showMyEzBay"><bean:message key="link.retour"/></html:link>
<html:link action="/membre.do?do=showEditMembre"><bean:message key="link.membre.edit"/></html:link><br>
</td>
</tr>
</table>

</body>
</html:html>
