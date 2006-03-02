//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import axlomoso.ezbay.struts.views.ArticleView;

/** 
 * MyEclipse Struts
 * Creation date: 03-01-2006
 * 
 * XDoclet definition:
 * @struts.form name="avisForm"
 */
public class AvisForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables
	private String avis = null;
	private String articleId = null;
	private ArticleView articleView = new ArticleView(); 
	// --------------------------------------------------------- Methods


	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getAvis() {
		return avis;
	}

	public void setAvis(String avis) {
		this.avis = avis;
	}

	public ArticleView getArticleView() {
		return articleView;
	}

	public void setArticleView(ArticleView articleView) {
		this.articleView = articleView;
	}

}

