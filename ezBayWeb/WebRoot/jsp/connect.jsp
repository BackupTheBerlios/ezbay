
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
     <title><bean:message key="connect.title" /></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
	<body>
	<center>
	<html:errors/>
	<%-- create a html form --%>
	<html:form action="connect">
		<br /><br /></br>
		<table>
		<tr>
			<td>
				<b><label for="login"><bean:message key="connect.login"/> :</b>
			</td>
			<td>
				</label><html:text property="login" value=""/>
			</td>
		</tr>
		<tr>
			<td>
				<b><label for="password"><bean:message key="connect.password"/> :</b> 
			</td>
			<td>
				</label><html:password property="password" value=""/>
			</td>
		</tr>
		</table>
		<br /><br />
		<html:hidden property="do" value="validateConnect" />
		<logic:present name="next" scope="request">
			<bean:define id="next" name="next" type="java.lang.String" /> 
			<html:hidden property="next" value="<%= next %>"/>
		</logic:present>
		<%-- submit and back button --%>
		<html:button property="home"  onclick="location.href='default.do'"><bean:message key="bouton.label.annuler"/></html:button>
		&nbsp;
		<html:submit><bean:message key="bouton.label.valider"/></html:submit>	
	</html:form>
	
	<html:link action="/membre.do?do=showInscription"><bean:message key="link.inscription"/></html:link><br>
 
	</center>		
	</body>
</html:html>
