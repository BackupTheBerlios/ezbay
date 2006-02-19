package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;


import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieFacade;
import axlomoso.ezbay.model.interfaces.CategorieFacadeHome;


public class CategorieFacadeDelegate {

	private CategorieFacade categorieFacade = null;

	public CategorieFacadeDelegate() throws Exception {
		try {
			Context jndiContext;
			jndiContext = new InitialContext();
			Object ref = jndiContext.lookup(CategorieFacadeHome.JNDI_NAME);
			CategorieFacadeHome facadeHome = (CategorieFacadeHome) PortableRemoteObject.narrow(ref, CategorieFacadeHome.class);
			this.categorieFacade = facadeHome.create();
		} catch (Exception e) {
			throw new Exception("Cannot locate CategorieFacadeHome", e);
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
