package axlomoso.ezbay.struts.views;

import java.util.Date;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.utils.Util;

public class ArticleView {
	private ArticleDTO articleDTO = new ArticleDTO();
	private CategorieDTO categorieDTO = new CategorieDTO();
	private Util util = new Util();
	
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

	public void setId(String id) {
		articleDTO.setId(id);
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
		return util.getDateToString(articleDTO.getDateLimite(), "dd/MM/yyyy - kk:mm:ss");
	}

	public ArticleDTO getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}

	public CategorieDTO getCategorieDTO() {
		return categorieDTO;
	}

	public void setCategorieDTO(CategorieDTO categorieDTO) {
		this.categorieDTO = categorieDTO;
	}

	public Boolean getEnVente() {
		return articleDTO.getEnVente();
	}

	public String getAcheteurId() {
		return articleDTO.getAcheteurId();
	}

	public String getAcheteurPseudo() {
		return articleDTO.getAcheteurPseudo();
	}

	public Date getDerniereEnchereDate() {
		return articleDTO.getDerniereEnchereDate();
	}

	public Double getDerniereEnchereMontant() {
		return articleDTO.getDerniereEnchereMontant();
	}

	public String getEncherisseurPseudo() {
		return articleDTO.getEncherisseurPseudo();
	}

	public Integer getNbEncheres() {
		return articleDTO.getNbEncheres();
	}

	public Date getTransactionDate() {
		return articleDTO.getTransactionDate();
	}

	public Double getTransactionMontant() {
		return articleDTO.getTransactionMontant();
	}

	public String getVendeurId() {
		return articleDTO.getVendeurId();
	}

	public String getVendeurPseudo() {
		return articleDTO.getVendeurPseudo();
	}

	public void setAcheteurId(String acheteurId) {
		articleDTO.setAcheteurId(acheteurId);
	}

	public void setAcheteurPseudo(String acheteurPseudo) {
		articleDTO.setAcheteurPseudo(acheteurPseudo);
	}

	public void setDerniereEnchereDate(Date derniereEnchereDate) {
		articleDTO.setDerniereEnchereDate(derniereEnchereDate);
	}

	public void setDerniereEnchereMontant(Double derniereEnchereMontant) {
		articleDTO.setDerniereEnchereMontant(derniereEnchereMontant);
	}

	public void setEncherisseurPseudo(String encherisseurPseudo) {
		articleDTO.setEncherisseurPseudo(encherisseurPseudo);
	}

	public void setEnVente(Boolean enVente) {
		articleDTO.setEnVente(enVente);
	}

	public void setNbEncheres(Integer nbEncheres) {
		articleDTO.setNbEncheres(nbEncheres);
	}

	public void setTransactionDate(Date transactionDate) {
		articleDTO.setTransactionDate(transactionDate);
	}

	public void setTransactionMontant(Double transactionMontant) {
		articleDTO.setTransactionMontant(transactionMontant);
	}

	public void setVendeurId(String vendeurId) {
		articleDTO.setVendeurId(vendeurId);
	}

	public void setVendeurPseudo(String vendeurPseudo) {
		articleDTO.setVendeurPseudo(vendeurPseudo);
	}

	public String getEncherisseurClientId() {
		return articleDTO.getEncherisseurClientId();
	}

	public String getEncherisseurMembreId() {
		return articleDTO.getEncherisseurMembreId();
	}

	public void setEncherisseurClientId(String encherisseurClientId) {
		articleDTO.setEncherisseurClientId(encherisseurClientId);
	}

	public void setEncherisseurMembreId(String encherisseurMembreId) {
		articleDTO.setEncherisseurMembreId(encherisseurMembreId);
	}

	public String getVendeurMembreId() {
		return articleDTO.getVendeurMembreId();
	}

	public void setVendeurMembreId(String vendeurMembreId) {
		articleDTO.setVendeurMembreId(vendeurMembreId);
	}
	
	public String getDerniereEnchereFormattedDate(){
		return util.getDateToString(this.getDerniereEnchereDate(), "dd/MM/yyyy - kk:mm:ss");
	}

}
