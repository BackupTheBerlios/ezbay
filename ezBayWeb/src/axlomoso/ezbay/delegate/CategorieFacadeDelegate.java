package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;


import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieFacade;
import axlomoso.ezbay.model.interfaces.CategorieFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;


public class CategorieFacadeDelegate {

	private static CategorieFacadeDelegate instance = null;
	private CategorieFacade categorieFacade = null;

	public static CategorieFacadeDelegate getInstance(){
		if( instance == null ) instance = new CategorieFacadeDelegate();
		return instance;
	}
	
	private CategorieFacadeDelegate(){
		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			CategorieFacadeHome home = (CategorieFacadeHome) locator.getRemoteHome(CategorieFacadeHome.JNDI_NAME, CategorieFacadeHome.class);
			this.categorieFacade = home.create();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}

	}

	public Collection getArticlesDTO(String categorieId) throws Exception, RemoteException {
		return categorieFacade.getArticlesDTO(categorieId);
	}

	public CategorieDTO getCategorie(String categorieId) throws Exception, RemoteException {
		return categorieFacade.getCategorie(categorieId);
	}

	public Collection getCategories() throws RemoteException {
		return categorieFacade.getCategories();
	}

	public void removeCategorie(CategorieDTO categorieDTO) throws Exception, RemoteException {
		categorieFacade.removeCategorie(categorieDTO);
	}

	

	
	

	
}
