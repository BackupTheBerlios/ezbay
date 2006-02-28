//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 02-28-2006
 * 
 * XDoclet definition:
 * @struts.form name="clientForm"
 */
public class ClientForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables
	private Collection articlesView = null;
	// --------------------------------------------------------- Methods

	public Collection getArticlesView() {
		return articlesView;
	}

	public void setArticlesView(Collection articlesView) {
		this.articlesView = articlesView;
	}
	
	public int getNbArticles(){
		return this.getArticlesView().size();
	}



}

