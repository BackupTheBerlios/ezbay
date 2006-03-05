package axlomoso.ezbay.test;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;

import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ActionEnchereFacade;
import axlomoso.ezbay.model.interfaces.ActionEnchereFacadeHome;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.model.interfaces.VendeurFacade;
import axlomoso.ezbay.model.interfaces.VendeurFacadeHome;
import axlomoso.ezbay.utils.ServiceLocator;
import junit.framework.TestCase;

public class ActionEnchereFacadeTest extends TestCase {
	//	facades remote
	ActionEnchereFacade enchereFacade;
	
	public ActionEnchereFacadeTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		ServiceLocator locator = ServiceLocator.getInstance();
		//creation de la facade du vendeur
		ActionEnchereFacadeHome enchereFacadeHome;
		enchereFacadeHome = (ActionEnchereFacadeHome) locator.getRemoteHome(ActionEnchereFacadeHome.JNDI_NAME, ActionEnchereFacadeHome.class);
		this.enchereFacade = enchereFacadeHome.create();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreateActionEnchere() {
		//return enchereFacade.createActionEnchere(enchereDTO, articleLocal, clientLocal);
	}

	public void testGetActionEncheresByArticle() {
		//return enchereFacade.getActionEncheresByArticle(articleId);
	}

	public void testGetEncherisseur() {
		//return enchereFacade.getEncherisseur(enchereId);
	}
	
	
	
	public static boolean equalsDTO(ActionEnchereDTO enchereDTO1, ActionEnchereDTO enchereDTO2,boolean testerId){
		boolean tRes = true;
		if (testerId){
			tRes = tRes && ( (enchereDTO1.getId()).equals(enchereDTO1.getId()) );
		}

		tRes = tRes && ( 
				( enchereDTO1.getDate() == null) && ( enchereDTO2.getDate() == null)
				|| ( enchereDTO1.getDate().equals(enchereDTO2.getDate())  )
				);
		/*tRes = tRes && ( 
				( enchereDTO1.getDateLimite() == null) && ( enchereDTO2.getDateLimite() == null)
				|| ( enchereDTO1.getDateLimite().equals(enchereDTO2.getDateLimite())  )
				);*/
		tRes = tRes && ( 
				( enchereDTO1.getMontant() == null) && ( enchereDTO2.getMontant() == null)
				|| ( enchereDTO1.getMontant().equals(enchereDTO2.getMontant())  )
				);
		
		return tRes;
	}
	
}
