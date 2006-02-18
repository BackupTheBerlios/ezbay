
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
    
    <title><bean:message key="connect.title" /></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
	<body>
	<html:errors/>
		<%-- create a html form --%>
		<html:form action="connect">
				<label for="login"><bean:message key="connect.login"/> : </label><html:text property="login" value=""/>
				<br>
				<label for="password"><bean:message key="connect.password"/> : </label><html:password property="password" value=""/>
				<br>
			<html:hidden property="do" value="validateConnect" />
			<br>
			<%-- submit and back button --%>
			<html:button property="home"  onclick="location.href='default.do'">Cancel</html:button>
			&nbsp;
			<html:submit>Connect</html:submit>	
			</html:form>
			
	</body>
</html:html>
