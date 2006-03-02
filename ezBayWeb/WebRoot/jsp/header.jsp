<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>

<table cellpadding="3" width="100%">
	<tr>
		<td align="left" width="90">
			<i>
				<html:link forward="welcome" styleClass="logo">EzBay</html:link>
			</i>
		</td>
		<logic:present name="membre" property="id" scope="session">
		<td>
			<bean:message key="header.title" />
			<bean:write name="membre" property="pseudo" />
		</td>
		</logic:present>
		<logic:present name="membre" property="id" scope="session">
		<td align="center">
			<html:link action="/myEzBay.do?do=showMyEzBay" styleClass="header">
				<bean:message key="link.monEzBay" />
			</html:link>
		</td>
		</logic:present>
		<td align="center">
			<html:link action="/article.do?do=showRechercheForm" styleClass="header">
				<bean:message key="link.Recherche.Article" />
			</html:link>
		</td>
		<td align="center">
			<html:link action="/categorie.do?do=showCategories" styleClass="header">
				<bean:message key="link.categories" />
			</html:link>
		</td>
		<td align="center">
			<logic:present name="membre" property="id" scope="session">
			<html:link action="/myEzBay.do?do=deconnect" styleClass="header">
				<bean:message key="link.fermerSession" />
			</html:link>
			</logic:present>
			<logic:notPresent name="membre" property="id" scope="session">
			<html:link action="/myEzBay.do?do=showMyEzBay" styleClass="header">
				<bean:message key="link.ouvrirSession" />
			</html:link>
			</logic:notPresent>
		</td>
	</tr>
</table>
<hr color="#FBE468">
