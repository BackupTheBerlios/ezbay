<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<table cellspacing="0" cellpadding="5" width="100%">
<tr>
  <td width="25%" align="center">
    <i><html:link forward="welcome" styleClass="logo">EzBay</html:link></i>
  </td>
 	<td width="25%" align="center">
		<html:link action="/myEzBay.do?do=showMyEzBay" styleClass="header"><bean:message key="link.monEzBay"/></html:link>
	</td>
	<td width="25%" align="center">
		<html:link action="/article.do?do=showRechercheForm" styleClass="header"><bean:message key="link.Recherche.Article"/></html:link>
	</td>
	<td width="25%" align="center">
		<html:link action="/categorie.do?do=showCategories" styleClass="header"><bean:message key="link.categories"/></html:link>
 	</td>
</tr>
<logic:present name="membre" property="id" scope="session">
<tr>
	<td>
		<bean:message key="header.title"/>
		<bean:write name="membre" property="pseudo" />
	</td>
</tr>
</logic:present>
</table>
<hr color="#FBE468">