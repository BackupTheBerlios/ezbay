<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>


  <table cellspacing="0" cellpadding="5" border="0" width="100%">
<tr>
  <td>
      <font color="#000088">
    <H1><B><i><html:link forward="welcome">EzBay</html:link></i></b><br/></H1></font>
    
  </td>
 	<td>
		<html:link action="/myEzBay.do?do=showMyEzBay"><bean:message key="link.monEzBay"/></html:link>
	</td>
	<td>
		<html:link action="/article.do?do=showRechercheForm"><bean:message key="link.Recherche.Article"/></html:link>
	</td>
	<td>
		<html:link action="/categorie.do?do=showCategories"><bean:message key="link.categories"/></html:link>
 	</td>
</tr>
<tr>
	<td>
		<logic:present name="membre" property="id" scope="session">
			<bean:message key="header.title"/>
			<bean:write name="membre" property="pseudo" />
		</logic:present>
	</td>
</tr>
</table>
<hr>