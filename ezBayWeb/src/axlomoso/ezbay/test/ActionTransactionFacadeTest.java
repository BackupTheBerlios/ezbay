package axlomoso.ezbay.test;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

import axlomoso.ezbay.model.interfaces.ActionTransactionDTO;
import axlomoso.ezbay.model.interfaces.ActionTransactionFacade;
import axlomoso.ezbay.model.interfaces.ActionTransactionFacadeHome;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.utils.ServiceLocator;
import junit.framework.TestCase;

public class ActionTransactionFacadeTest extends TestCase {
	private ActionTransactionFacade transactionFacade;
	
	public ActionTransactionFacadeTest(String arg0) throws Exception  {
		super(arg0);
		ServiceLocator locator = ServiceLocator.getInstance();
		//creation de la facade du vendeur
		ActionTransactionFacadeHome transactionFacadeHome;
		transactionFacadeHome = (ActionTransactionFacadeHome) locator.getRemoteHome(ActionTransactionFacadeHome.JNDI_NAME, ActionTransactionFacadeHome.class);
		this.transactionFacade = transactionFacadeHome.create();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void createActionTransaction() {
		//return transactionFacade.createActionTransaction(transactionDTO, articleLocal, clientLocal);
	}

	public void getAcquereur() {
		//return transactionFacade.getAcquereur(articleId);
	}

	public void getActionTransactionByArticle() {
		//return transactionFacade.getActionTransactionByArticle(articleId);
	}

	public void setAvis() {
		//transactionFacade.setAvis(transactionId, avis);
	}

}
