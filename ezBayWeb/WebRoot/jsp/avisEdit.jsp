 
<%@ page language="java"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<html> 
	<head>
		<title><bean:message key="titre.application" />-<bean:message key="avisEdit.titre" /></title>
	</head>
	<body>
	<center>
		<h2><bean:message key="titre.application" /> - <bean:message key="avisEdit.titre" /></h2>
		<hr width="50%;" color=" #FDF3BF" size="3" />
	<html:form action="/avis">
		<table class="body_affichage_donnees" align="center">
			<tr>
				<td class="intitule">
					<bean:message key="avisEdit.vendeur" /> : 
				</td>
				<td>
					<bean:write name="avisForm" property="articleView.vendeurPseudo" />
				</td>
			</tr>
			<tr>
				<td class="intitule">
					<bean:message key="avisEdit.article" /> :
				</td>
				<td>
					<bean:write name="avisForm" property="articleView.libelle" />
				</td>
			</tr>
			<tr>
				<td class="intitule">
					<bean:message key="avisEdit.date" /> :
				</td>
				<td>
					<bean:write name="avisForm" property="articleView.transactionFormattedDate" />
				</td>
			</tr>	
			<tr>
				<td class="intitule">
					<bean:message key="avisEdit.montant" /> : 
				</td>
				<td>
					<bean:write name="avisForm" property="articleView.transactionMontant" />&nbsp;<bean:message key="general.label.devise" />
				</td>
			</tr>
			<td> </td>
			<tr>
				<td class="intitule">
					<bean:message key="avisEdit.votreAvis" /> :
				</td>
				<td>
					<html:select property="avis">
						<html:option value="très bien"><bean:message key="clientAchats.avis.titre.tresBien" /></html:option>
						<html:option value="bien"><bean:message key="clientAchats.avis.titre.bien" /></html:option>
						<html:option value="passable"><bean:message key="clientAchats.avis.titre.passable" /></html:option>
						<html:option value="mauvais"><bean:message key="clientAchats.avis.titre.mauvais" /></html:option>				
					</html:select>
				</td>
			</tr>
		</table>
		<br /><br />
				<html:hidden property="articleId" />
				<html:hidden property="do" value="saveAvis"/>
				<html:submit><bean:message key="link.valider" /></html:submit>
		</html:form>
		</center>
	</body>
</html>

