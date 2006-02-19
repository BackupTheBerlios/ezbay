
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
    
    <title> <bean:message key="articleFiche.title"/></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <table>
  <tr>
  <td>
<bean:message key="articleFiche.libelle"/>:<bean:write name="articleForm" property="libelle" /><br />
<bean:message key="articleFiche.modele"/>:<bean:write name="articleForm" property="marque" /><br />
<bean:message key="articleFiche.marque"/>:<bean:write name="articleForm" property="modele" /><br />
<bean:message key="articleFiche.prixVente"/>:<bean:write name="articleForm" property="prixVente" /><br />
<bean:message key="articleFiche.anneeFabrication"/>:<bean:write name="articleForm" property="anneeFabrication" /><br />
<bean:message key="articleFiche.dateLimite"/>:<bean:write name="articleForm" property="stringDateLimite" /><br />
<bean:message key="articleFiche.description"/>:<bean:write name="articleForm" property="description" /><br />
<br /><br />
<html:link action="/membre.do?do=showMembreFiche" paramName="articleForm" paramProperty="vendeurId" paramId="vendeurId"><bean:message key="link.membreFiche"/> : <bean:write name="articleForm" property="vendeurPseudo" /> </html:link>
</td>
</tr>
</table>
  </body>
</html:html>
