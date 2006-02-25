package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.exceptions.PseudoDejaUtiliseException;
import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.CategorieFacadeHome;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.ClientFacade;
import axlomoso.ezbay.model.interfaces.ClientFacadeHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreFacade;
import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;

public class ClientFacadeDelegate {

	private ClientFacade clientFacade = null;
	private static ClientFacadeDelegate instance = null;

	public static ClientFacadeDelegate getInstance(){
		if( instance == null ) instance = new ClientFacadeDelegate();
		return instance;
	}
	
	private ClientFacadeDelegate(){
		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			ClientFacadeHome home = (ClientFacadeHome) locator.getRemoteHome(ClientFacadeHome.JNDI_NAME, ClientFacadeHome.class);
			this.clientFacade = home.create();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}
	}
	
	public MembreDTO getMembre(String clientId) throws RemoteException, Exception{
		return clientFacade.getMembre(clientId);
	}
	
}
