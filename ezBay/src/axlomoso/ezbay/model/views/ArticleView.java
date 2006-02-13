package axlomoso.ezbay.model.views;

import java.util.Date;

import axlomoso.ezbay.model.interfaces.ArticleDTO;


public class ArticleView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5165714044887828546L;
	private ArticleDTO articleDTO = new ArticleDTO() ;

	public ArticleDTO getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}

	public boolean anneeFabricationHasBeenSet() {
		return articleDTO.anneeFabricationHasBeenSet();
	}

	public Object clone() throws CloneNotSupportedException {
		return articleDTO.clone();
	}

	public boolean dateLimiteHasBeenSet() {
		return articleDTO.dateLimiteHasBeenSet();
	}

	public boolean descriptionHasBeenSet() {
		return articleDTO.descriptionHasBeenSet();
	}

	public boolean equals(ArticleDTO that) {
		return articleDTO.equals(that);
	}

	public boolean equals(Object other) {
		return articleDTO.equals(other);
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

	public String getPrimaryKey() {
		return articleDTO.getPrimaryKey();
	}

	public Double getPrixVente() {
		return articleDTO.getPrixVente();
	}

	public int hashCode() {
		return articleDTO.hashCode();
	}

	public boolean idHasBeenSet() {
		return articleDTO.idHasBeenSet();
	}

	public boolean isIdentical(Object other) {
		return articleDTO.isIdentical(other);
	}

	public boolean libelleHasBeenSet() {
		return articleDTO.libelleHasBeenSet();
	}

	public boolean marqueHasBeenSet() {
		return articleDTO.marqueHasBeenSet();
	}

	public boolean modeleHasBeenSet() {
		return articleDTO.modeleHasBeenSet();
	}

	public boolean prixVenteHasBeenSet() {
		return articleDTO.prixVenteHasBeenSet();
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
