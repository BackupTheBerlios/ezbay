//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.VendeurDTO;

/** 
 * MyEclipse Struts
 * Creation date: 02-13-2006
 * 
 * XDoclet definition:
 * @struts.form name="myEzBayForm"
 */
public class MyEzBayForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables
	boolean isVendeur = false;
	MembreDTO membreDTO = null;
	VendeurDTO vendeurDTO = null;
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

		// TODO Auto-generated method stub
	}

	public boolean getIsVendeur() {
		return isVendeur;
	}

	public void setIsVendeur(boolean isVendeur) {
		this.isVendeur = isVendeur;
	}

	
	public VendeurDTO getVendeurDTO() {
		return vendeurDTO;
	}

	public void setVendeurDTO(VendeurDTO vendeurDTO) {
		this.vendeurDTO = vendeurDTO;
	}

	public MembreDTO getMembreDTO() {
		return membreDTO;
	}

	public void setMembreDTO(MembreDTO membreDTO) {
		this.membreDTO = membreDTO;
	}

}

