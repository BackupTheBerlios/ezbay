package axlomoso.ezbay.test;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Random;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import junit.framework.TestCase;
import axlomoso.ezbay.model.ejb.ActionTransactionFacadeBean;
import axlomoso.ezbay.model.ejb.ArticleFacadeBean;
import axlomoso.ezbay.model.ejb.CategorieFacadeBean;
import axlomoso.ezbay.model.ejb.ClientFacadeBean;
import axlomoso.ezbay.model.ejb.MembreFacadeBean;
import axlomoso.ezbay.model.ejb.VendeurFacadeBean;
import axlomoso.ezbay.model.interfaces.ActionEnchereFacade;
import axlomoso.ezbay.model.interfaces.ActionEnchereFacadeHome;
import axlomoso.ezbay.model.interfaces.ActionTransactionDTO;
import axlomoso.ezbay.model.interfaces.ActionTransactionFacade;
import axlomoso.ezbay.model.interfaces.ActionTransactionFacadeHome;
import axlomoso.ezbay.model.interfaces.ActionTransactionLocal;
import axlomoso.ezbay.model.interfaces.ActionTransactionLocalHome;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieLocal;
import axlomoso.ezbay.model.interfaces.CategorieLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieUtil;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.model.interfaces.ClientLocalHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreLocal;
import axlomoso.ezbay.model.interfaces.MembreLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurUtil;
import axlomoso.ezbay.utils.ServiceLocator;

public class ActionTransactionFacadeTest extends TestCase {
	//	facades remote
	private ActionTransactionFacade transactionFacade;
	//fields
	private MembreDTO membreDTOcree1;
	private MembreDTO membreDTOcree2;
	private ClientDTO clientDTOcree1;
	private ClientDTO clientDTOcree2;
	private VendeurDTO vendeurDTOcree1;
	private VendeurDTO vendeurDTOcree2;
	private ArticleDTO articleDTOcree1;
	private ArticleDTO articleDTOcree2;
	private CategorieDTO categorieDTOcree;
	
