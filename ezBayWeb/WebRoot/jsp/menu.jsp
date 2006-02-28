<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<table class="menu" cellspacing="2" cellpadding="1">

<logic:notEmpty name="membre" scope="session">
	<tr class="menu_ligne_fonce" height="20">
	    <td><bean:message key="myEzBay.menu.sectionClient.titre"/></font>
		 </td>
	</tr>
	<tr height="20">
	    <td><html:link action="/membre.do?do=showInformations"><bean:message key="myEzBay.menu.sectionClient.link.infos"/></html:link>
		 </td>
	</tr>
	<tr height="20">
	    <td><html:link action="#"><bean:message key="myEzBay.menu.sectionClient.link.achats"/></html:link>
		 </td>
	</tr>
	<tr height="20">
	    <td><html:link action="#"><bean:message key="myEzBay.menu.sectionClient.link.encheres"/></html:link>
		 <br /><br /></td>
	</tr>
	
	<logic:empty name="vendeurId" scope="session">
	<tr height="20">
	    <td><html:link action="/vendeur.do?do=showEditVendeur"><bean:message key="myEzBay.menu.sectionVendeur.link.inscription"/></html:link>
		 <br /><br /></td>
	</tr>
	
	</logic:empty>
	<logic:notEmpty name="vendeurId" scope="session">
	
	<tr class="menu_ligne_fonce" height="20">
	    <td><bean:message key="myEzBay.menu.sectionVendeur.titre"/>
	    </td>
	</tr>
	<tr height="20">
	    <td><html:link action="/vendeur.do?do=showInformations"><bean:message key="myEzBay.menu.sectionVendeur.link.infos"/></html:link><br/></td>
	</tr>
	<tr height="20">
	    <td><html:link action="/vendeur.do?do=showArticles"><bean:message key="myEzBay.menu.sectionClient.link.articles"/></html:link></td>
	</tr>
	<tr height="20">
	    <td><html:link action="/article.do?do=showEdit"><bean:message key="myEzBay.menu.sectionClient.link.ajoutArticle"/></html:link></td>
	</tr>
	
	
	</logic:notEmpty>
	<tr height="20">
		<td><br /><br /><html:link action="/myEzBay.do?do=deconnect"><bean:message key="link.deconnexion"/></html:link>	 </td>
	</tr>
</logic:notEmpty>
	
</table>




	
	