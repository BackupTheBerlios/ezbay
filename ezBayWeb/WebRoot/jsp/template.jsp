<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
	<HEAD>
		   <link rel="stylesheet" href="<html:rewrite page="/style.css"/>" type="text/css">
 
		<title>
			titre
		</title>
	</HEAD>

	<body bgcolor="#ffffff" text="#000000" link="#023264" alink="#023264" vlink="#023264">
		<table width="100%" cellspacing="5" height="100%">
			<tr>
				<td colspan="2">
					<tiles:insert attribute="header" />
				</td>
			</tr>
			<tr height="100%">
				<logic:notEmpty name="membre" scope="session">
				<td valign="top">
					<tiles:insert attribute='menu' />
				</td>
				</logic:notEmpty>
				<logic:empty name="membre" scope="session">
				<td></td>
				</logic:empty>
				<td width="100%" valign="top" align="left">
					<tiles:insert attribute='body' />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<tiles:insert attribute="footer" />
				</td>
			</tr>
		</table>
	</body>
</html>
