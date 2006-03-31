//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.delegate.ArticleFacadeDelegate;
import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.exceptions.ArticlePasEnVenteException;
import axlomoso.ezbay.exceptions.EnchereInsuffisanteException;
import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.struts.form.EnchereForm;

/** 
 * MyEclipse Struts
 * Creation date: 02-23-2006
 * 
 * XDoclet definition:
 * @struts.action path="/enchereSave" name="enchereForm" input="/jsp/articleEnchereEdit.jsp" scope="request" validate="true"
 */
public class EnchereSaveAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		ActionErrors erreurs = new ActionErrors();
		ActionForward next = null;
		String articleId = "";
		try {
			MembreFacadeDelegate membreFacade = MembreFacadeDelegate.getInstance();
			ArticleFacadeDelegate articleFacade = ArticleFacadeDelegate.getInstance();
			//recuperation des champs du form et session
			EnchereForm enchereForm = (EnchereForm) form;
			articleId = enchereForm.getArticleDTO().getId();			
			Double montantEnchere = enchereForm.getMontantEnchereCourante();
			String membreId = ((MembreDTO)request.getSession().getAttribute("membre")).getId();
			//creations objets
			ActionEnchereDTO actionEnchereDTO = new ActionEnchereDTO();
			actionEnchereDTO.setMontant(montantEnchere);
			String clientId = membreFacade.getClientDTOByMembreId(membreId).getId();
			//action
			articleFacade.encherir(actionEnchereDTO, articleId, clientId);
			next = new ActionForward("/article.do?do=showArticleFiche&id=" + articleId);
		} catch (ArticlePasEnVenteException e) {
			erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("articleRetrait.erreurs.articlePasEnVente"));
			next = mapping.getInputForward();
		} catch (EnchereInsuffisanteException e) {
			erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("articleEnchereEdit.erreurs.montantInferieurADerniereEnchere"));
			next = mapping.getInputForward();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (!erreurs.isEmpty()) {
			saveErrors(request, erreurs);
		}
		return next;
	}

}

