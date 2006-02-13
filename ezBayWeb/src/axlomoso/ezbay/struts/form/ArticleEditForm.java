//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.views.ArticleView;


/** 
 * MyEclipse Struts
 * Creation date: 02-12-2006
 * 
 * XDoclet definition:
 * @struts.form name="articleEditForm"
 */
public class ArticleEditForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables
	private ArticleDTO articleDTO = new ArticleDTO();
	// --------------------------------------------------------- Methods

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
		return articleDTO.getDateLimite().toString();
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
		articleDTO.setDateLimite(new Date());
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



}

