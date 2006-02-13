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
import axlomoso.ezbay.model.views.MembreView;

/**
 * MyEclipse Struts Creation date: 11-04-2004
 * 
 * XDoclet definition:
 * 
 * @struts:form name="InscriptionForm"
 */

public class InscriptionForm extends ActionForm {

	private MembreView membreView = new MembreView();

	private String password2 = null;
	private String stringDateNaissance = null;

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		membreView.setId(null);
		membreView.setNom(null);
		membreView.setPrenom(null);
		membreView.setPseudo(null);
		membreView.setPassword(null);
		password2 = null;
		membreView.setEmail(null);
		membreView.setAdresse(null);
		membreView.setCodePostal(null);
		membreView.setVille(null);
		membreView.setPays(null);
		membreView.setTelephoneFixe(null);
		membreView.setTelephonePortable(null);
		stringDateNaissance = null;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ((membreView.getNom() == null) || (membreView.getNom().length() == 0)) {
			errors.add("nom", new ActionError("inscription.erreur.nom.obligatoire"));
		}
		if ((membreView.getPrenom() == null) || (membreView.getPrenom().length() == 0)) {
			errors.add("prenom", new ActionError("inscription.erreur.prenom.obligatoire"));
		}
		if ((membreView.getPseudo() == null) || (membreView.getPseudo().length() == 0)) {
			errors.add("pseudo", new ActionError("inscription.erreur.pseudo.obligatoire"));
		}
		if ((membreView.getPassword() == null) || (membreView.getPassword().length() == 0)) {
			errors.add("password", new ActionError("inscription.erreur.password.obligatoire"));
		}
		if ((password2 == null) || (password2.length() == 0)) {
			errors.add("password2", new ActionError("inscription.erreur.password2.obligatoire"));
		}
		if ((membreView.getEmail() == null) || (membreView.getEmail().length() == 0)) {
			errors.add("email", new ActionError("inscription.erreur.email.obligatoire"));
		}
		if ((membreView.getAdresse() == null) || (membreView.getAdresse().length() == 0)) {
			errors.add("adresse",new ActionError("inscription.erreur.adresse.obligatoire"));
		}
		if ((membreView.getCodePostal() == null) || (membreView.getCodePostal().length() == 0)) {
			errors.add("codePostal", new ActionError("inscription.erreur.codePostal.obligatoire"));
		}
		if ((membreView.getVille() == null) || (membreView.getVille().length() == 0)) {
			errors.add("ville", new ActionError("inscription.erreur.ville.obligatoire"));
		}
		if ((membreView.getPays() == null) || (membreView.getPays().length() == 0)) {
			errors.add("pays", new ActionError("inscription.erreur.pays.obligatoire"));
		}
		if ((membreView.getTelephoneFixe() == null) || (membreView.getTelephoneFixe().length() == 0)) {
			errors.add("telephoneFixe", new ActionError("inscription.erreur.telephoneFixe.obligatoire"));
		}
		if ((membreView.getTelephonePortable() == null) || (membreView.getTelephonePortable().length() == 0)) {
			errors.add("telephonePortable", new ActionError("inscription.erreur.telephonePortable.obligatoire"));
		}
		if (stringDateNaissance == null) {
			errors.add("dateNaissance", new ActionError("inscription.erreur.dateNaissance.obligatoire"));
		}
		else{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			try {
				df.parse(stringDateNaissance);
				membreView.setDateNaissance(df.parse(stringDateNaissance));
			} catch (ParseException e) {
				// dateFormat incorrect
				errors.add("dateNaissance", new ActionError("inscription.erreur.dateNaissance.badFormat"));
			}
		}
		if (!membreView.getPassword().equals(password2)) {
			errors.add("passwordconfirm", new ActionError("inscription.erreur.password.confirmation"));
		}

		return errors;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getStringDateNaissance() {
		String tRes = "";
		if(membreView.getDateNaissance() != null){
			Calendar tCal = Calendar.getInstance();
			tCal.setTime(membreView.getDateNaissance());
			tRes = tCal.get(Calendar.DAY_OF_MONTH) + "/" +
				(tCal.get(Calendar.MONTH) +1) + "/" +
				tCal.get(Calendar.YEAR);		
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			tRes = df.format(membreView.getDateNaissance()); 
		}
		stringDateNaissance = tRes;
		return stringDateNaissance;
	}

	public void setStringDateNaissance(String stringDateNaissance) {
		this.stringDateNaissance = stringDateNaissance;
	}

	public String getAdresse() {
		return membreView.getAdresse();
	}

	public String getCodePostal() {
		return membreView.getCodePostal();
	}

	public Date getDateNaissance() {
		return membreView.getDateNaissance();
	}

	public String getEmail() {
		return membreView.getEmail();
	}

	public String getId() {
		return membreView.getId();
	}

	public String getNom() {
		return membreView.getNom();
	}

	public String getPassword() {
		return membreView.getPassword();
	}

	public String getPays() {
		return membreView.getPays();
	}

	public String getPrenom() {
		return membreView.getPrenom();
	}

	public String getPseudo() {
		return membreView.getPseudo();
	}

	public String getTelephoneFixe() {
		return membreView.getTelephoneFixe();
	}

	public String getTelephonePortable() {
		return membreView.getTelephonePortable();
	}

	public String getVille() {
		return membreView.getVille();
	}

	public void setAdresse(String adresse) {
		membreView.setAdresse(adresse);
	}

	public void setCodePostal(String codePostal) {
		membreView.setCodePostal(codePostal);
	}

	public void setDateNaissance(Date dateNaissance) {
		membreView.setDateNaissance(dateNaissance);
	}

	public void setEmail(String email) {
		membreView.setEmail(email);
	}

	public void setId(String id) {
		membreView.setId(id);
	}

	public void setNom(String nom) {
		membreView.setNom(nom);
	}

	public void setPassword(String password) {
		membreView.setPassword(password);
	}

	public void setPays(String pays) {
		membreView.setPays(pays);
	}

	public void setPrenom(String prenom) {
		membreView.setPrenom(prenom);
	}

	public void setPseudo(String pseudo) {
		membreView.setPseudo(pseudo);
	}

	public void setTelephoneFixe(String telephoneFixe) {
		membreView.setTelephoneFixe(telephoneFixe);
	}

	public void setTelephonePortable(String telephonePortable) {
		membreView.setTelephonePortable(telephonePortable);
	}

	public void setVille(String ville) {
		membreView.setVille(ville);
	}

	public String toString() {
		return membreView.toString();
	}
	
	
}
