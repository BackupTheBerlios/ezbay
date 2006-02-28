//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.delegate.VendeurFacadeDelegate;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.struts.form.VendeurForm;

/**
 * MyEclipse Struts Creation date: 02-15-2006
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/inscriptionVendeur" name="inscriptionVendeurForm"
 *                input="/jsp/inscriptionVendeur.jsp" scope="request"
 *                validate="true"
 */
public class VendeurAction extends DispatchAction {
	public static final String custTOAttribute = "VendeurForm";

	String target = "";

	
	/*
	 * créer des forwards pour : 
	 * - showForm
	 * - ...
	 */ 
		
	public ActionForward showInformations(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("VendeurAction.showInformations()");
		setVendeurForm(form, request);
		return (mapping.findForward("showInformations"));
	}
	
	public ActionForward showEditVendeur(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("VendeurAction.showEditVendeur()");
		setVendeurForm(form, request);
		return (mapping.findForward("showEdit"));
	}
	
	public ActionForward showArticles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("VendeurAction.showArticles()");
		setVendeurForm(form, request);
		return (mapping.findForward("showArticles"));
	}
	

	/**
	 * @param form
	 * @param request
	 * @throws Exception
	 */
	private void setVendeurForm(ActionForm form, HttpServletRequest request) throws Exception {
		VendeurForm vendeurForm = (VendeurForm) form;
		double dGlobal = System.currentTimeMillis();
		MembreFacadeDelegate membreDelegate = MembreFacadeDelegate.getInstance();
		VendeurFacadeDelegate vendeurDelegate = VendeurFacadeDelegate.getInstance();
		try {
			MembreDTO membreDTO = (MembreDTO) request.getSession().getAttribute("membre");
			VendeurDTO vendeurDTO = membreDelegate.getVendeurDTOByMembreId(membreDTO.getId());
			if(vendeurDTO == null){
				vendeurDTO = new VendeurDTO();
			}
			vendeurForm.setVendeurDTO(vendeurDTO);
			vendeurForm.setArticlesEnAttente(vendeurDelegate.getArticlesEnAttente(vendeurDTO.getId()));
			vendeurForm.setArticlesEnVente(vendeurDelegate.getArticlesEnVente(vendeurDTO.getId()));
			vendeurForm.setArticlesVendus(vendeurDelegate.getArticlesVendus(vendeurDTO.getId()));
		} catch (RemoteException e) {
		e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("setVendeurForm - temps execution : " + (System.currentTimeMillis() - dGlobal) + "ms");
	}
	
	
}
