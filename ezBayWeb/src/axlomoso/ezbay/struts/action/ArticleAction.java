//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import axlomoso.ezbay.struts.form.ArticleForm;
import axlomoso.ezbay.delegate.ArticleFacadeDelegate;
import axlomoso.ezbay.delegate.CategorieFacadeDelegate;
import axlomoso.ezbay.delegate.VendeurFacadeDelegate;
import axlomoso.ezbay.delegate.VendeurFacadeDelegate;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;

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
		System.out.println("ArticleAction.showEdit()");
		ArticleForm articleEditForm = (ArticleForm) form;
		/* get id of the book from request */
		String id = request.getParameter("id");
		/* load the session facade and get the book by primary key */
		try {
			ArticleFacadeDelegate articleFacade = new ArticleFacadeDelegate();
			CategorieFacadeDelegate categorieFacade = new CategorieFacadeDelegate();
			ArticleDTO articleDTO = new ArticleDTO();
			if( (id != null) && (id.length() > 0) ){
				// modification
				articleDTO = articleFacade.getArticle(id);
				articleEditForm.setCategorieDTO(articleFacade.getCategorieDTO(id));
			}
			articleEditForm.setArticleDTO(articleDTO);
			articleEditForm.setCategories(categorieFacade.getCategories());
		} catch (RemoteException e) {
		e.printStackTrace();
		} catch (NamingException e) {
		e.printStackTrace();
		} catch (CreateException e) {
		e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("showEdit");	
	}
	
	public ActionForward showArticleFiche(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("ArticleAction.showArticleFiche()");
		ArticleForm articleEditForm = (ArticleForm) form;
		/* get id of the book from request */
		String id = request.getParameter("id");
		/* load the session facade and get the book by primary key */
		try {
			ArticleFacadeDelegate articleFacade = new ArticleFacadeDelegate();
			VendeurFacadeDelegate vendeurFacade = new VendeurFacadeDelegate();
			String vendeurId=articleFacade.getVendeurDTO(id).getId();
			articleEditForm.setArticleDTO(articleFacade.getArticle(id));
			articleEditForm.setVendeurId(vendeurId);
			articleEditForm.setMembrePseudo(vendeurFacade.getMembre(vendeurId).getPseudo());
		} catch (RemoteException e) {
		e.printStackTrace();
		} catch (NamingException e) {
		e.printStackTrace();
		} catch (CreateException e) {
		e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("showArticleFiche");	
	}

	
	public ActionForward deleteArticle(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}
	
	public ActionForward showAdd(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("showAdd");
	}

}

