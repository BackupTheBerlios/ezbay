
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
    <link rel="stylesheet" href="<html:rewrite page="/style.css"/>" type="text/css">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<center>
<h2><bean:message key="titre.application"/> - <bean:message key="inscription.label"/></h2>
<br />
<hr width="50%;" color=" #FDF3BF" size="3" />
<br />
<html:errors />
<html:form action="/membreSave">
<table class="body_affichage_donnees">
<tr>
<td class="intitule"><b><bean:message key="inscription.label.nom"/>:</b></td>
<td><html:text property="nom" /></td>
</tr>
<tr>
<td class="intitule"><b><bean:message key="inscription.label.prenom"/>:</b></td>
<td><html:text property="prenom" /></td>
</tr>
<tr>
<td class="intitule"><b><bean:message key="inscription.label.pseudo"/>:</b></td>
<td>
	 <logic:empty name="membreForm" property="id"> 
		<b><html:text property="pseudo" /></b>
	 </logic:empty>
	 <logic:notEmpty name="membreForm" property="id"> 
		<bean:write name="membreForm" property="pseudo" />
		<html:hidden property="pseudo" />
	 </logic:notEmpty>
</td>
</tr>
<tr>
<td class="intitule"><b><bean:message key="inscription.label.password1"/>:</b></td>
<td><html:password property="password" value="" /></td>
</tr>
<tr>
<td class="intitule"><b><bean:message key="inscription.label.password2"/>:</b></td>
<td><html:password property="password2" value=""/></td>
</tr>
<tr>
<td class="intitule"><b><bean:message key="inscription.label.email"/>:</b></td>
<td><html:text property="email" /></td>
</tr>
<tr>
<td class="intitule"><b><bean:message key="inscription.label.adresse"/>:</b></td>
<td><html:text property="adresse" /></td>
</tr>
<tr>
<td class="intitule"><b><bean:message key="inscription.label.codepostal"/>:</b></td>
<td><html:text property="codePostal" maxlength="5" size="5"/></td>
</tr>
<tr>
<td class="intitule"><b><bean:message key="inscription.label.ville"/>:</b></td>
<td><html:text property="ville" /></td>
</tr>
<tr>
<td class="intitule"><b><bean:message key="inscription.label.pays"/>:</b></td>
<td><html:text property="pays" /></td>
</tr>
<tr>
<td class="intitule"><b><bean:message key="inscription.label.telephonefixe"/>:</b></td>
<td><html:text property="telephoneFixe" maxlength="10"/></td>
</tr>
<tr>
<td class="intitule"><b><bean:message key="inscription.label.telephoneportable"/>:</b></td>
<td><html:text property="telephonePortable" maxlength="10"/></td>
</tr>
<tr>
<td class="intitule"><b><bean:message key="inscription.label.dateNaissance"/>:</b></td>
<td><html:text property="stringDateNaissance" size="10" maxlength="10"/></td>
</tr>
</table><br /><br />
<html:submit><bean:message key="bouton.label.valider"/></html:submit>


<html:hidden property="id" />
</html:form>
</center>
</body>
</html>