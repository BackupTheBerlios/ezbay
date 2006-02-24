//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.delegate.ArticleFacadeDelegate;
import axlomoso.ezbay.delegate.MembreFacadeDelegate;
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
		try {
			MembreFacadeDelegate membreFacade = MembreFacadeDelegate.getInstance();
			ArticleFacadeDelegate articleFacade = ArticleFacadeDelegate.getInstance();
			EnchereForm enchereForm = (EnchereForm) form;
			//recuperation des champs du form et session
			ArticleDTO articleDTO = enchereForm.getArticleDTO();
			Double montantEnchere = enchereForm.getMontantEnchereCourante();
			String articleId = enchereForm.getArticleDTO().getId();
			String membreId = ((MembreDTO)request.getSession().getAttribute("membre")).getId();
			//creations objets
			ActionEnchereDTO actionEnchereDTO = new ActionEnchereDTO();
			actionEnchereDTO.setMontant(montantEnchere);
			String clientId = membreFacade.getClientDTO(membreId).getId();
			//action
			articleFacade.encherir(actionEnchereDTO, articleId, clientId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return (mapping.findForward("saveSuccess"));
	}

}

