package axlomoso.ezbay.struts.views;

import java.util.Date;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.utils.Util;

public class ArticleView {

	private ArticleDTO articleDTO = new ArticleDTO();
	
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

	public Double getPrixVente() {
		return articleDTO.getPrixVente();
	}

	public void setAnneeFabrication(Integer anneeFabrication) {
		articleDTO.setAnneeFabrication(anneeFabrication);
	}

	public void setDateLimite(Date dateLimite) {
		articleDTO.setDateLimite(dateLimite);
	}

	public void setDescription(String description) {
		articleDTO.setDescription(description);
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

	public String getFormattedDateLimite() {
		Util util = new Util();
		return util.getDateToString(articleDTO.getDateLimite());
	}

}