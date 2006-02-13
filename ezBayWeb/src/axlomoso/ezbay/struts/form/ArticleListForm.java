//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.form;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 02-12-2006
 * 
 * XDoclet definition:
 * @struts.form name="articleListForm"
 */
public class ArticleListForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/**
	 * 
	 */
	private static final long serialVersionUID = 968688817928699885L;
	private Collection articleViews = null;
	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		articleViews = new ArrayList();
	}
	public Collection getArticleViews() {
		return articleViews;
	}
	public void setArticleViews(Collection articleViews) {
		this.articleViews = articleViews;
	}

}

