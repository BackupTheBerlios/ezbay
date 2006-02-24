package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.exceptions.PseudoDejaUtiliseException;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.CategorieFacadeHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreFacade;
import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;

public class MembreFacadeDelegate {

	private MembreFacade membreFacade = null;
	private static MembreFacadeDelegate instance = null;

	public static MembreFacadeDelegate getInstance(){
		if( instance == null ) instance = new MembreFacadeDelegate();
		return instance;
	}
	
	private MembreFacadeDelegate(){
		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			MembreFacadeHome home = (MembreFacadeHome) locator.getRemoteHome(MembreFacadeHome.JNDI_NAME, MembreFacadeHome.class);
			this.membreFacade = home.create();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}

	}

	public VendeurDTO getVendeurDTO(String membreId) throws RemoteException {
		return membreFacade.getVendeurDTO(membreId);
	}
	
	public VendeurDTO saveVendeur(MembreDTO membreDTO, VendeurDTO vendeurDTO) throws Exception, RemoteException {
		return membreFacade.saveVendeur(membreDTO, vendeurDTO);
	}

	public MembreDTO saveMembre(MembreDTO membreDTO) throws Exception, RemoteException, PseudoDejaUtiliseException {
		return membreFacade.saveMembre(membreDTO);
	}
	
	public MembreDTO getMembre(String membreId) throws Exception, RemoteException {
		return membreFacade.getMembre(membreId);
	}

	public MembreDTO getMembre(String pseudo, String password) throws FinderException, RemoteException {
		return membreFacade.getMembre(pseudo, password);
	}

	public MembreFacade getMembreFacade() {
		return membreFacade;
	}

	public void setMembreFacade(MembreFacade membreFacade) {
		this.membreFacade = membreFacade;
	}
	
}
