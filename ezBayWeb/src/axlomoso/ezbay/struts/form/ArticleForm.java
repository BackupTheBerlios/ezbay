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
import org.apache.struts.validator.ValidatorForm;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
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
	private ArticleDTO articleDTO = new ArticleDTO();
	private CategorieDTO categorieDTO = new CategorieDTO();
	private Collection categories = null;
	private String stringDateLimite = ""; 
	private String stringPrixVente="";
	private String vendeurId = "";
	private String membreId = "";
	private String membrePseudo = "";
	// --------------------------------------------------------- Methods

	
	public String getStringPrixVente() {
		if(articleDTO.getPrixVente() != null){
			Double d = articleDTO.getPrixVente();
			stringPrixVente = String.valueOf(d.doubleValue());
		}
		return stringPrixVente;
	}

	public void setStringPrixVente(String stringPrixVente) {
		this.stringPrixVente = stringPrixVente;
	}

	public ArticleDTO getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}

	public boolean anneeFabricationHasBeenSet() {
		return articleDTO.anneeFabricationHasBeenSet();
	}

	public Integer getAnneeFabrication() {
		return articleDTO.getAnneeFabrication();
	}

	public String getStringDateLimite(){
		stringDateLimite = "";
		if(articleDTO.getDateLimite() != null){
			stringDateLimite = util.getDateToString(articleDTO.getDateLimite());
		}
		return stringDateLimite;
	}
	
	public Date getDateLimite() {
		return articleDTO.getDateLimite();
	}

	public String getDescription() {
		return articleDTO.getDescription();
	}

	public String getId() {
		return articleDTO.getId();
	}

	public String getLibelle() {
		return articleDTO.getLibelle();
	}

	public String getMarque() {
		return articleDTO.getMarque();
	}

	public String getModele() {
		return articleDTO.getModele();
	}

	public String getPrimaryKey() {
		return articleDTO.getPrimaryKey();
	}

	public Double getPrixVente() {
		return articleDTO.getPrixVente();
	}

	public void setAnneeFabrication(Integer anneeFabrication) {
		articleDTO.setAnneeFabrication(anneeFabrication);
	}

	public void setStringDateLimite(String date){
		stringDateLimite = date;
	}
	
	public void setDateLimite(Date dateLimite) {
		articleDTO.setDateLimite(dateLimite);
	}

	public void setDescription(String description) {
		articleDTO.setDescription(description);
	}

	public void setId(String id) {
		articleDTO.setId(id);
	}

	public void setLibelle(String libelle) {
		articleDTO.setLibelle(libelle);
	}

	public void setMarque(String marque) {
		articleDTO.setMarque(marque);
	}

	public void setModele(String modele) {
		articleDTO.setModele(modele);
	}

	public void setPrimaryKey(String primaryKey) {
		articleDTO.setPrimaryKey(primaryKey);
	}

	public void setPrixVente(Double prixVente) {
		articleDTO.setPrixVente(prixVente);
	}

	public String toString() {
		return articleDTO.toString();
	}
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if ((articleDTO.getLibelle() == null) || (articleDTO.getLibelle().length() == 0)) {
			errors.add("libelle", new ActionError("articleEdit.erreurs.libelleVide"));
		}
		if ((articleDTO.getMarque() == null) || (articleDTO.getMarque().length() == 0)) {
			errors.add("marque", new ActionError("articleEdit.erreurs.marqueVide"));
		}
		if ((articleDTO.getModele() == null) || (articleDTO.getModele().length() == 0)) {
			errors.add("modele", new ActionError("articleEdit.erreurs.modeleVide"));
		}
		if ((stringPrixVente == null)||(stringPrixVente.length()==0)) {
			errors.add("prixVente", new ActionError("articleEdit.erreurs.prixVenteVide"));
		}
		else{
			try {				
					 Double d = new Double(Double.parseDouble(stringPrixVente));
					 articleDTO.setPrixVente(d);
				}				
			 catch (Exception e) {
				errors.add("prixVente", new ActionError("articleEdit.erreurs.prixVenteNonValide"));
			}
		}		
		
		if ((stringDateLimite == null)||(stringDateLimite.length() == 0)) {
			errors.add("dateLimite", new ActionError("articleEdit.erreurs.dateLimiteVide"));
		}else{
			try {
				Date tDate = util.getStringToDate(stringDateLimite);
				articleDTO.setDateLimite(tDate);
			} catch (ParseException e) {
				// dateFormat incorrect
				errors.add("dateLimite", new ActionError("articleEdit.erreurs.dateLimiteNonValide"));
			}
		}		
		
		if (  (articleDTO.getAnneeFabrication() != null) ){
			int annee = articleDTO.getAnneeFabrication().intValue();
			Calendar tCal = Calendar.getInstance();
			int anneeMax = tCal.get(Calendar.YEAR);
			if( (annee <= 0) || ( annee > anneeMax) )			
				errors.add("anneeFabrication", new ActionError("articleEdit.erreurs.anneeFabricationNonValide"));
		}
		return errors;
	}

	public void setVendeurId(String vendeurId) {
		this.vendeurId = vendeurId;
	}
	public String getVendeurId() {
		return vendeurId;
	}


	public CategorieDTO getCategorieDTO() {
		return categorieDTO;
	}

	public void setCategorieDTO(CategorieDTO categorieDTO) {
		this.categorieDTO = categorieDTO;
	}

	public Collection getCategories() {
		return categories;
	}

	public void setCategories(Collection categories) {
		this.categories = categories;
	}

	public String getMembreId() {
		return membreId;
	}

	public String getMembrePseudo() {
		return membrePseudo;
	}
}

