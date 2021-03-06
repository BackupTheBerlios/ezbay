//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.struts.form.ConnectForm;
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
		String target = "";
		try {
			MembreFacadeDelegate membreFacadeDelegate = MembreFacadeDelegate.getInstance();
			MyEzBayForm myEzBayForm = (MyEzBayForm) form;
			MembreDTO membreDTO = (MembreDTO) request.getSession().getAttribute("membre");
			if(membreDTO == null)
				target = "showConnectForm";
			else{
				myEzBayForm.setMembreDTO(membreDTO);			
				myEzBayForm.setVendeurDTO(membreFacadeDelegate.getVendeurDTOByMembreId(membreDTO.getId()));
				target = "showMyEzBay";
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (mapping.findForward(target));
	}
	
	public ActionForward deconnect(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			request.getSession().removeAttribute("membre");
			request.getSession().removeAttribute("vendeurId");
			return (mapping.findForward("welcome"));
		}
	
}

