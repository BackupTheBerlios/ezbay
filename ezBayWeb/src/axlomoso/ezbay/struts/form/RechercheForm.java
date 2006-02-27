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
import axlomoso.ezbay.utils.Util;

/** 
 * MyEclipse Struts
 * Creation date: 02-19-2006
 * 
 * XDoclet definition:
 * @struts.form name="rechercheForm"
 */
public class RechercheForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables
	
	private Util util = new Util();
	// --------------------------------------------------------- Methods
	private ArticleDTO articleDTO=new ArticleDTO();
	private CategorieDTO categorieDTO=new CategorieDTO();
	private String stringPrixVenteMin=null;
	private String stringPrixVenteMax=null;
	private Double prixVenteMin=null;
	private Double prixVenteMax=null;
	private String stringDateLimite=null;
	private String stringAnneeFabrication=null;
	private Collection articlesView = null;
	private int nbArticles;


	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// TODO Auto-generated method stub
	}

	

	public ArticleDTO getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}

	public Integer getAnneeFabrication() {
		return articleDTO.getAnneeFabrication();
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

	public void setPrixVente(Double prixVente) {
		articleDTO.setPrixVente(prixVente);
	}

	public CategorieDTO getCategorieDTO() {
		return categorieDTO;
	}

	public void setCategorieDTO(CategorieDTO categorieDTO) {
		this.categorieDTO = categorieDTO;
	}

	public Double getPrixVente() {
		return articleDTO.getPrixVente();
	}

	public void setAnneeFabrication(Integer anneeFabrication) {
		articleDTO.setAnneeFabrication(anneeFabrication);
	}

	
	public String getStringDateLimite() {
		return stringDateLimite;
	}

	public void setStringDateLimite(String stringDateLimite) {
		this.stringDateLimite = stringDateLimite;
	}

	public Collection getArticlesView() {
		return articlesView;
	}

	public void setArticlesView(Collection articlesDTO) {
		this.articlesView = articlesDTO;
	}

	public String getStringAnneeFabrication() {
		return stringAnneeFabrication;
	}

	public void setStringAnneeFabrication(String stringAnneeFabrication) {
		this.stringAnneeFabrication = stringAnneeFabrication;
	}
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (articleDTO.getLibelle() == null) {
			this.setLibelle("");
		}
		if (articleDTO.getMarque() == null) {
			this.setMarque("");
		}
		if (articleDTO.getModele() == null) {
			this.setModele("");
		}
		if ((stringPrixVenteMin == null)||(stringPrixVenteMin.length()==0)) {
			this.setPrixVenteMin(new Double(Double.MIN_VALUE));
		}
		else{
			try {				
					 Double d = new Double(Double.parseDouble(stringPrixVenteMin));
					 this.setPrixVenteMin(d);
				}				
			 catch (Exception e) {
				errors.add("prixVente", new ActionError("articleEdit.erreurs.prixVenteNonValide"));
			}
		}		
		if ((stringPrixVenteMax == null)||(stringPrixVenteMax.length()==0)) {
			this.setPrixVenteMax(new Double(Double.MAX_VALUE));
		}
		else{
			try {				
					 Double d = new Double(Double.parseDouble(stringPrixVenteMax));
					 this.setPrixVenteMax(d);
				}				
			 catch (Exception e) {
				errors.add("prixVente", new ActionError("articleEdit.erreurs.prixVenteNonValide"));
			}
		}		
		if ((stringDateLimite == null)||(stringDateLimite.length() == 0)) {
			Date date=new Date();			
			date.setTime(0);
			this.setDateLimite(date);
		}else{
			try {
				Date tDate = util.getStringToDate(stringDateLimite, "dd/MM/yyyy");
				articleDTO.setDateLimite(tDate);
			} catch (ParseException e) {
				// dateFormat incorrect
				errors.add("dateLimite", new ActionError("articleEdit.erreurs.dateLimiteNonValide"));
			}
		}		
		
		if (  (stringAnneeFabrication == null) ||(stringAnneeFabrication.length() == 0)){
			this.setAnneeFabrication(new Integer(0));
		}
			else{
				try{
					int annee = Integer.parseInt(stringAnneeFabrication);
					Calendar tCal = Calendar.getInstance();
					int anneeMax = tCal.get(Calendar.YEAR);
					if( (annee <= 0) || ( annee > anneeMax) )			
						errors.add("anneeFabrication", new ActionError("articleEdit.erreurs.anneeFabricationNonValide"));
					else
						this.setAnneeFabrication(new Integer(annee));
				}catch(Exception e){
					errors.add("anneeFabrication", new ActionError("articleEdit.erreurs.anneeFabricationNonValide"));
				}
		}
		return errors;
	}



	public Double getPrixVenteMax() {
		return prixVenteMax;
	}



	public void setPrixVenteMax(Double prixVenteMax) {
		this.prixVenteMax = prixVenteMax;
	}



	public Double getPrixVenteMin() {
		return prixVenteMin;
	}



	public void setPrixVenteMin(Double prixVenteMin) {
		this.prixVenteMin = prixVenteMin;
	}



	public String getStringPrixVenteMax() {
		return stringPrixVenteMax;
	}



	public void setStringPrixVenteMax(String stringPrixVenteMax) {
		this.stringPrixVenteMax = stringPrixVenteMax;
	}



	public String getStringPrixVenteMin() {
		return stringPrixVenteMin;
	}



	public void setStringPrixVenteMin(String stringPrixVenteMin) {
		this.stringPrixVenteMin = stringPrixVenteMin;
	}



	public void setNbArticles(int nbArticles) {
		this.nbArticles = nbArticles;
	}



	public int getNbArticles() {
		return nbArticles;
	}



	

}

