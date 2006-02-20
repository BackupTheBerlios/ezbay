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
	private Collection listeCategories=null;
	// --------------------------------------------------------- Methods
	
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
	}

	public Collection getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(Collection listeCategories) {
		this.listeCategories = listeCategories;
	}
}

