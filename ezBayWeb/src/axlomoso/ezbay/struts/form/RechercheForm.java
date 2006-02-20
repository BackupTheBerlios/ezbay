//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.form;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.model.interfaces.ArticleDTO;

/** 
 * MyEclipse Struts
 * Creation date: 02-19-2006
 * 
 * XDoclet definition:
 * @struts.form name="rechercheForm"
 */
public class RechercheForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables
	
	
	// --------------------------------------------------------- Methods
	private ArticleDTO articleDTO=new ArticleDTO();
	private Collection result=null;

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// TODO Auto-generated method stub
	}

	public Collection getResult() {
		return result;
	}

	public void setResult(Collection result) {
		this.result = result;
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

}

