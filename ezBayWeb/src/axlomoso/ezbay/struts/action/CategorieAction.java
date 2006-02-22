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
import org.apache.struts.actions.DispatchAction;

import axlomoso.ezbay.delegate.CategorieFacadeDelegate;
import axlomoso.ezbay.delegate.ArticleFacadeDelegate;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.struts.form.ArticleListForm;
import axlomoso.ezbay.struts.form.CategorieForm;


/** 
 * MyEclipse Struts
 * Creation date: 02-19-2006
 * 
 * XDoclet definition:
 * @struts.action path="/categorie" name="categorieForm" parameter="do" scope="request" validate="true"
 */
public class CategorieAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables
	String target = "";
	// --------------------------------------------------------- Methods

	
	public ActionForward showCategories(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("CategorieAction.showCategories()");
		CategorieForm categorieForm = (CategorieForm) form;
		CategorieFacadeDelegate categorieFacadeDelegate=CategorieFacadeDelegate.getInstance();
		try {
			categorieForm.setListeCategories(categorieFacadeDelegate.getCategories());
		}
		catch(Exception e){
		   e.printStackTrace();
		}			
		return (mapping.findForward("showCategories"));
	}
	
	public ActionForward showCategoriesRecherche(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("CategorieAction.showCategoriesRecherche()");
		try {
			CategorieFacadeDelegate categorieFacade = CategorieFacadeDelegate.getInstance();
			Collection categories = categorieFacade.getCategories();
			request.getSession().setAttribute("categories", categories);
		}
		catch(Exception e){
		   e.printStackTrace();
		}			
		return (mapping.findForward("showCategoriesRecherche"));
	}

	
	

	
	
}

