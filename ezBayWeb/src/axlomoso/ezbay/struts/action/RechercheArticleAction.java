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
			ArticleFacadeDelegate articleFacade = new ArticleFacadeDelegate();
			ArticleDTO articleDTO=rechercheForm.getArticleDTO();			
			String idCategorie=rechercheForm.getCategorieDTO().getId();
			System.out.println("id categorie"+idCategorie);			
			System.out.println("libelle article"+articleDTO.getLibelle());			
			System.out.println("marque "+articleDTO.getMarque());
			System.out.println("modele"+articleDTO.getModele());			
			System.out.println("prix vente Min"+rechercheForm.getPrixVenteMin());	
			System.out.println("prix vente Max"+rechercheForm.getPrixVenteMax());
			System.out.println("annee fabrication"+articleDTO.getAnneeFabrication());			
			System.out.println("date limite"+articleDTO.getDateLimite());
			
			Collection result=articleFacade.getArticles(idCategorie,articleDTO.getLibelle(),articleDTO.getMarque(),articleDTO.getModele(),rechercheForm.getPrixVenteMin(),rechercheForm.getPrixVenteMax(),articleDTO.getAnneeFabrication(),articleDTO.getDateLimite());
			rechercheForm.setArticlesDTO(result);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return mapping.findForward("showList");
		}


}

