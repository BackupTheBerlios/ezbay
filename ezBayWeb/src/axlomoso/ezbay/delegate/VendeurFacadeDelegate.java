package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacade;
import axlomoso.ezbay.model.interfaces.VendeurFacadeHome;

public class VendeurFacadeDelegate {
	private VendeurFacade vendeurFacade = null;

	public VendeurFacadeDelegate() throws Exception {
		try {
			Context jndiContext;
			jndiContext = new InitialContext();
			Object ref = jndiContext.lookup(VendeurFacadeHome.JNDI_NAME);
			VendeurFacadeHome facadeHome = (VendeurFacadeHome) PortableRemoteObject.narrow(ref, VendeurFacadeHome.class);
			this.vendeurFacade = facadeHome.create();
		} catch (Exception e) {
			throw new Exception("Cannot locate MembreFacadeHome", e);
		}
	}
	
	public VendeurFacade getVendeurFacade() {
		return vendeurFacade;
	}

	public void setVendeurFacade(VendeurFacade vendeurFacade) {
		this.vendeurFacade = vendeurFacade;
	}

	public Collection getArticles(String vendeurId) throws RemoteException {
		return vendeurFacade.getArticles(vendeurId);
	}

	public VendeurDTO getVendeur(String vendeurId) throws Exception, RemoteException {
		return vendeurFacade.getVendeur(vendeurId);
	}

	public Collection getVendeurs() throws RemoteException {
		return vendeurFacade.getVendeurs();
	}

	public void removeVendeur(VendeurDTO vendeurDTO) throws Exception, RemoteException {
		vendeurFacade.removeVendeur(vendeurDTO);
	}

	public Collection getArticlesEnAttente(String vendeurId) throws RemoteException {
		return vendeurFacade.getArticlesEnAttente(vendeurId);
	}

	public Collection getArticlesEnVente(String vendeurId) throws RemoteException {
		return vendeurFacade.getArticlesEnVente(vendeurId);
	}

	public Collection getArticlesVendus(String vendeurId) throws RemoteException {
		return vendeurFacade.getArticlesVendus(vendeurId);
	}
	
}
