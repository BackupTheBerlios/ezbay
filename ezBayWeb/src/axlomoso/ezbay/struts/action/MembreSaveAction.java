package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.exceptions.PseudoDejaUtiliseException;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.struts.form.MembreForm;

public class MembreSaveAction extends Action {

	public static final String custTOAttribute = "MembreForm";
	String target = "";
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("MembreSaveAction.execute()");
		ActionErrors erreurs = new ActionErrors();
		MembreForm inscriptionForm = (MembreForm) form;
		String id = request.getParameter("id");
		MembreDTO membreDTO = inscriptionForm.getMembreDTO();
		if( ( id == null ) || ( id.length() == 0 )){
			// nouvelle inscription
			try{
				performSaveMembre(request, membreDTO);
				target = "succesInscription";
			}catch (PseudoDejaUtiliseException e){
				erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("inscription.erreur.pseudoDejaUtilise"));
				target = "echec";
			}
		}else{
			// mis à jour des infos personnelles
			performSaveMembre(request, membreDTO);
			target = "succesUpdate";
		}
		if (!erreurs.isEmpty()) {
			saveErrors(request, erreurs);
		}
		return (mapping.findForward(target));
	}
	
	/**
	 * @param request
	 * @param membreDTO
	 * @throws Exception
	 * @throws RemoteException
	 * @throws PseudoDejaUtiliseException
	 */
	private void performSaveMembre(HttpServletRequest request, MembreDTO membreDTO) throws Exception, RemoteException, PseudoDejaUtiliseException {
		MembreFacadeDelegate membreDelegate = MembreFacadeDelegate.getInstance();
		MembreDTO membreDTOCreated = membreDelegate.saveMembre(membreDTO);
		request.getSession().setAttribute("membre",membreDTOCreated);
	}

}
