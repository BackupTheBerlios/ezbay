package axlomoso.ezbay.struts.views;

import java.util.Date;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.utils.Util;

public class ArticleView {
	private ArticleDTO articleDTO = new ArticleDTO();
	private VendeurDTO vendeurDTO = new VendeurDTO();
	private MembreDTO vendeurMembreDTO = new MembreDTO();
	private CategorieDTO categorieDTO = null;
	private ActionEnchereView derniereEnchereView = null;
	private ActionTransactionView transactionView = null;
	private Integer nbEncheres = null;
	
	
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
		return util.getDateToString(articleDTO.getDateLimite());
	}

	public ArticleDTO getArticleDTO() {
		return articleDTO;
	}

	public void setArticleDTO(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}

	public ActionEnchereView getDerniereEnchereView() {
		return derniereEnchereView;
	}

	public void setDerniereEnchereView(ActionEnchereView derniereEnchereView) {
		this.derniereEnchereView = derniereEnchereView;
	}

	public VendeurDTO getVendeurDTO() {
		return vendeurDTO;
	}

	public void setVendeurDTO(VendeurDTO vendeurDTO) {
		this.vendeurDTO = vendeurDTO;
	}

	public Integer getNbEncheres() {
		return nbEncheres;
	}

	public void setNbEncheres(Integer nbEncheres) {
		this.nbEncheres = nbEncheres;
	}

	public ActionTransactionView getTransactionView() {
		return transactionView;
	}

	public void setTransactionView(
			ActionTransactionView transactionView) {
		this.transactionView = transactionView;
	}

	public CategorieDTO getCategorieDTO() {
		return categorieDTO;
	}

	public void setCategorieDTO(CategorieDTO categorieDTO) {
		this.categorieDTO = categorieDTO;
	}

	public MembreDTO getVendeurMembreDTO() {
		return vendeurMembreDTO;
	}

	public void setVendeurMembreDTO(MembreDTO vendeurMembreDTO) {
		this.vendeurMembreDTO = vendeurMembreDTO;
	}

}
