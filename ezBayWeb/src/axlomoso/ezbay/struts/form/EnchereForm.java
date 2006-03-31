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
import axlomoso.ezbay.struts.views.ActionEnchereView;
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
	private String stringMontantEnchereCourante = null;
	private Double montantEnchereCourante = null;
	private Double montantDerniereEnchere = new Double(0);
	private ActionEnchereView enchereView ; // = null;

	
	
	// --------------------------------------------------------- Methods

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		stringMontantEnchereCourante = "";
		montantEnchereCourante = null;
	}
	
	public String getStringMontantEnchereCourante() {
		return stringMontantEnchereCourante;
	}

	public void setStringMontantEnchereCourante(String montant) {
		stringMontantEnchereCourante = montant;
	}

	public Double getMontantEnchereCourante() {
		return montantEnchereCourante;
	}

	public void setMontantEnchereCourante(Double montant) {
		montantEnchereCourante = montant;
		System.out.println("setMontantEnchereCourante");
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
	
	public ActionEnchereView getEnchereView() {
		return enchereView;
	}

	public void setEnchereView(ActionEnchereView enchereView) {
		System.out.println("ActionEnchereView.setEnchereView()");
		this.enchereView = enchereView;
	}
	
	public Double getMontant() {
		Double tRes = new Double(0);
		if( enchereView != null )tRes = enchereView.getMontant();
		return tRes;
	}
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		boolean tSuccess = true;
		Double d = null;
		if ((this.getStringMontantEnchereCourante() == null)||(this.getStringMontantEnchereCourante().length() == 0)) {
			errors.add("stringMontant", new ActionError("articleEnchereEdit.erreurs.montantVide"));
			tSuccess = false;
		}
		else{
			try {				
					 d = new Double(Double.parseDouble(this.getStringMontantEnchereCourante()));
					 if( this.getMontant().doubleValue() > 0 ){
						 // l'article a déjà été enchéri
						 if( d.doubleValue() <= this.getMontant().doubleValue() ){
							 // montant de l'enchère courante <= montant de la dernière enchère
							 errors.add("prixVente", new ActionError("articleEnchereEdit.erreurs.montantInferieurADerniereEnchere"));
							 tSuccess = false;
						 }
					 }
					 else{
						 // l'article n'a jamais été enchéri
						 if( d.doubleValue() <= this.getPrixVente().doubleValue() ){
							 //montant de l'enchère courante <= prix vente de départ
							 errors.add("prixVente", new ActionError("articleEnchereEdit.erreurs.montantInferieurAPrixDepart"));
							 tSuccess = false;
						 }
					 }
				}				
			 catch (NumberFormatException e) {
				errors.add("prixVente", new ActionError("articleEnchereEdit.erreurs.montantInvalide"));
				tSuccess = false;
			}
		}
		if(tSuccess){
			this.setMontantEnchereCourante(d);
		}
		return errors;
	}

	public Double getMontantDerniereEnchere() {
		return montantDerniereEnchere;
	}

	public void setMontantDerniereEnchere(Double montantDerniereEnchere) {
		this.montantDerniereEnchere = montantDerniereEnchere;
	}
}

