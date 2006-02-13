//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;
import java.util.Collection;

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


import axlomoso.ezbay.struts.form.ArticleListForm;
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
public class ArticleListAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	private ArticleFacade articleFacade;

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
		ArticleListForm articleListForm = (ArticleListForm) form;
		try {
			MembreDTO membre = (MembreDTO)request.getSession().getAttribute("membre");
			InitialContext jndiContext = new InitialContext();
			Collection articleViews = null;
			Object ref = null;
			// en attendant : tous les articles
			ref = jndiContext.lookup(ArticleFacadeHome.JNDI_NAME);
			ArticleFacadeHome facadeHome = (ArticleFacadeHome) PortableRemoteObject.narrow(ref, ArticleFacadeHome.class);
			this.articleFacade = facadeHome.create();
			articleViews = articleFacade.getArticles();
			if(membre == null){
				// Tous les articles
			}
			else{
				// articles du membre
			}
			articleListForm.setArticleViews(articleViews);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}
			return mapping.findForward("showList");
		}

}

