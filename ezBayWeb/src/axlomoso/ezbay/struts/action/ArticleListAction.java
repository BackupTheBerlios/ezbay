//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import axlomoso.ezbay.struts.form.ArticleListForm;
import axlomoso.ezbay.struts.form.RechercheForm;
import axlomoso.ezbay.delegate.ArticleFacadeDelegate;
import axlomoso.ezbay.delegate.CategorieFacadeDelegate;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreFacade;
import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurFacade;
import axlomoso.ezbay.model.interfaces.VendeurFacadeHome;

/** 
 * MyEclipse Struts
 * Creation date: 02-12-2006
 * 
 * XDoclet definition:
 * @struts.action path="/articleList" name="articleListForm" input="/jsp/articleList.jsp" scope="request" validate="true"
 */
public class ArticleListAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	private ArticleFacade articleFacade;

	public ActionForward showArticlesByCategorie(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			ArticleListForm articleListForm = (ArticleListForm) form;
			String categorieId = request.getParameter("categorieId");
			try {
				ArticleFacadeDelegate articleFacade = ArticleFacadeDelegate.getInstance();
				CategorieFacadeDelegate categorieFacade = CategorieFacadeDelegate.getInstance();
				if( categorieId == null ) categorieId = "";
				Collection articles = articleFacade.getArticlesEnVenteByCategorie(categorieId);
				articleListForm.setArticlesView(articles);
				articleListForm.setCategorieDTO(categorieFacade.getCategorie(categorieId));
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

