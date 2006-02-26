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

import axlomoso.ezbay.model.interfaces.ActionEnchereFacadeLocal;
import axlomoso.ezbay.model.interfaces.ActionEnchereFacadeLocalHome;
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
 * @ejb.bean name="TimerFinVenteBean" display-name="Name for TimerFinVenteBean"
 *           description="Description for TimerFinVenteBean"
 *           jndi-name="ejb/TimerFinVenteBean" type="Stateless" view-type="remote"
 */
public class TimerFinVenteBean implements SessionBean, TimedObject {

	/** The session context */
	private SessionContext context;

	private TimerHandle timerHandle = null;
	ServiceLocator locator;
	ArticleFacadeLocal articleFacade;

	public TimerFinVenteBean() {
		super();
		try {
			locator = ServiceLocator.getInstance();
			ArticleFacadeLocalHome articleFacadeLocalHome = (ArticleFacadeLocalHome) locator.getLocalHome(ArticleFacadeLocalHome.JNDI_NAME);
			articleFacade = (ArticleFacadeLocal) articleFacadeLocalHome.create();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}

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
	 * @ejb.interface-method view-type = "both"
	 * @param
	 */
	public void initializeTimer(long timeout, String timerName) {
		try {
			// Creation du Timer
			TimerService ts = context.getTimerService();			
			Timer timer = ts.createTimer(timeout, timerName);
			System.out.println("Timer created at "
					+ new Date(System.currentTimeMillis())
					+ " with a timeout: " + timeout + " and with info: "
					+ timerName);
			
			timerHandle = timer.getHandle();
		} catch (Exception e) {
			System.out.println("Exception after create timer : " + e.toString());

		}		

	}

	public void ejbTimeout(Timer timer) {
		System.out.println("ejbTimeout() called at: "
				+ new Date(System.currentTimeMillis()) + " with info:");
		articleFacade.terminerVente(timer.getInfo().toString());
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * @param
	 */

	public void cancelTimer(String timerName) {
		try {
			TimerService ts = context.getTimerService();
			Collection timers = ts.getTimers();
			Iterator it = timers.iterator();
			while (it.hasNext()) {
				Timer myTimer = (Timer) it.next();
				if ((myTimer.getInfo().equals(timerName))) {
					myTimer.cancel();
					System.out.println("Successfully Cancelled " + timerName);

				}
			}
		} catch (Exception e) {
			System.out.println("Exception after cancelling timer : "+ e.toString());
		}
		return;
	}

	

}
