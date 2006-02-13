package axlomoso.ezbay.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.views.MembreView;
import axlomoso.ezbay.struts.form.InscriptionForm;

public class InscriptionAction extends Action {

	public static final String custTOAttribute = "InscriptionForm";

	String target = "";
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionErrors erreurs = new ActionErrors();
		InscriptionForm membreForm = (InscriptionForm) form;
		MembreFacadeDelegate membreDelegate = new MembreFacadeDelegate();
		MembreView membreViewExist = membreDelegate.getMembre(membreForm.getPseudo(), membreForm.getPassword());
		if (membreViewExist != null) {
			erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("inscription.erreur.existMembre"));
			target = "echec";
			System.out.println("Inscription echec !");
		} else {
			System.out.println("Inscription succes !");
			target = "succes";
			MembreDTO membreDTO = new MembreDTO();
			membreDTO.setNom(membreForm.getNom());
			membreDTO.setPrenom(membreForm.getPrenom());
			membreDTO.setPseudo(membreForm.getPseudo());
			membreDTO.setPassword(membreForm.getPassword());
			membreDTO.setEmail(membreForm.getEmail());
			membreDTO.setAdresse(membreForm.getAdresse());
			membreDTO.setCodePostal(membreForm.getCodePostal());
			membreDTO.setVille(membreForm.getVille());
			membreDTO.setPays(membreForm.getPays());
			membreDTO.setTelephoneFixe(membreForm.getTelephoneFixe());
			membreDTO.setTelephonePortable(membreForm.getTelephonePortable());
			membreDTO.setDateNaissance(membreForm.getDateNaissance());
			
			MembreView membreViewCreated = membreDelegate.createMembre(membreDTO);
			membreForm.setId(membreViewCreated.getId());
			MembreView membre = membreDelegate.getMembre(membreViewCreated.getId());
			request.getSession().setAttribute("membre",membre);
		}
		if (!erreurs.isEmpty()) {
			saveErrors(request, erreurs);
		}
		return (mapping.findForward(target));

	}

}