	public ActionTransactionFacadeTest(String arg0) throws Exception  {
		super(arg0);
		ServiceLocator locator = ServiceLocator.getInstance();
		//creation de la facade du vendeur
		ActionTransactionFacadeHome transactionFacadeHome;
		transactionFacadeHome = (ActionTransactionFacadeHome) locator.getRemoteHome(ActionTransactionFacadeHome.JNDI_NAME, ActionTransactionFacadeHome.class);
		this.transactionFacade = transactionFacadeHome.create();
	
		//creation de 2 membres
		MembreDTO tMembreDTO = new MembreDTO();
		MembreLocalHome membreLocalHome = MembreFacadeBean.getEntityHome();
		tMembreDTO.setNom("membre 1");
		MembreLocal membreLocal1 = membreLocalHome.create(tMembreDTO);
		membreDTOcree1 = membreLocal1.getMembreDTO();
		tMembreDTO.setNom("membre 2");
		MembreLocal membreLocal2 = membreLocalHome.create(tMembreDTO);
		membreDTOcree2 = membreLocal2.getMembreDTO();
		
		//creation de 2 clients
		ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
		ClientLocal clientLocal1 = clientLocalHome.create();
		ClientLocal clientLocal2 = clientLocalHome.create();
		clientDTOcree1 = clientLocal1.getClientDTO();
		clientDTOcree2 = clientLocal2.getClientDTO();

		//creation de 2 vendeurs
		VendeurLocalHome vendeurLocalHome = VendeurFacadeBean.getEntityHome();
		VendeurDTO tVendeurDTO;
		tVendeurDTO = new VendeurDTO();
		tVendeurDTO.setCodeSecuCB(VendeurUtil.generateGUID(this));
		tVendeurDTO.setDateExpirCB(new Date(System.currentTimeMillis()));
		tVendeurDTO.setNumCB(VendeurUtil.generateGUID(this));
		tVendeurDTO.setNomProprioCB("vendeur 1");
		VendeurLocal vendeurLocal1 = vendeurLocalHome.create(tVendeurDTO);
		vendeurDTOcree1 = vendeurLocal1.getVendeurDTO();
		tVendeurDTO = new VendeurDTO();
		tVendeurDTO.setCodeSecuCB(VendeurUtil.generateGUID(this));
		tVendeurDTO.setDateExpirCB(new Date(System.currentTimeMillis()));
		tVendeurDTO.setNumCB(VendeurUtil.generateGUID(this));
		tVendeurDTO.setNomProprioCB("vendeur 2");		
		VendeurLocal vendeurLocal2 = vendeurLocalHome.create(tVendeurDTO);
		vendeurDTOcree2 = vendeurLocal2.getVendeurDTO();

		//création de catégorie
		CategorieLocalHome categorieLocalHome = CategorieFacadeBean.getEntityHome();
		categorieDTOcree = new CategorieDTO();
		categorieDTOcree.setLibelle("cat" + CategorieUtil.generateGUID(this));
		CategorieLocal categorieLocal = categorieLocalHome.create(categorieDTOcree);
		categorieDTOcree = categorieLocal.getCategorieDTO();
		
		//création de 2 articles
		Random ran = new Random(); 
		ArticleLocalHome articleLocalHome = ArticleFacadeBean.getEntityHome();
		articleDTOcree1 = new ArticleDTO();
		articleDTOcree1.setAnneeFabrication(new Integer(1900 + ran.nextInt()));
		articleDTOcree1.setDateLimite(new Date(System.currentTimeMillis() + ran.nextInt()));
		articleDTOcree1.setDescription("description " + VendeurUtil.generateGUID(this));
		articleDTOcree1.setLibelle("description " + VendeurUtil.generateGUID(this));
		articleDTOcree1.setMarque("marque " + VendeurUtil.generateGUID(this));
		articleDTOcree1.setModele("modèle " + VendeurUtil.generateGUID(this));
		articleDTOcree1.setPrixVente(new Double(ran.nextDouble()));
		ArticleLocal articleLocal1 = articleLocalHome.create(articleDTOcree1, vendeurLocal1);
		articleLocal1.setCategorieLocal(categorieLocal);
		articleDTOcree1 = articleLocal1.getArticleDTO();
		
		articleDTOcree2 = new ArticleDTO();
		articleDTOcree2.setAnneeFabrication(new Integer(1900 + ran.nextInt()));
		articleDTOcree2.setDateLimite(new Date(System.currentTimeMillis() + ran.nextInt()));
		articleDTOcree2.setDescription("description " + VendeurUtil.generateGUID(this));
		articleDTOcree2.setLibelle("description " + VendeurUtil.generateGUID(this));
		articleDTOcree2.setMarque("marque " + VendeurUtil.generateGUID(this));
		articleDTOcree2.setModele("modèle " + VendeurUtil.generateGUID(this));
		articleDTOcree2.setPrixVente(new Double(ran.nextDouble()));
		ArticleLocal articleLocal2 = articleLocalHome.create(articleDTOcree2, vendeurLocal1);
		articleLocal2.setCategorieLocal(categorieLocal);
		articleDTOcree2 = articleLocal2.getArticleDTO();
		
		//relations :
		membreLocal1.setClientLocal(clientLocal1);
		membreLocal1.setVendeurLocal(vendeurLocal1);
		membreLocal2.setClientLocal(clientLocal2);
		membreLocal2.setVendeurLocal(vendeurLocal2);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		//suppression des vendeurs crees
		VendeurLocalHome vendeurLocalHome=VendeurFacadeBean.getEntityHome();
		vendeurLocalHome.findByPrimaryKey(vendeurDTOcree1.getId()).remove();
		vendeurLocalHome.findByPrimaryKey(vendeurDTOcree2.getId()).remove();
		//suppression des clients crees
		ClientLocalHome clientLocalHome = ClientFacadeBean.getEntityHome();
		clientLocalHome.findByPrimaryKey(clientDTOcree1.getId()).remove();
		clientLocalHome.findByPrimaryKey(clientDTOcree2.getId()).remove();
		//suppression des membres crees
		MembreLocalHome membreLocalHome=MembreFacadeBean.getEntityHome();		
		membreLocalHome.findByPrimaryKey(membreDTOcree1.getId()).remove();
		membreLocalHome.findByPrimaryKey(membreDTOcree2.getId()).remove();
		//suppression des articles
		ArticleFacadeBean.getEntity(articleDTOcree1.getId()).remove();
		ArticleFacadeBean.getEntity(articleDTOcree2.getId()).remove();
		//suppression de la catégorie
		CategorieFacadeBean.getEntity(categorieDTOcree.getId()).remove();
	}

	public void testSetAvis() throws RemoteException, CreateException, FinderException, Exception {
		//prerequis
		ArticleLocal articleLocal = ArticleFacadeBean.getEntity(articleDTOcree1.getId());
		ClientLocal clientLocal = ClientFacadeBean.getEntity(clientDTOcree1.getId());
		ActionTransactionDTO transactionDTO = new ActionTransactionDTO();
		transactionDTO.setDate( new Date( System.currentTimeMillis() ) );
		transactionDTO.setMontant( articleLocal.getPrixVente() );
		ActionTransactionLocalHome transactionHome = ActionTransactionFacadeBean.getEntityHome();
		ActionTransactionLocal transactionLocal = transactionHome.create(transactionDTO);
		transactionLocal.setArticleLocal(articleLocal);
		transactionLocal.setClientLocal(clientLocal);
		
		//action
		String avis = "tres bien";
		transactionFacade.setAvis(transactionLocal.getId(), avis);
		String avisRecup = transactionLocal.getAvis();
		assertEquals( avis, avisRecup );
		
		//nettoyage...
		transactionLocal.remove();
	}

}
