<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<table cellspacing="2" cellpadding="2" border="0" width="30%">

<logic:notEmpty name="membre" scope="session">
	<tr>
	    <td align="right"><font color="#000088"><h2><bean:message key="myEzBay.menu.sectionClient.titre"/></h2></font>
		 </td>
	</tr>
	<tr>
	    <td align="right"><html:link action="/membre.do?do=showInformations"><bean:message key="myEzBay.menu.sectionClient.link.infos"/></html:link>
		 </td>
	</tr>
	<tr>
	    <td align="right"><html:link action="#"><bean:message key="myEzBay.menu.sectionClient.link.achats"/></html:link>
		 </td>
	</tr>
	<tr>
	    <td align="right"><html:link action="#"><bean:message key="myEzBay.menu.sectionClient.link.encheres"/></html:link>
		 <br /><br /></td>
	</tr>
	
	<logic:empty name="vendeurId" scope="session">
	<tr>
	    <td align="right"><html:link action="/vendeur.do?do=showEditVendeur"><bean:message key="myEzBay.menu.sectionVendeur.link.inscription"/></html:link>
		 <br /><br /></td>
	</tr>
	
	</logic:empty>
	<logic:notEmpty name="vendeurId" scope="session">
	
	<tr>
	    <td align="right"><font color="#000088"><h2><bean:message key="myEzBay.menu.sectionVendeur.titre"/></h2></font>
	    </td>
	</tr>
		<tr>
	    <td align="right"><html:link action="/vendeur.do?do=showInformations"><bean:message key="myEzBay.menu.sectionVendeur.link.infos"/></html:link><br/></td>
	</tr>
		<tr>
	    <td align="right"><html:link action="/vendeur.do?do=showArticles"><bean:message key="myEzBay.menu.sectionClient.link.articles"/></html:link></td>
	</tr>
	<tr>
	    <td align="right"><html:link action="/article.do?do=showEdit"><bean:message key="myEzBay.menu.sectionClient.link.ajoutArticle"/></html:link></td>
	</tr>
	
	
	</logic:notEmpty>
	<tr>
		<td align="right"><br /><br /><html:link action="/myEzBay.do?do=deconnect"><bean:message key="link.deconnexion"/></html:link>	 </td>
	</tr>
</logic:notEmpty>
	
</table>




	
	