//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.model.interfaces.CategorieDTO;

/** 
 * MyEclipse Struts
 * Creation date: 02-19-2006
 * 
 * XDoclet definition:
 * @struts.form name="categorieForm"
 */
public class CategorieForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables
		
	// --------------------------------------------------------- Methods
	private CategorieDTO categorieDTO=new CategorieDTO();
	private Collection listeCategories=null;
	private Collection listeArticles=null;
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

	public CategorieDTO getCategorieDTO() {
		return categorieDTO;
	}

	public void setCategorieDTO(CategorieDTO categorieDTO) {
		this.categorieDTO = categorieDTO;
	}

	public String getLibelle() {
		return categorieDTO.getLibelle();
	}

	public void setLibelle(String libelle) {
		categorieDTO.setLibelle(libelle);
	}

	public Collection getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(Collection listeCategories) {
		this.listeCategories = listeCategories;
	}

	public Collection getListeArticles() {
		return listeArticles;
	}

	public void setListeArticles(Collection listeArticles) {
		this.listeArticles = listeArticles;
	}

}

