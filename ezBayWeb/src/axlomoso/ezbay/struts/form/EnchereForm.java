//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
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
	private ArticleView articleView = new ArticleView();
	private ActionEnchereDTO actionEnchereDTO = new ActionEnchereDTO();
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

	public Double getMontant() {
		return actionEnchereDTO.getMontant();
	}

	public void setMontant(Double montant) {
		actionEnchereDTO.setMontant(montant);
	}

	public String getStringMontant() {
		return stringMontant;
	}

	public void setStringMontant(String stringMontant) {
		this.stringMontant = stringMontant;
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
					 if( this.articleView.getDerniereEnchereDTO() != null ){
						 // l'article a déjà été enchéri
						 if( d.doubleValue() <= this.articleView.getDerniereEnchereDTO().getMontant().doubleValue() ){
							 // montant de l'enchère courante <= montant de la dernière enchère
							 errors.add("prixVente", new ActionError("articleEnchereEdit.erreurs.montantInferieurADerniereEnchere"));
						 }
					 }
					 else{
						 // l'article n'a jamais été enchéri
						 if( d.doubleValue() <= this.articleView.getPrixVente().doubleValue() ){
							 //montant de l'enchère courante <= prix vente de départ
							 errors.add("prixVente", new ActionError("articleEnchereEdit.erreurs.montantInferieurAPrixDepart"));
						 }
					 }
				}				
			 catch (NumberFormatException e) {
				errors.add("prixVente", new ActionError("articleEnchereEdit.erreurs.montantInvalide"));
				 e.printStackTrace();
			}
		}		
		return errors;
	}

	public ArticleView getArticleView() {
		return articleView;
	}

	public void setArticleView(ArticleView articleView) {
		this.articleView = articleView;
	}
	
}

