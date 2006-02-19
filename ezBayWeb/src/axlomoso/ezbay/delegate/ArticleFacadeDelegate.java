package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.VendeurDTO;

public class ArticleFacadeDelegate {

	private ArticleFacade articleFacade = null;

	public ArticleFacadeDelegate() throws Exception {
		try {
			Context jndiContext;
			jndiContext = new InitialContext();
			Object ref = jndiContext.lookup(ArticleFacadeHome.JNDI_NAME);
			ArticleFacadeHome facadeHome = (ArticleFacadeHome) PortableRemoteObject.narrow(ref, ArticleFacadeHome.class);
			this.articleFacade = facadeHome.create();
		} catch (Exception e) {
			throw new Exception("Cannot locate ArticleFacadeHome", e);
		}
	}

	public ArticleDTO getArticle(String articleId) throws Exception, RemoteException {
		return articleFacade.getArticle(articleId);
	}

	public Collection getArticles() throws RemoteException {
		return articleFacade.getArticles();
	}

	public Collection getArticlesByCategorie(String categorieId) throws RemoteException {
		return articleFacade.getArticlesByCategorie(categorieId);
	}

	public Collection getArticlesByLibelle(String libelle) throws RemoteException {
		return articleFacade.getArticlesByLibelle(libelle);
	}

	public Collection getArticlesByVendeur(String vendeurId) throws RemoteException {
		return articleFacade.getArticlesByVendeur(vendeurId);
	}

	public CategorieDTO getCategorieDTO(String articleId) throws Exception, RemoteException {
		return articleFacade.getCategorieDTO(articleId);
	}

	public Object getPrimaryKey() throws RemoteException {
		return articleFacade.getPrimaryKey();
	}

	public VendeurDTO getVendeurDTO(String articleId) throws Exception, RemoteException {
		return articleFacade.getVendeurDTO(articleId);
	}
	
}
