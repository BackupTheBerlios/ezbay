package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;

import javax.ejb.FinderException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreFacade;
import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.views.MembreView;
import axlomoso.ezbay.model.views.VendeurView;

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

	public MembreView createMembre(MembreDTO membreDTO) throws Exception, RemoteException {
		MembreDTO tMembreDTO =  membreFacade.createMembre(membreDTO);
		return this.createMembreView(tMembreDTO);
	}
	
	public MembreView getMembre(String membreId) throws Exception, RemoteException {
		MembreDTO membreDTO = membreFacade.getMembre(membreId);
		return this.createMembreView(membreDTO);
	}

	public MembreView getMembre(String pseudo, String password) throws FinderException, RemoteException {
		MembreDTO membreDTO = membreFacade.getMembre(pseudo, password);
		return this.createMembreView(membreDTO);
	}

	public void removeMembre(MembreDTO membreDTO) throws Exception, RemoteException {
		membreFacade.removeMembre(membreDTO);
	}

	public void updateMembre(MembreDTO membreDTO) throws Exception, RemoteException {
		membreFacade.updateMembre(membreDTO);
	}
	
	private MembreView createMembreView(MembreDTO membreDTO) throws RemoteException{
		MembreView tRes = null;
		if( membreDTO != null){
			tRes = new MembreView();
			VendeurView vendeurView = new VendeurView();
			VendeurFacadeDelegate vendeurFacade = new VendeurFacadeDelegate();
			VendeurDTO vendeurDTO = membreFacade.getVendeurDTO(membreDTO.getId());
			vendeurView.setVendeurDTO(vendeurDTO);
			tRes.setMembreDTO(membreDTO);
			tRes.setVendeurView(vendeurView);
		}
		return tRes;
	}

}
