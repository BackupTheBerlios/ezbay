//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.delegate.VendeurFacadeDelegate;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacade;
import axlomoso.ezbay.model.interfaces.VendeurFacadeHome;
import axlomoso.ezbay.struts.form.ArticleForm;
import axlomoso.ezbay.struts.form.MembreForm;
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
public class MembreAction extends DispatchAction {
	public static final String custTOAttribute = "VendeurForm";

	String target = "";

	
	/*
	 * cr�er des forwards pour : 
	 * - showForm
	 * - ...
	 */ 
		
	public ActionForward showInscription(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().removeAttribute("membre");
		return (mapping.findForward("showInscription"));
	}
	
	public ActionForward showInformations(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MembreForm membreForm = (MembreForm) form;
		MembreDTO membreDTO = (MembreDTO) request.getSession().getAttribute("membre");
		membreForm.setMembreDTO(membreDTO);
		return (mapping.findForward("showInformations"));
	}
	
	public ActionForward showEditMembre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MembreForm membreForm = (MembreForm) form;
		MembreDTO membreDTO = (MembreDTO) request.getSession().getAttribute("membre");
		membreForm.setMembreDTO(membreDTO);
		return (mapping.findForward("showEdit"));
	}

	public ActionForward showMembreFiche(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MembreForm membreForm = (MembreForm) form;
		MembreFacadeDelegate membreFacade = MembreFacadeDelegate.getInstance();
		String membreId = request.getParameter("membreId");
		//membre
		MembreDTO membreDTO = membreFacade.getMembreById(membreId);
		membreForm.setId(membreDTO.getId());
		membreForm.setPseudo(membreDTO.getPseudo());
		//vendeur
		VendeurFacadeDelegate vendeurFacade = VendeurFacadeDelegate.getInstance();
		String vendeurId = null;
		String clientId = membreFacade.getClientDTOByMembreId(membreId).getId();
		VendeurDTO vendeurDTO = membreFacade.getVendeurDTOByMembreId(membreId);
		if (vendeurDTO!=null){
			vendeurId = vendeurDTO.getId();			
			membreForm.setVendeurId(vendeurId);		
			membreForm.setArticlesViewEnVente(vendeurFacade.getArticlesEnVente(vendeurId));			
			membreForm.setArticlesViewVendus(vendeurFacade.getArticlesVendus(vendeurId));					
		}				
		membreForm.setClientId(clientId);
		return (mapping.findForward("showMembreFiche"));
	}
	
	
}
