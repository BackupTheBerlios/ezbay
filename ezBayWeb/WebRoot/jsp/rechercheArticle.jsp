<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<bean:define id="categories" name="categories" type="java.util.Collection" scope="session" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<head>
<title>
		rechercheArticle.jsp
	</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="refresh" content="30">
</head>

<body>
<center>
	<html:errors />
	<%-- create a html form --%>
	<html:form action="rechercheArticle">
		<table border="0">
			<tbody>

				<tr>
					<td>
						<b><bean:message key="articleEdit.categorie" />
						:</b>
					</td>
					<td>
						<html:select property="categorieDTO.id">
							<html:options collection="categories" property="id" labelProperty="libelle" />
						</html:select>
					</td>
				</tr>

				<tr>
					<td>
						<b><bean:message key="articleEdit.libelle" />
						:</b>
					</td>
					<td>
						<html:text property="libelle" />
					</td>
				</tr>
				<tr>

					<td>
						<b><bean:message key="articleEdit.marque" />
						:</b>
					</td>
					<td>
						<html:text property="marque" />
					</td>
				</tr>

				<tr>
					<td>
						<b><bean:message key="articleEdit.modele" />
						:</b>
					</td>
					<td>
						<html:text property="modele" />
					</td>
				</tr>

				<tr>
					<td>
						<b><bean:message key="rechercheArticle.prixVenteMin" />
						:</b>
					</td>
					<td>
						<html:text property="stringPrixVenteMin" />
					</td>
				</tr>

				<tr>
					<td>
						<b><bean:message key="rechercheArticle.prixVenteMax" />
						:</b>
					</td>
					<td>
						<html:text property="stringPrixVenteMax" />
					</td>
				</tr>

				<tr>
					<td>
						<b><bean:message key="rechercheArticle.anneeFabrication" />
						:</b>
					</td>
					<td>
						<html:text property="stringAnneeFabrication" />
					</td>
				</tr>

				<tr>
					<td>
						<b><bean:message key="rechercheArticle.dateLimite" />
						:</b>
					</td>
					<td>
						<html:text property="stringDateLimite" />
					</td>
				</tr>

			</tbody>
		</table>
		<%-- set the parameter for the dispatch action --%>
		<html:hidden property="id" />
		<br>
		<%-- submit and back button --%>
		<html:button property="back" onclick="history.back();">Back</html:button>
			&nbsp;
			
			<html:submit>
			<bean:message key="link.Recherche.Article" />
		</html:submit>
	</html:form>

<logic:notEmpty name="rechercheForm" property="articlesView"> 
<br /><br />
<b><bean:message key="rechercheArticle.nbArticles" /> : <bean:write name="rechercheForm" property="nbArticles" /></b>
	<br /><br /><br />
	<table border="0">
		<tbody>
			<%-- set the header --%>
			<tr>
				<td width="16%" align="center">
					<h2><bean:message key="articleList.libelle" /></h2>
				</td>
				<td width="16%" align="center">
					<h2><bean:message key="articleList.marque" /></h2>
				</td>
				<td width="16%" align="center">
					<h2><bean:message key="articleList.modele" /></h2>
				</td>
				<td width="16%" align="center">
					<h2><bean:message key="articleList.prixActuel" /></h2>
				</td>
				<td width="16%" align="center">
					<h2><bean:message key="articleList.nbEncheres" /></h2>
				</td>
				<td width="16%" align="center">
					<h2><bean:message key="articleList.dateLimite" /></h2>
				</td>
			</tr>
			<%-- start with an iterate over the array articlesView --%>
			<logic:iterate name="rechercheForm" property="articlesView" id="article">
				<tr>
					<%-- article informations --%>

					<td align="center">
						<html:link action="article.do?do=showArticleFiche" paramName="article" paramProperty="id" paramId="id">
							<bean:write name="article" property="libelle" />						
						</html:link>
					</td>
					<td align="center">
						<bean:write name="article" property="marque" />
					</td>
					<td align="center">
						<bean:write name="article" property="modele" />
					</td>
					<td align="center">
						<logic:empty name="article" property="derniereEnchereView">
							<bean:write name="article" property="prixVente" />
						</logic:empty>
						<logic:notEmpty name="article" property="derniereEnchereView">			
							<b><bean:write name="article" property="derniereEnchereView.montant" />
							&nbsp;<bean:message key="general.label.devise" /></b>
						</logic:notEmpty>
					</td>
					<td align="center">
						<bean:write name="article" property="nbEncheres" />
					</td>					
					<td align="center">
						<bean:write name="article" property="formattedDateLimite" />
					</td>										
				</tr>

			</logic:iterate>
			<logic:notPresent name="article">
				<tr>
					<td colspan="5">
						<bean:message key="articleList.noArticle" />
					</td>
				</tr>
			</logic:notPresent>
		</tbody>
	</table>

</logic:notEmpty>
<logic:empty name="rechercheForm" property="articlesView"> 
	<logic:present name="showResult" scope="request">
		<b><bean:message key="rechercheArticle.noArticle" /> </b>
	</logic:present>
</logic:empty>
</center>
</body>
</html:html>
