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
import org.apache.struts.actions.DispatchAction;

import axlomoso.ezbay.delegate.ArticleFacadeDelegate;
import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.struts.form.EnchereForm;
import axlomoso.ezbay.struts.views.ArticleView;

/** 
 * MyEclipse Struts
 * Creation date: 02-23-2006
 * 
 * XDoclet definition:
 * @struts.action path="/enchere" name="enchereForm" parameter="do" scope="request" validate="true"
 */
public class EnchereAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables
	
	// --------------------------------------------------------- Methods

	/** 
	 * Method showEnchereForm
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showEnchereForm(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		String target = "";
		EnchereForm enchereForm = (EnchereForm) form;
		ActionErrors erreurs = new ActionErrors();
		if(request.getSession().getAttribute("membre") == null){
			target = "EchecMembreNonConnecte";
			erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("articleEnchereEdit.erreurs.membreNonConnecte"));
		}
		else{
			ArticleFacadeDelegate articleDelegate = ArticleFacadeDelegate.getInstance();	
			String articleId = request.getParameter("articleId");
			try {
				ArticleDTO articleDTO = articleDelegate.getArticle(articleId);
				ActionEnchereDTO actionEnchereDTO = articleDelegate.getDerniereEnchere(articleDTO.getId());
				if( actionEnchereDTO != null ){
					enchereForm.setMontantDerniereEnchere(actionEnchereDTO.getMontant());
				}
				else{
					enchereForm.setMontantDerniereEnchere(new Double(0));
				}
				enchereForm.setArticleDTO(articleDTO);
				target = "showEnchereForm";
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
	if (!erreurs.isEmpty()) {
		saveErrors(request, erreurs);
	}
	return (mapping.findForward(target));
	}

}

