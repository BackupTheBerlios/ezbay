package axlomoso.ezbay.test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

import axlomoso.ezbay.model.ejb.ActionEnchereFacadeBean;
import axlomoso.ezbay.model.ejb.ArticleFacadeBean;
import axlomoso.ezbay.model.ejb.CategorieFacadeBean;
import axlomoso.ezbay.model.ejb.ClientFacadeBean;
import axlomoso.ezbay.model.ejb.MembreFacadeBean;
import axlomoso.ezbay.model.ejb.VendeurFacadeBean;
import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ActionEnchereFacade;
import axlomoso.ezbay.model.interfaces.ActionEnchereFacadeHome;
import axlomoso.ezbay.model.interfaces.ActionEnchereLocal;
import axlomoso.ezbay.model.interfaces.ActionEnchereLocalHome;
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
import axlomoso.ezbay.model.interfaces.MembreFacade;
import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
import axlomoso.ezbay.model.interfaces.MembreLocal;
import axlomoso.ezbay.model.interfaces.MembreLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurFacade;
import axlomoso.ezbay.model.interfaces.VendeurFacadeHome;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurUtil;
import axlomoso.ezbay.utils.ServiceLocator;
import junit.framework.TestCase;

public class ActionEnchereFacadeTest extends TestCase {
	//	facades remote
	private ActionEnchereFacade enchereFacade;
	private MembreDTO membreDTOcree1;
	private MembreDTO membreDTOcree2;
	private ClientDTO clientDTOcree1;
	private ClientDTO clientDTOcree2;
	private VendeurDTO vendeurDTOcree1;
	private VendeurDTO vendeurDTOcree2;
	private ArticleDTO articleDTOcree1;
	private ArticleDTO articleDTOcree2;
	private CategorieDTO categorieDTOcree;
	
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

	public void testCreateActionEnchere() throws Exception {
		ArticleLocal articleLocal1 = ArticleFacadeBean.getEntity(articleDTOcree1.getId());
		ArticleLocal articleLocal2 = ArticleFacadeBean.getEntity(articleDTOcree2.getId());
		ClientLocal clientLocal = ClientFacadeBean.getEntity(clientDTOcree1.getId());
		ActionEnchereDTO enchereDTO = new ActionEnchereDTO();
		enchereDTO.setDate(articleDTOcree1.getDateLimite());
		enchereDTO.setMontant(articleDTOcree1.getPrixVente());
		ActionEnchereDTO enchereDTOrecup = null;
		try {
			enchereDTOrecup = enchereFacade.createActionEnchere(enchereDTO, articleLocal1, clientLocal);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}
		
		ActionEnchereLocal enchereLocal = ActionEnchereFacadeBean.getEntity(enchereDTOrecup.getId());
		assertNotNull(enchereLocal);
		assertNotNull(enchereLocal.getId());
		ArticleLocal articleLocalRecup = enchereLocal.getArticleLocal();
		ClientLocal clientLocalRecup = enchereLocal.getClientLocal();
		assertTrue( articleLocalRecup.getId().equals(articleLocal1.getId()) );
		assertTrue( clientLocalRecup.getId().equals(clientLocalRecup.getId()) );
		
		//nettoyage
		enchereLocal.remove();
		
	}

	//méthode de test de ActionEnchereFacadeBean.getActionEncheresByArticle() et de ActionEnchereFacadeBean.getEncherisseur()
	public void testGetActionEncheresByArticleAndGetEncherisseur() throws CreateException, RemoteException, FinderException, Exception {
		ArrayList enchereDTOToRemove = new ArrayList();
		ArticleLocal articleLocal1 = ArticleFacadeBean.getEntity(articleDTOcree1.getId());
		ArticleLocal articleLocal2 = ArticleFacadeBean.getEntity(articleDTOcree2.getId());
		ClientLocal clientLocal1 = ClientFacadeBean.getEntity(clientDTOcree1.getId());
		ClientLocal clientLocal2 = ClientFacadeBean.getEntity(clientDTOcree2.getId());
		
		ActionEnchereDTO enchereDTO = null;
		ActionEnchereLocalHome enchereLocalHome = ActionEnchereFacadeBean.getEntityHome();
		
		
		ActionEnchereLocal enchereLocal = null;
		int i1;
		for(i1=0 ; i1<10 ; i1++){
			enchereDTO = new ActionEnchereDTO();
			enchereDTO.setDate(new Date(System.currentTimeMillis() + i1 * 10000 ));
			enchereDTO.setMontant( new Double( articleDTOcree1.getPrixVente().doubleValue() + 10 * i1 ) );
			enchereLocal = enchereLocalHome.create(enchereDTO, articleLocal1, clientLocal1);
			enchereDTOToRemove.add(enchereLocal.getActionEnchereDTO());
		}
		int i2;
		for(i2=0 ; i2<5 ; i2++){
			enchereDTO = new ActionEnchereDTO();
			enchereDTO.setDate(new Date(System.currentTimeMillis() + i2 * 10000 ));
			enchereDTO.setMontant( new Double( articleDTOcree2.getPrixVente().doubleValue() + 10 * i2 ) );
			enchereLocal = enchereLocalHome.create(enchereDTO, articleLocal2, clientLocal2);
			enchereDTOToRemove.add(enchereLocal.getActionEnchereDTO());
		}
		
		//nombre d'enchères
		int nbEncheres;
		nbEncheres = enchereFacade.getActionEncheresByArticle(articleLocal1.getId()).size();
		assertEquals(nbEncheres, i1);
		nbEncheres = enchereFacade.getActionEncheresByArticle(articleLocal2.getId()).size();
		assertEquals(nbEncheres, i2);
		
		Collection encheres;
		encheres = enchereFacade.getActionEncheresByArticle(articleLocal1.getId());
		for(Iterator it = encheres.iterator(); it.hasNext() ;){
			ActionEnchereDTO tEnchereDTO = (ActionEnchereDTO) it.next();
			ActionEnchereLocal ae = ActionEnchereFacadeBean.getEntity(tEnchereDTO.getId());
			ArticleLocal art = ae.getArticleLocal();
			assertTrue( (art.getId()).equals(articleLocal1.getId()) ); // on teste que l'enchère correspond au bon article
			ClientDTO encherisseurDTO = enchereFacade.getEncherisseur(ae.getId());
			assertTrue( encherisseurDTO.getId().equals(clientLocal1.getId()) );// on teste que l'enchère correspond au bon client
		}
		encheres = enchereFacade.getActionEncheresByArticle(articleLocal2.getId());
		for(Iterator it = encheres.iterator(); it.hasNext() ;){
			ActionEnchereDTO tEnchereDTO = (ActionEnchereDTO) it.next();
			ActionEnchereLocal ae = ActionEnchereFacadeBean.getEntity(tEnchereDTO.getId());
			ArticleLocal art = ae.getArticleLocal();
			assertTrue( (art.getId()).equals(articleLocal2.getId()) ); // on teste que l'enchère correspond au bon article
			ClientDTO encherisseurDTO = enchereFacade.getEncherisseur(ae.getId());
			assertTrue( encherisseurDTO.getId().equals(clientLocal2.getId()) );// on teste que l'enchère correspond au bon client
		}
		
		for(Iterator it = enchereDTOToRemove.iterator(); it.hasNext() ;){
			enchereDTO = (ActionEnchereDTO)it.next();
			ActionEnchereFacadeBean.getEntity(enchereDTO.getId()).remove();
		}
				
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
