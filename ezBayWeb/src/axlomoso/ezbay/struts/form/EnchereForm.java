//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.struts.views.ArticleView;

/** 
 * MyEclipse Struts
 * Creation date: 02-23-2006
 * 
 * XDoclet definition:
 * @struts.form name="enchereForm"
 */
public class EnchereForm extends ActionForm {
	// --------------------------------------------------------- Instance Variables
	private ArticleDTO articleDTO = new ArticleDTO();
	private ActionEnchereDTO lastActionEnchereDTO = new ActionEnchereDTO();
	private ActionEnchereDTO currentActionEnchereDTO = new ActionEnchereDTO();
	private String stringMontant = "";
	// --------------------------------------------------------- Methods


	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		stringMontant = "";
	}

	public Double getMontantDerniereEnchere() {
		return lastActionEnchereDTO.getMontant();
	}

	public void setMontantDerniereEnchere(Double montant) {
		lastActionEnchereDTO.setMontant(montant);
	}
	
	public Double getMontant() {
		return currentActionEnchereDTO.getMontant();
	}

	public void setMontant(Double montant) {
		currentActionEnchereDTO.setMontant(montant);
	}

	public String getStringMontant() {
		return stringMontant;
	}

	public void setStringMontant(String stringMontant) {
		this.stringMontant = stringMontant;
	}

	public ArticleDTO getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}

	public String getLibelle() {
		return articleDTO.getLibelle();
	}

	public Double getPrixVente() {
		return articleDTO.getPrixVente();
	}

	public void setLibelle(String libelle) {
		articleDTO.setLibelle(libelle);
	}

	public void setPrixVente(Double prixVente) {
		articleDTO.setPrixVente(prixVente);
	}

	public String getId() {
		return articleDTO.getId();
	}

	public void setId(String id) {
		articleDTO.setId(id);
	}
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		System.out.println("EnchereForm.validate()");
		ActionErrors errors = new ActionErrors();
		if ((stringMontant == null)||(stringMontant.length() == 0)) {
			errors.add("stringMontant", new ActionError("articleEnchereEdit.erreurs.montantVide"));
		}
		else{
			try {				
					 Double d = new Double(Double.parseDouble(stringMontant));
					 this.setMontant(d);
					 if( this.getMontantDerniereEnchere().doubleValue() > 0 ){
						 // l'article a déjà été enchéri
						 if( d.doubleValue() <= this.getMontantDerniereEnchere().doubleValue() ){
							 // montant de l'enchère courante <= montant de la dernière enchère
							 errors.add("prixVente", new ActionError("articleEnchereEdit.erreurs.montantInferieurADerniereEnchere"));
						 }
					 }
					 else{
						 // l'article n'a jamais été enchéri
						 if( d.doubleValue() <= this.getPrixVente().doubleValue() ){
							 //montant de l'enchère courante <= prix vente de départ
							 errors.add("prixVente", new ActionError("articleEnchereEdit.erreurs.montantInferieurAPrixDepart"));
						 }
					 }
					 // A deplacer dans EnchereSaveAction : fin
				}				
			 catch (NumberFormatException e) {
				errors.add("prixVente", new ActionError("articleEnchereEdit.erreurs.montantInvalide"));
			}
		}		
		return errors;
	}
	
}

