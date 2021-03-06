//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.delegate.ArticleFacadeDelegate;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.struts.form.ArticleListForm;
import axlomoso.ezbay.struts.form.RechercheForm;

/** 
 * MyEclipse Struts
 * Creation date: 02-19-2006
 * 
 * XDoclet definition:
 * @struts.action path="/rechercheArticle" name="rechercheForm" scope="request" validate="true"
 */
public class RechercheArticleAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response	 
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {		
		RechercheForm rechercheForm = (RechercheForm) form;			
		try {
			ArticleFacadeDelegate articleFacade = ArticleFacadeDelegate.getInstance();
			ArticleDTO articleDTO=rechercheForm.getArticleDTO();			
			String idCategorie=rechercheForm.getCategorieDTO().getId();
			Collection result=articleFacade.rechercherArticlesEnVente(idCategorie,articleDTO.getLibelle(),articleDTO.getMarque(),articleDTO.getModele(),rechercheForm.getPrixVenteMin(),rechercheForm.getPrixVenteMax(),articleDTO.getAnneeFabrication(),articleDTO.getDateLimite());
			rechercheForm.setArticlesView(result);
			rechercheForm.setNbArticles(result.size());
			request.setAttribute("showResult", "true");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("showList");
	}


}

