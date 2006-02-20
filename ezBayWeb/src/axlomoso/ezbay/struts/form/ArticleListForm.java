//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.form;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.model.interfaces.CategorieDTO;

/** 
 * MyEclipse Struts
 * Creation date: 02-12-2006
 * 
 * XDoclet definition:
 * @struts.form name="articleListForm"
 */
public class ArticleListForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables
	private Collection articlesDTO = null;
	private CategorieDTO categorieDTO = null; 
	// --------------------------------------------------------- Methods


	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		articlesDTO = new ArrayList();
	}
	public Collection getArticlesDTO() {
		return articlesDTO;
	}
	public void setArticlesDTO(Collection articlesDTO) {
		this.articlesDTO = articlesDTO;
	}
	public CategorieDTO getCategorieDTO() {
		return categorieDTO;
	}
	public void setCategorieDTO(CategorieDTO categorieDTO) {
		this.categorieDTO = categorieDTO;
	}

}

