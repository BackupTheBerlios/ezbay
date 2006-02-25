package axlomoso.ezbay.delegate;

import java.rmi.RemoteException;
import javax.ejb.CreateException;


import axlomoso.ezbay.model.interfaces.TimerFinVenteBean;
import axlomoso.ezbay.model.interfaces.TimerFinVenteBeanHome;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;




public class TimerDelegate {
	
	private TimerFinVenteBean timerFinVente=null;	
	private static TimerDelegate instance = null;
	
	public static TimerDelegate getInstance(){
		if( instance == null ) instance = new TimerDelegate();
		return instance;
	}
		
	private TimerDelegate(){
		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			TimerFinVenteBeanHome home;
			home = (TimerFinVenteBeanHome) locator.getRemoteHome(TimerFinVenteBeanHome.JNDI_NAME, TimerFinVenteBeanHome.class);
			this.timerFinVente = home.create();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}

	}


	public void cancelTimer(String timerName) {
		try {
			timerFinVente.cancelTimer(timerName);
		} catch (RemoteException e) {			
			e.printStackTrace();
		}
	}

	public void initializeTimer(long timeout, String timerName) {
		try {
			timerFinVente.initializeTimer(timeout, timerName);
		} catch (RemoteException e) {			
			e.printStackTrace();
		}
	}
	
	
	

	
}
