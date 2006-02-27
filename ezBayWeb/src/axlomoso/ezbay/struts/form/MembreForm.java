package axlomoso.ezbay.struts.form;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.utils.Util;

/**
 * MyEclipse Struts Creation date: 11-04-2004
 * 
 * XDoclet definition:
 * 
 * @struts:form name="MembreForm"
 */

public class MembreForm extends ActionForm {

	private MembreDTO membreDTO = new MembreDTO();
	private String password2 = null;
	private String stringDateNaissance = null;
	private Util util = new Util();
	
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getStringDateNaissance() {
		stringDateNaissance = "";
		if(membreDTO.getDateNaissance() != null){
			stringDateNaissance = util.getDateToString(membreDTO.getDateNaissance(), "dd/MM/yyyy");
		}
		return stringDateNaissance;
	}

	public void setStringDateNaissance(String stringDateNaissance) {
		this.stringDateNaissance = stringDateNaissance;
	}

	public String getAdresse() {
		return membreDTO.getAdresse();
	}

	public String getCodePostal() {
		return membreDTO.getCodePostal();
	}

	public Date getDateNaissance() {
		return membreDTO.getDateNaissance();
	}

	public String getEmail() {
		return membreDTO.getEmail();
	}

	public String getId() {
		return membreDTO.getId();
	}

	public String getNom() {
		return membreDTO.getNom();
	}

	public String getPassword() {
		return membreDTO.getPassword();
	}

	public String getPays() {
		return membreDTO.getPays();
	}

	public String getPrenom() {
		return membreDTO.getPrenom();
	}

	public String getPseudo() {
		return membreDTO.getPseudo();
	}

	public String getTelephoneFixe() {
		return membreDTO.getTelephoneFixe();
	}

	public String getTelephonePortable() {
		return membreDTO.getTelephonePortable();
	}

	public String getVille() {
		return membreDTO.getVille();
	}

	public void setAdresse(String adresse) {
		membreDTO.setAdresse(adresse);
	}

	public void setCodePostal(String codePostal) {
		membreDTO.setCodePostal(codePostal);
	}

	public void setDateNaissance(Date dateNaissance) {
		membreDTO.setDateNaissance(dateNaissance);
	}

	public void setEmail(String email) {
		membreDTO.setEmail(email);
	}

	public void setId(String id) {
		membreDTO.setId(id);
	}

	public void setNom(String nom) {
		membreDTO.setNom(nom);
	}

	public void setPassword(String password) {
		membreDTO.setPassword(password);
	}

	public void setPays(String pays) {
		membreDTO.setPays(pays);
	}

	public void setPrenom(String prenom) {
		membreDTO.setPrenom(prenom);
	}

	public void setPseudo(String pseudo) {
		membreDTO.setPseudo(pseudo);
	}

	public void setTelephoneFixe(String telephoneFixe) {
		membreDTO.setTelephoneFixe(telephoneFixe);
	}

	public void setTelephonePortable(String telephonePortable) {
		membreDTO.setTelephonePortable(telephonePortable);
	}

	public void setVille(String ville) {
		membreDTO.setVille(ville);
	}

