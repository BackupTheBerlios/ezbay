//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.form;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.struts.views.ActionEnchereView;
import axlomoso.ezbay.struts.views.ArticleView;
import axlomoso.ezbay.utils.Util;


/** 
 * MyEclipse Struts
 * Creation date: 02-12-2006
 * 
 * XDoclet definition:
 * @struts.form name="articleEditForm"
 */
public class ArticleForm extends ActionForm {
	
	private Util util = new Util();
	// --------------------------------------------------------- Instance Variables
	private ArticleView articleview = new ArticleView();
	private Collection categories = null;
	private String stringDateLimite = ""; 
	private String stringPrixVente="";
	// --------------------------------------------------------- Methods
	
	public String getStringPrixVente() {
		if(this.getPrixVente() != null){
			Double d = this.getPrixVente();
			stringPrixVente = String.valueOf(d.doubleValue());
		}
		return stringPrixVente;
	}

	public void setStringPrixVente(String stringPrixVente) {
		this.stringPrixVente = stringPrixVente;
	}

	public ArticleView getArticleView() {
		return articleview;
	}

	public void setArticleView(ArticleView articleview) {
		this.articleview = articleview;
	}

	public Integer getAnneeFabrication() {
		return articleview.getAnneeFabrication();
	}

	public String getStringDateLimite(){
		stringDateLimite = "";
		if(this.getDateLimite() != null){
			stringDateLimite = util.getDateToString(this.getDateLimite(), "dd/MM/yyyy - kk:mm:ss");
		}
		return stringDateLimite;
	}
	
	public Date getDateLimite() {
		return articleview.getDateLimite();
	}

	public String getDescription() {
		return articleview.getDescription();
	}

	public String getId() {
		return articleview.getId();
	}

	public String getLibelle() {
		return articleview.getLibelle();
	}

	public String getMarque() {
		return articleview.getMarque();
	}

	public String getModele() {
		return articleview.getModele();
	}

	public Double getPrixVente() {
		return articleview.getPrixVente();
	}

	public void setAnneeFabrication(Integer anneeFabrication) {
		articleview.setAnneeFabrication(anneeFabrication);
	}

	public void setStringDateLimite(String date){
		stringDateLimite = date;
	}
	
	public void setDateLimite(Date dateLimite) {
		articleview.setDateLimite(dateLimite);
	}

	public void setDescription(String description) {
		articleview.setDescription(description);
	}

	public void setId(String id) {
		articleview.setId(id);
	}

	public void setLibelle(String libelle) {
		articleview.setLibelle(libelle);
	}

	public void setMarque(String marque) {
		articleview.setMarque(marque);
	}

	public void setModele(String modele) {
		articleview.setModele(modele);
	}

	public void setPrixVente(Double prixVente) {
		articleview.setPrixVente(prixVente);
	}

	public String toString() {
		return articleview.toString();
	}
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if ((this.getLibelle() == null) || (this.getLibelle().length() == 0)) {
			errors.add("libelle", new ActionError("articleEdit.erreurs.libelleVide"));
		}
		if ((this.getMarque() == null) || (this.getMarque().length() == 0)) {
			errors.add("marque", new ActionError("articleEdit.erreurs.marqueVide"));
		}
		if ((this.getModele() == null) || (this.getModele().length() == 0)) {
			errors.add("modele", new ActionError("articleEdit.erreurs.modeleVide"));
		}
		if ((stringPrixVente == null)||(stringPrixVente.length()==0)) {
			errors.add("prixVente", new ActionError("articleEdit.erreurs.prixVenteVide"));
		}
		else{
			try {				
					 Double d = new Double(Double.parseDouble(stringPrixVente));
					 this.setPrixVente(d);
				}				
			 catch (NumberFormatException e) {
				errors.add("prixVente", new ActionError("articleEdit.erreurs.prixVenteNonValide"));
			}
		}		
		
		if ((stringDateLimite == null)||(stringDateLimite.length() == 0)) {
			errors.add("dateLimite", new ActionError("articleEdit.erreurs.dateLimiteVide"));
		}else{
			try {
				Date tDate = util.getStringToDate(stringDateLimite, "dd/MM/yyyy - kk:mm:ss");
				//ce code est commenté pour pouvoir tester le timer le jours de la soutenance
				/*long dateMiniMilli = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
				long dateRecupMilli = tDate.getTime();
				if( dateRecupMilli <= dateMiniMilli){
					errors.add("dateLimite", new ActionError("articleEdit.erreurs.dateLimiteTropPetite"));
				}
				*/
				this.setDateLimite(tDate);
			} catch (ParseException e) {
				// dateFormat incorrect
				errors.add("dateLimite", new ActionError("articleEdit.erreurs.dateLimiteNonValide"));
			}
		}		
		
		if (  (this.getAnneeFabrication() != null) ){
			int annee = this.getAnneeFabrication().intValue();
			Calendar tCal = Calendar.getInstance();
			int anneeMax = tCal.get(Calendar.YEAR);
			if( (annee <= 0) || ( annee > anneeMax) )			
				errors.add("anneeFabrication", new ActionError("articleEdit.erreurs.anneeFabricationNonValide"));
		}
		return errors;
	}

	public Collection getCategories() {
		return categories;
	}

	public void setCategories(Collection categories) {
		this.categories = categories;
	}

	public ArticleView getArticleview() {
		return articleview;
	}

	public void setArticleview(ArticleView articleview) {
		this.articleview = articleview;
	}

}

