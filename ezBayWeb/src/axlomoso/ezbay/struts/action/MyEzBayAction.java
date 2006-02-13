//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.views.MembreView;
import axlomoso.ezbay.struts.form.MyEzBayForm;

/** 
 * MyEclipse Struts
 * Creation date: 02-13-2006
 * 
 * XDoclet definition:
 * @struts.action path="/myEzBay" name="myEzBayForm" input="/jsp/myEzBay.jsp" parameter="do" scope="request" validate="true"
 */
public class MyEzBayAction extends DispatchAction {

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
	public ActionForward showMyEzBay(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		System.out.println("execute");		
		String target = "";
		MembreView membre = (MembreView) request.getSession().getAttribute("membre");
		if(membre == null)
			target = "showConnectForm";
		else
			target = "showMyEzBay";
		return (mapping.findForward(target));
	}

	public ActionForward deconnect(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			request.getSession().removeAttribute("membre");
			System.out.println("deconnect");
			return (mapping.findForward("welcome"));
		}
	
	
	
	
}

