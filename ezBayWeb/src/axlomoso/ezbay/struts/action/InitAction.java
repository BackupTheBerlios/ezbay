//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.delegate.CategorieFacadeDelegate;

/** 
 * MyEclipse Struts
 * Creation date: 02-27-2006
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class InitAction extends Action {

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
		CategorieFacadeDelegate categorieFacade = CategorieFacadeDelegate.getInstance();
		Collection categories = new ArrayList();
		try {
			categories = categorieFacade.getCategories();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("categories", categories);
		return mapping.findForward("next");
	}

}

