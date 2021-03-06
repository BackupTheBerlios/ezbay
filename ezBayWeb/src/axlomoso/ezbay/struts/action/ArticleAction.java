//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import axlomoso.ezbay.struts.form.ArticleForm;
import axlomoso.ezbay.delegate.ArticleFacadeDelegate;
import axlomoso.ezbay.delegate.CategorieFacadeDelegate;
import axlomoso.ezbay.delegate.ClientFacadeDelegate;
import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.delegate.VendeurFacadeDelegate;
import axlomoso.ezbay.delegate.VendeurFacadeDelegate;
import axlomoso.ezbay.exceptions.ArticleEnEnchereException;
import axlomoso.ezbay.exceptions.ArticleEnVenteException;
import axlomoso.ezbay.exceptions.ArticleProprietaireException;
import axlomoso.ezbay.exceptions.ArticleVenduException;
import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;

/** 
 * MyEclipse Struts
 * Creation date: 02-12-2006
 * 
 * XDoclet definition:
 * @struts.action path="/articleEdit" name="articleEditForm" parameter="do" scope="request" validate="true"
 */
public class ArticleAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	public ActionForward showEdit(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			
			this.setArticleForm(id, form, request);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return mapping.findForward("showEdit");	
	}
	
	public ActionForward showArticleFiche(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			this.setArticleForm(id, form, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("showArticleFiche");	
	}

	public ActionForward supprimerArticle(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			this.setArticleForm(id, form, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("showConfirmRetrait");
	}
	
	public ActionForward confirmSupprimerArticle(
			// suppression d'un article ==> suppression de la bd
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		String target = "showVendeurArticles";
		ActionErrors erreurs = new ActionErrors();
		String articleId = request.getParameter("id");
			try {
				MembreFacadeDelegate membreFacade = MembreFacadeDelegate.getInstance();
				MembreDTO membreDTO = (MembreDTO) request.getSession().getAttribute("membre");
				String vendeurId = membreFacade.getVendeurDTOByMembreId(membreDTO.getId()).getId();
				VendeurFacadeDelegate vendeurFacade = VendeurFacadeDelegate.getInstance();
				vendeurFacade.removeArticle(vendeurId, articleId);
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (ArticleProprietaireException e) {
				erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("articleRetrait.erreurs.nonProprietaire"));
				e.printStackTrace();
			} catch (ArticleEnVenteException e) {
				erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("articleRetrait.erreurs.articleEnVente"));
				e.printStackTrace();
			} catch (ArticleVenduException e) {
				erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("articleRetrait.erreurs.articleVendu"));
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!erreurs.isEmpty()) {
				saveErrors(request, erreurs);
				target = "echecDelete";
			}
		return mapping.findForward(target);
	}
	
	public ActionForward retirerArticle(
			//retrait d'un article de la vente ==> d�sactivation, mais reste en BD
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		ActionErrors erreurs = new ActionErrors();
		String articleId = request.getParameter("id");
			try {
				MembreFacadeDelegate membreFacade = MembreFacadeDelegate.getInstance();
				MembreDTO membreDTO = (MembreDTO) request.getSession().getAttribute("membre");
				String vendeurId = membreFacade.getVendeurDTOByMembreId(membreDTO.getId()).getId();
				VendeurFacadeDelegate vendeurFacade = VendeurFacadeDelegate.getInstance();
				vendeurFacade.retirerArticle(vendeurId, articleId);				
				
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (ArticleProprietaireException e) {
				erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("articleRetrait.erreurs.nonProprietaire"));
				e.printStackTrace();
			} catch (ArticleEnEnchereException e) {
				erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("articleRetrait.erreurs.articleEnEnchere"));
				e.printStackTrace();
			} catch (ArticleVenduException e) {
				erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("articleRetrait.erreurs.articleVendu"));
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!erreurs.isEmpty()) {
				saveErrors(request, erreurs);
			}
		return mapping.findForward("showVendeurArticles");
	}	
	
	public ActionForward mettreEnVenteArticle(
			//mise en vente d'un article existant
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		String target = "showVendeurArticles";
		ActionErrors erreurs = new ActionErrors();
		String articleId = request.getParameter("id");
			try {
				MembreFacadeDelegate membreFacade = MembreFacadeDelegate.getInstance();
				MembreDTO membreDTO = (MembreDTO) request.getSession().getAttribute("membre");
				String vendeurId = membreFacade.getVendeurDTOByMembreId(membreDTO.getId()).getId();
				VendeurFacadeDelegate vendeurFacade = VendeurFacadeDelegate.getInstance();
				vendeurFacade.mettreEnVenteArticle(vendeurId, articleId);				
			} catch (ArticleProprietaireException e) {
				erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("articleRetrait.erreurs.nonProprietaire"));
				e.printStackTrace();
			} catch (ArticleVenduException e) {
				erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("articleRetrait.erreurs.articleVendu"));
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!erreurs.isEmpty()) {
				saveErrors(request, erreurs);
				target = "echecMiseEnVente";
			}
		return mapping.findForward(target);
	}	
	
	private void setArticleForm(String articleId, ActionForm form, HttpServletRequest request) throws Exception{
		ArticleForm articleForm = (ArticleForm) form;	
		ArticleFacadeDelegate articleFacade = ArticleFacadeDelegate.getInstance();
		ArticleDTO articleDTO = new ArticleDTO();
		if( (articleId != null) && (articleId.length() > 0) ){
			articleDTO = articleFacade.getArticle(articleId);
		}
		else{
			Date tDate = new Date(System.currentTimeMillis() + (5 * 24 * 60 * 60 * 1000) );
			//date par d�faut : Date courante + 5 jours
			articleDTO.setDateLimite(tDate);
		}
		articleForm.setArticleView(articleFacade.getArticleViewFromArticleDTO(articleDTO));
	}	
	
	public ActionForward showRechercheForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return (mapping.findForward("showCategoriesRecherche"));
	}
}