	public String toString() {
		return membreDTO.toString();
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {
		membreDTO.setId(null);
		membreDTO.setNom(null);
		membreDTO.setPrenom(null);
		membreDTO.setPseudo(null);
		membreDTO.setPassword(null);
		password2 = null;
		membreDTO.setEmail(null);
		membreDTO.setAdresse(null);
		membreDTO.setCodePostal(null);
		membreDTO.setVille(null);
		membreDTO.setPays(null);
		membreDTO.setTelephoneFixe(null);
		membreDTO.setTelephonePortable(null);
		stringDateNaissance = null;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ((membreDTO.getNom() == null) || (membreDTO.getNom().length() == 0)) {
			errors.add("nom", new ActionError("inscription.erreur.nom.obligatoire"));
		}
		if ((membreDTO.getPrenom() == null) || (membreDTO.getPrenom().length() == 0)) {
			errors.add("prenom", new ActionError("inscription.erreur.prenom.obligatoire"));
		}
		if ((membreDTO.getPseudo() == null) || (membreDTO.getPseudo().length() == 0)) {
			errors.add("pseudo", new ActionError("inscription.erreur.pseudo.obligatoire"));
		}
		if ((membreDTO.getPassword() == null) || (membreDTO.getPassword().length() == 0)) {
			errors.add("password", new ActionError("inscription.erreur.password.obligatoire"));
		}
		if ((password2 == null) || (password2.length() == 0)) {
			errors.add("password2", new ActionError("inscription.erreur.password2.obligatoire"));
		}
		if ((membreDTO.getEmail() == null) || (membreDTO.getEmail().length() == 0)) {
			errors.add("email", new ActionError("inscription.erreur.email.obligatoire"));
		}
		else{
			if(! util.checkMailAddress(membreDTO.getEmail())) errors.add("email", new ActionError("inscription.erreur.email.invalide"));
		}
		if ((membreDTO.getAdresse() == null) || (membreDTO.getAdresse().length() == 0)) {
			errors.add("adresse",new ActionError("inscription.erreur.adresse.obligatoire"));
		}
		
		if (membreDTO.getCodePostal() == null){
			errors.add("codePostal", new ActionError("inscription.erreur.codePostal.obligatoire"));
		}
		else{
			try{
				if ((membreDTO.getCodePostal().length() >5)
						|| (Integer.parseInt(membreDTO.getCodePostal()) == 0))
					errors.add("codePostal", new ActionError(
					"inscription.erreur.codePostal.badFormat"));
			}
			catch (NumberFormatException e) {
			errors.add("codePostal", new ActionError("inscription.erreur.codePostal.badFormat"));}
		}
		
		if ((membreDTO.getVille() == null) || (membreDTO.getVille().length() == 0)) {
			errors.add("ville", new ActionError("inscription.erreur.ville.obligatoire"));
		}
		if ((membreDTO.getPays() == null) || (membreDTO.getPays().length() == 0)) {
			errors.add("pays", new ActionError("inscription.erreur.pays.obligatoire"));
		}

		if (((membreDTO.getTelephoneFixe() == null)||(membreDTO.getTelephoneFixe().length() == 0))
				&& ((membreDTO.getTelephonePortable() == null)||(membreDTO.getTelephonePortable().length() == 0))) {
			errors.add("telephoneOblig", new ActionError(
					"inscription.erreur.telephone.obligatoire"));
		} else {
			try {
				if ((membreDTO.getTelephoneFixe() != null)&&(membreDTO.getTelephoneFixe().length() != 0)){
					 long p=Long.parseLong(membreDTO.getTelephoneFixe());					
				}
				if ((membreDTO.getTelephonePortable() != null)&&(membreDTO.getTelephonePortable().length() != 0)){
					long x=Long.parseLong(membreDTO.getTelephonePortable());					
				}
			} catch (NumberFormatException e) {
				errors.add("telephoneformat", new ActionError("inscription.erreur.telephone.badFormat"));
			}
		} 
		
		if (stringDateNaissance == null) {
			errors.add("dateNaissance", new ActionError("inscription.erreur.dateNaissance.obligatoire"));
		}
		else{
			try {
				Date tDate = util.getStringToDate(stringDateNaissance, "dd/MM/yyyy");
				membreDTO.setDateNaissance(tDate);
			} catch (ParseException e) {
				// dateFormat incorrect
				errors.add("dateNaissance", new ActionError("inscription.erreur.dateNaissance.badFormat"));
			}
		}
		if (!membreDTO.getPassword().equals(password2)) {
			errors.add("passwordconfirm", new ActionError("inscription.erreur.password.confirmation"));
		}

		return errors;
	}

	public MembreDTO getMembreDTO() {
		return membreDTO;
	}

	public void setMembreDTO(MembreDTO membreDTO) {
		this.membreDTO = membreDTO;
	}
}
