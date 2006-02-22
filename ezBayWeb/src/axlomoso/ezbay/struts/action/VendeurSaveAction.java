//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.struts.form.VendeurForm;

/** 
 * MyEclipse Struts
 * Creation date: 02-17-2006
 * 
 * XDoclet definition:
 * @struts.action path="/vendeurSave" name="vendeurForm" input="/jsp/vendeurEdit.jsp" scope="request" validate="true"
 */
public class VendeurSaveAction extends Action {

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
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("VendeurSaveAction.execute()");
		VendeurForm inscriptionVendeurForm = (VendeurForm) form;
		MembreFacadeDelegate membreDelegate;
		membreDelegate = MembreFacadeDelegate.getInstance();
		MembreDTO membreDTO = (MembreDTO) request.getSession().getAttribute("membre");
		membreDelegate.saveVendeur(membreDTO, inscriptionVendeurForm.getVendeurDTO());
		return (mapping.findForward("succes"));
	}


}

