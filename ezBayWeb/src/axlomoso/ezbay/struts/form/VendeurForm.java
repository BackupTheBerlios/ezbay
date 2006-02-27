//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.form;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.utils.Util;

/**
 * MyEclipse Struts Creation date: 02-15-2006
 * 
 * XDoclet definition:
 * 
 * @struts.form name="VendeurForm"
 */
public class VendeurForm extends ActionForm {

	// --------------------------------------------------------- Instance
	private Util util = new Util();
	// Variables
	private VendeurDTO vendeurDTO = new VendeurDTO();
	private String stringDateExpirCB = null;
	private Collection articlesEnAttente = null;
	private Collection articlesEnVente = null;
	private Collection articlesVendus = null;

	// --------------------------------------------------------- Methods

	// ---------------- delegate methods for vendeurDTO

	public String getCodeSecuCB() {
		return vendeurDTO.getCodeSecuCB();
	}

	public Date getDateExpirCB() {
		return vendeurDTO.getDateExpirCB();
	}

	public String getId() {
		return vendeurDTO.getId();
	}

	public String getNomProprioCB() {
		return vendeurDTO.getNomProprioCB();
	}

	public String getNumCB() {
		return vendeurDTO.getNumCB();
	}

	public void setCodeSecuCB(String codeSecuCB) {
		vendeurDTO.setCodeSecuCB(codeSecuCB);
	}

	public void setDateExpirCB(Date dateExpirCB) {
		vendeurDTO.setDateExpirCB(dateExpirCB);
	}

	public void setNumCB(String numCB) {
		vendeurDTO.setNumCB(numCB);
	}

	public void setId(String id) {
		vendeurDTO.setId(id);
	}

	public void setNomProprioCB(String nomProprioCB) {
		vendeurDTO.setNomProprioCB(nomProprioCB);
	}

	public String toString() {
		return vendeurDTO.toString();
	}

	public VendeurDTO getVendeurDTO() {
		return vendeurDTO;
	}

	public void setVendeurDTO(VendeurDTO vendeurDTO) {
		this.vendeurDTO = vendeurDTO;
	}

	public String getStringDateExpirCB() {
		stringDateExpirCB = "";
		if(vendeurDTO.getDateExpirCB() != null){
			stringDateExpirCB = util.getDateToString(vendeurDTO.getDateExpirCB(), "dd/MM/yyyy");
		}
		return stringDateExpirCB;
	}

	public void setStringDateExpirCB(String stringDateExpirCB) {
		this.stringDateExpirCB = stringDateExpirCB;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		try {
			if ((vendeurDTO.getCodeSecuCB() == null)
					|| (vendeurDTO.getCodeSecuCB().length() != 3)
					|| (Integer.parseInt(vendeurDTO.getCodeSecuCB()) == 0)) {
				errors.add("codeSecuCB", new ActionError(
						"vendeur.inscription.erreur.codeSecuCB.badFormat"));

			}

		} catch (NumberFormatException e) {
			errors.add("codeSecuCB", new ActionError(
					"vendeur.inscription.erreur.codeSecuCB.badFormat"));
		}

		try {
			if ((vendeurDTO.getNumCB() == null)
					|| (vendeurDTO.getNumCB().length() != 16)
					|| (Long.parseLong(vendeurDTO.getNumCB()) == 0)) {
				errors.add("numCB", new ActionError(
						"vendeur.inscription.erreur.numCB.badFormat"));
			}
		} catch (NumberFormatException e) {
			errors.add("numCB", new ActionError(
					"vendeur.inscription.erreur.numCB.badFormat"));
		}

		if ((vendeurDTO.getNomProprioCB() == null)
				|| (vendeurDTO.getNomProprioCB().length() == 0)) {
			errors.add("nomProprioCB", new ActionError(
					"vendeur.inscription.erreur.nomProprioCB.obligatoire"));
		}

		if (stringDateExpirCB == null) {
			errors.add("DateExpirCB", new ActionError(
					"vendeur.inscription.erreur.dateExpirCB.obligatoire"));
		} else {
			try {
				Date tDate = util.getStringToDate(stringDateExpirCB, "dd/MM/yyyy");
				vendeurDTO.setDateExpirCB(tDate);
			} catch (ParseException e) {
				// dateFormat incorrect
				errors.add("stringDateExpirCB", new ActionError("vendeur.inscription.erreur.dateExpirCB.badFormat"));
			}
		}
		return errors;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		vendeurDTO.setCodeSecuCB(null);
		vendeurDTO.setNomProprioCB(null);
		vendeurDTO.setId(null);
		vendeurDTO.setNumCB(null);
		stringDateExpirCB = null;
	}

	public Collection getArticlesEnAttente() {
		return articlesEnAttente;
	}

	public void setArticlesEnAttente(Collection articlesEnAttente) {
		this.articlesEnAttente = articlesEnAttente;
	}

	public Collection getArticlesEnVente() {
		return articlesEnVente;
	}

	public void setArticlesEnVente(Collection articlesEnVente) {
		this.articlesEnVente = articlesEnVente;
	}

	public Collection getArticlesVendus() {
		return articlesVendus;
	}

	public void setArticlesVendus(Collection articlesVendus) {
		this.articlesVendus = articlesVendus;
	}

}
