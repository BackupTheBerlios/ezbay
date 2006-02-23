
<%@ page language="java" pageEncoding="UTF-8"%>

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
		<table border="0" width="100%" cellspacing="5">
			<tr>
				<td colspan="2">
					<tiles:insert attribute="logo" />
				</td>
			</tr>
			<tr>
				<td width="140" valign="top" bgcolor="#CCCCCC">
					<tiles:insert attribute='menu' />
				</td>
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
