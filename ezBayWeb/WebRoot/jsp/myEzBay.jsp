<%@ page import="axlomoso.ezbay.model.views.MembreView"%>
<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

<html> 
	<head>
		<title>JSP for myEzBayForm form</title>
	</head>
	<body>
	myEzBay
	<br />
	
	<%	
	String isVendeur = "0";
	if(((MembreView)session.getAttribute("membre")).getVendeurView().getId() != null) isVendeur = "1";%>
	 <logic:equal name="isVendeur" value="0"> 
		Devenir vendeur
	 </logic:equal>
	 <logic:notEqual name="isVendeur" value="0"> 
		Mes objets en vente
	  </logic:notEqual>
	 
	 <br>
	 nom : <bean:write name="membre" property="nom"/><br>
	 vendeur : <bean:write name="membre" property="vendeurView"/><br>
	 vendeurId : <bean:write name="membre" property="vendeurView.id"/><br>
<br>
	 
	 
	 <br /> 
	<html:link action="/myEzBay.do?do=deconnect"><bean:message key="link.deconnexion"/></html:link>
	</body>
</html>
