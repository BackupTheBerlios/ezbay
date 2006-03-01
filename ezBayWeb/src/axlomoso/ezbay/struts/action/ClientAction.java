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
import axlomoso.ezbay.delegate.ClientFacadeDelegate;
import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.struts.form.ClientForm;
import axlomoso.ezbay.struts.views.ArticleView;

/** 
 * MyEclipse Struts
 * Creation date: 02-28-2006
 * 
 * XDoclet definition:
 * @struts.action parameter="do" validate="true"
 */
public class ClientAction extends DispatchAction {

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
	public ActionForward showMyAchats(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		ClientForm clientForm = (ClientForm) form;
		try {
			MembreDTO membreDTO = (MembreDTO)request.getSession().getAttribute("membre");
			ClientFacadeDelegate clientFacade = ClientFacadeDelegate.getInstance();
			MembreFacadeDelegate membreFacade = MembreFacadeDelegate.getInstance();
			ArticleFacadeDelegate articleFacade = ArticleFacadeDelegate.getInstance();
			ClientDTO clientDTO = membreFacade.getClientDTOByMembreId(membreDTO.getId());
			Collection mesArticlesDTOAchetes = clientFacade.getArticlesAchetes(clientDTO.getId());
			clientForm.setArticlesView(articleFacade.getArticlesDtoToView(mesArticlesDTOAchetes));
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("showAchats");
	}
	
	/** 
	 * Method showMyEncheres
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showMyEncheres(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			ClientForm clientForm = (ClientForm) form;
			try {
				MembreDTO membreDTO = (MembreDTO)request.getSession().getAttribute("membre");
				ClientFacadeDelegate clientFacade = ClientFacadeDelegate.getInstance();
				MembreFacadeDelegate membreFacade = MembreFacadeDelegate.getInstance();
				ArticleFacadeDelegate articleFacade = ArticleFacadeDelegate.getInstance();
				ClientDTO clientDTO = membreFacade.getClientDTOByMembreId(membreDTO.getId());
				Collection mesArticlesDTOEncheris = clientFacade.getArticlesEnEncheres(clientDTO.getId());
				clientForm.setArticlesView(articleFacade.getArticlesDtoToView(mesArticlesDTOEncheris));
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mapping.findForward("showEncheres");
	}
	
	
	
	
	/** 
	 * Method showMyEncheres
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward deposerAvis(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			ClientForm clientForm = (ClientForm) form;
			ArticleFacadeDelegate articleFacade = ArticleFacadeDelegate.getInstance();
			String articleId = request.getParameter("articleId");
			Collection articlesView = clientForm.getArticlesView();
			String avis = null;
			Iterator it = articlesView.iterator();
			while(it.hasNext() && (avis == null)){
				ArticleView articleView = (ArticleView) it.next();
				if( articleView.getId().equals(articleId) ){
					avis = articleView.getTransactionAvis();
				}
			}
			try {
				articleFacade.deposerTransactionAvis(articleId, avis);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			return mapping.findForward("showAchats");
	}
}

