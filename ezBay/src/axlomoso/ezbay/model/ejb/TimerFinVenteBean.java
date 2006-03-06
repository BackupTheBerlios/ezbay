package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerHandle;
import javax.ejb.TimerService;

import javax.ejb.CreateException;

import axlomoso.ezbay.model.interfaces.ArticleFacadeLocal;
import axlomoso.ezbay.model.interfaces.ArticleFacadeLocalHome;
import axlomoso.ezbay.utils.ServiceLocator;
import axlomoso.ezbay.utils.ServiceLocatorException;

/**
 * XDoclet-based session bean. The class must be declared public according to
 * the EJB specification.
 * 
 * To generate the EJB related files to this EJB: - Add Standard EJB module to
 * XDoclet project properties - Customize XDoclet configuration for your
 * appserver - Run XDoclet
 * 
 * Below are the xdoclet-related tags needed for this EJB.
 * 
 * @ejb.bean name="TimerFinVente" display-name="Name for TimerFinVenteBean"
 *           description="Description for TimerFinVenteBean"
 *           jndi-name="ejb/TimerFinVente" type="Stateless" view-type="local"
 */
public class TimerFinVenteBean implements SessionBean, TimedObject {

	/** The session context */
	private SessionContext context;

	private TimerHandle timerHandle = null;
	ServiceLocator locator;
	ArticleFacadeLocal articleFacade;
	
	public TimerFinVenteBean() {
		super();
		locator = ServiceLocator.getInstance();
	}

	/**
	 * Set the associated session context. The container calls this method after
	 * the instance creation.
	 * 
	 * The enterprise bean instance should store the reference to the context
	 * object in an instance variable.
	 * 
	 * This method is called with no transaction context.
	 * 
	 * @throws EJBException
	 *             Thrown if method fails due to system-level error.
	 */
	public void setSessionContext(SessionContext newContext)
			throws EJBException {
		context = newContext;
	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * @ejb.interface-method view-type = "local" 
	 * cette methode per,et de creer un timer avec un temps de validite et un identifiant
	 * @param timeout
	 * @param timerName
	 */	 
	
	public void initializeTimer(long timeout, String timerName) {
		try {
			// Creation du Timer
			TimerService ts = context.getTimerService();			
			Timer timer = ts.createTimer(timeout, timerName);			
			timerHandle = timer.getHandle();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}		

	}

	//cette methode s'execute une fois le temps limite du timer est atteind
	public void ejbTimeout(Timer timer) {		
		try{
			ArticleFacadeLocalHome articleFacadeLocalHome = (ArticleFacadeLocalHome) locator.getLocalHome(ArticleFacadeLocalHome.JNDI_NAME);
			articleFacade = (ArticleFacadeLocal) articleFacadeLocalHome.create();
			//on apelle la methode qui permet de finir la vente et qui se trouve dans ArticleFacadeBean
			articleFacade.terminerVente(timer.getInfo().toString());
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage());
		} catch (CreateException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "local" 
	 * cette methode permet d'annuler un timer en passant en parametre l'identifiant du ce dernier
	 * @param timerName
	 */	 
	
	public void cancelTimer(String timerName) {
		try {
			TimerService ts = context.getTimerService();
			Collection timers = ts.getTimers();//la liste des timers disponibles			
			Iterator it = timers.iterator();
			while (it.hasNext()) {
				Timer myTimer = (Timer) it.next();
				if ((myTimer.getInfo().equals(timerName))) {
					//si on trouve le timer on l'arrete
					myTimer.cancel();					
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return;
	}

	

}
