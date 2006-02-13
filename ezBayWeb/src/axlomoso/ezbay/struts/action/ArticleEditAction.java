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


import axlomoso.ezbay.struts.form.ArticleEditForm;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.views.ArticleView;

/** 
 * MyEclipse Struts
 * Creation date: 02-12-2006
 * 
 * XDoclet definition:
 * @struts.action path="/articleEdit" name="articleEditForm" parameter="do" scope="request" validate="true"
 */
public class ArticleEditAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	public ActionForward showEdit(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		ArticleEditForm articleEditForm = (ArticleEditForm) form;
		/* get id of the book from request */
		String id = request.getParameter("id");
		/* load the session facade and get the book by primary key */
		try {
			InitialContext context = new InitialContext();
			ArticleFacadeHome articleFacadeHome = (ArticleFacadeHome)context.lookup(ArticleFacadeHome.JNDI_NAME);
			ArticleFacade articleFacade = articleFacadeHome.create();
			articleEditForm.setArticleDTO(articleFacade.getArticle(id));
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
	
	public ActionForward saveArticle(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		ArticleEditForm articleEditForm = (ArticleEditForm) form;
		/* load the session facade and save the book by primary key	*/
		try {
			InitialContext context = new InitialContext();
			ArticleFacadeHome articleFacadeHome = (ArticleFacadeHome)context.lookup(ArticleFacadeHome.JNDI_NAME);
			ArticleFacade articleFacade = articleFacadeHome.create();
			System.out.println("saveArticle");
			ArticleDTO newArt = articleEditForm.getArticleDTO();
			newArt.setDateLimite(new Date());
			articleFacade.updateArticle(newArt);
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

