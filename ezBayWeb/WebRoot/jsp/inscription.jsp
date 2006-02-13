
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
<bean:message key="titre.application"/> - <bean:message key="inscription.label"/>
</font><br>
<hr width="100%" noshade="true" />
<html:errors />
<html:form action="/inscription">
<table>
<tr>
<td align="right"><bean:message key="inscription.label.nom"/>:</td>
<td><html:text property="nom" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.prenom"/>:</td>
<td><html:text property="prenom" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.pseudo"/>:</td>
<td><html:text property="pseudo" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.password1"/>:</td>
<td><html:password property="password" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.password2"/>:</td>
<td><html:password property="password2" /></td>>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.email"/>:</td>
<td><html:text property="email" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.adresse"/>:</td>
<td><html:text property="adresse" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.codepostal"/>:</td>
<td><html:text property="codePostal" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.ville"/>:</td>
<td><html:text property="ville" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.pays"/>:</td>
<td><html:text property="pays" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.telephonefixe"/>:</td>
<td><html:text property="telephoneFixe" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.telephoneportable"/>:</td>
<td><html:text property="telephonePortable" /></td>
</tr>
<tr>
<td align="right"><bean:message key="inscription.label.dateNaissance"/>:</td>
<td><html:text property="stringDateNaissance" /></td>
</tr>
<tr>
<td colspan="2">
<html:submit><bean:message key="bouton.label.valider"/></html:submit>
</td>
</tr>
</table>

</html:form>

</body>
</html>