//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.delegate.VendeurFacadeDelegate;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.struts.form.ArticleForm;
import axlomoso.ezbay.struts.form.VendeurForm;

/** 
 * MyEclipse Struts
 * Creation date: 02-17-2006
 * 
 * XDoclet definition:
 * @struts.action path="/articleSave" name="articleEditForm" input="/jsp/articleEdit.jsp" scope="request" validate="true"
 */
public class ArticleSaveAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

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
		
		System.out.println("ArticleSaveAction.execute()");
		ArticleForm articleEditForm = (ArticleForm) form;
		
		/* load the session facade and save the book by primary key	*/

		try {
			VendeurFacadeDelegate vendeurFacade = new VendeurFacadeDelegate();
			MembreFacadeDelegate membreFacade = new MembreFacadeDelegate();
			MembreDTO membreDTO = (MembreDTO)request.getSession().getAttribute("membre");
			VendeurDTO vendeurDTO = membreFacade.getVendeurDTO(membreDTO.getId());
			vendeurFacade.saveArticle(vendeurDTO.getId(), articleEditForm.getArticleDTO(), articleEditForm.getCategorieDTO().getId());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return mapping.findForward("showList");
		return mapping.findForward("showVendeurArticles");
	}

}

