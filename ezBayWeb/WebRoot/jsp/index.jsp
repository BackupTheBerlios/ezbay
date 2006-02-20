
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
    
 <title>index.jsp</title>
    
 <meta http-equiv="pragma" content="no-cache">
 <meta http-equiv="cache-control" content="no-cache">
 <meta http-equiv="expires" content="0"> 
 <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
 <meta http-equiv="description" content="This is my page">
 </head>
  
<body>
    Welcome!
 <br />
 <html:link action="/membre.do?do=showInscription"><bean:message key="link.inscription"/></html:link><br>
 <br />  
 <br>
 <a href="rechercheArticle.jsp"><bean:message key="link.Recherche.Article"/></a><br>
 <br>
 <br />
 <html:link action="/categorie.do?do=showCategories"><bean:message key="link.categories"/></html:link><br>
 <br />
 <html:link action="/myEzBay.do?do=showMyEzBay">monEzBay</html:link>
 <br />
 <html:link action="/articleList.do">Show the articles list</html:link>
 <br />
 <html:link action="/membreList.do">Show the members list</html:link>
 <br />
 <br />
</body>
</html:html>
