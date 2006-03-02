//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import axlomoso.ezbay.delegate.ArticleFacadeDelegate;
import axlomoso.ezbay.struts.form.AvisForm;
import axlomoso.ezbay.struts.form.ClientForm;
import axlomoso.ezbay.struts.views.ArticleView;

/** 
 * MyEclipse Struts
 * Creation date: 03-01-2006
 * 
 * XDoclet definition:
 * @struts.action path="/avis" name="avisForm" parameter="do" scope="request"
 */
public class AvisAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods
	/** 
	 * Method showMyEncheres
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showDeposerAvisForm(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			AvisForm avisForm = (AvisForm) form;
			String articleId = request.getParameter("articleId");
			avisForm.setArticleId(articleId);
			return mapping.findForward("showAvisForm");
	}
	
	/** 
	 * Method showMyEncheres
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward saveAvis(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		AvisForm avisForm = (AvisForm) form;
		ArticleFacadeDelegate articleFacade = ArticleFacadeDelegate.getInstance();
		String articleId = avisForm.getArticleId();
		String avis = avisForm.getAvis();
		try {
			articleFacade.deposerTransactionAvis(articleId, avis);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return mapping.findForward("showAchats");
	}

}

