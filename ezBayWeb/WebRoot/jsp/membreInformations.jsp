
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
  <title><bean:message key="membre.titre.infos"/></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <center>
<font size="+1">
<bean:message key="membre.titre.infos"/>
</font><br>
<hr width="50%" noshade="true" />
<table>
<tr>
<td align="left"><b><bean:message key="inscription.label.nom"/>:</b></td>
<td><bean:write name="membreForm" property="nom" /></td>
</tr>
<tr>
<td align="left"><b><bean:message key="inscription.label.prenom"/>:</b></td>
<td><bean:write name="membreForm" property="prenom" /></td>
</tr>
<tr>
<td align="left"><b><bean:message key="inscription.label.pseudo"/>:</b></td>
<td><bean:write name="membreForm" property="pseudo" /></td>
</tr>
<tr>
<td align="left"><b><bean:message key="inscription.label.email"/>:</b></td>
<td><bean:write name="membreForm" property="email" /></td>
</tr>
<td align="left"><b><bean:message key="inscription.label.adresse"/>:</b></td>
<td><bean:write name="membreForm" property="adresse" /></td>
</tr>
<tr>
<td align="left"><b><bean:message key="inscription.label.codepostal"/>:</b></td>
<td><bean:write name="membreForm" property="codePostal" /></td>
</tr>
<tr>
<td align="left"><b><bean:message key="inscription.label.ville"/>:</b></td>
<td><bean:write name="membreForm" property="ville" /></td>
</tr>
<tr>
<td align="left"><b><bean:message key="inscription.label.pays"/>:</b></td>
<td><bean:write name="membreForm" property="pays" /></td>
</tr>
<td align="left"><b><bean:message key="inscription.label.telephonefixe"/>:</b></td>
<td><bean:write name="membreForm" property="telephoneFixe" /></td>
</tr>
<tr>
<td align="left"><b><bean:message key="inscription.label.telephoneportable"/>:</b></td>
<td><bean:write name="membreForm" property="telephonePortable" /></td>
</tr>
<tr>
<td align="left"><b><bean:message key="inscription.label.dateNaissance"/>:</b></td>
<td><bean:write name="membreForm" property="stringDateNaissance" /></td>
</tr>
<tr><td><br><br></td></tr>
<tr>
<td align="center">
<html:link action="/myEzBay.do?do=showMyEzBay"><bean:message key="link.retour"/></html:link>
</td>
<td align="center"><html:link action="/membre.do?do=showEditMembre"><bean:message key="link.membre.edit"/></html:link><br>
</td>
</tr>
</table>
</center>
</body>
</html:html>
