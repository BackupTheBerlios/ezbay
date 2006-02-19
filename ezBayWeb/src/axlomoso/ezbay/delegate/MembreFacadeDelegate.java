package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;

import javax.ejb.FinderException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.exceptions.PseudoDejaUtiliseException;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreFacade;
import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;

public class MembreFacadeDelegate {

	private MembreFacade membreFacade = null;

	public MembreFacadeDelegate() throws Exception {
		try {
			Context jndiContext;
			jndiContext = new InitialContext();
			Object ref = jndiContext.lookup(MembreFacadeHome.JNDI_NAME);
			MembreFacadeHome facadeHome = (MembreFacadeHome) PortableRemoteObject.narrow(ref, MembreFacadeHome.class);
			this.membreFacade = facadeHome.create();
		} catch (Exception e) {
			throw new Exception("Cannot locate MembreFacadeHome", e);
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
	
	public void removeMembre(MembreDTO membreDTO) throws Exception, RemoteException {
		membreFacade.removeMembre(membreDTO);
	}
	
}
