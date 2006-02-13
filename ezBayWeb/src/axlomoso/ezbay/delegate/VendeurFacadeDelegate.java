package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;
import java.util.Collection;

import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacade;

public class VendeurFacadeDelegate {
	private VendeurFacade vendeurFacade = null;

	public VendeurFacade getVendeurFacade() {
		return vendeurFacade;
	}

	public void setVendeurFacade(VendeurFacade vendeurFacade) {
		this.vendeurFacade = vendeurFacade;
	}

	public VendeurDTO createVendeur() throws Exception, RemoteException {
		return vendeurFacade.createVendeur();
	}

	public Collection getArticles(VendeurDTO vendeurDTO) throws RemoteException {
		return vendeurFacade.getArticles(vendeurDTO);
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
	
}
