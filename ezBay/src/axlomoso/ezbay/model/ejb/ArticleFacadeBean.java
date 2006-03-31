package axlomoso.ezbay.model.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import axlomoso.ezbay.exceptions.ArticleEnEnchereException;
import axlomoso.ezbay.exceptions.ArticleEnVenteException;
import axlomoso.ezbay.exceptions.ArticlePasEnVenteException;
import axlomoso.ezbay.exceptions.ArticleProprietaireException;
import axlomoso.ezbay.exceptions.ArticleVenduException;
import axlomoso.ezbay.exceptions.EnchereInsuffisanteException;
import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ActionEnchereFacadeLocal;
import axlomoso.ezbay.model.interfaces.ActionEnchereFacadeLocalHome;
import axlomoso.ezbay.model.interfaces.ActionEnchereLocal;
import axlomoso.ezbay.model.interfaces.ActionTransactionDTO;
import axlomoso.ezbay.model.interfaces.ActionTransactionFacadeLocal;
import axlomoso.ezbay.model.interfaces.ActionTransactionFacadeLocalHome;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleLocal;
import axlomoso.ezbay.model.interfaces.ArticleLocalHome;
import axlomoso.ezbay.model.interfaces.CategorieDTO;
import axlomoso.ezbay.model.interfaces.CategorieLocal;
import axlomoso.ezbay.model.interfaces.ClientDTO;
import axlomoso.ezbay.model.interfaces.ClientLocal;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreLocal;
import axlomoso.ezbay.model.interfaces.TimerFinVenteBeanHome;
import axlomoso.ezbay.model.interfaces.TimerFinVenteLocal;
import axlomoso.ezbay.model.interfaces.TimerFinVenteLocalHome;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.model.interfaces.VendeurLocal;
import axlomoso.ezbay.model.interfaces.VendeurLocalHome;
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
 * @ejb.bean name="ArticleFacade" display-name="Name for ArticleFacade"
 *           description="Description for ArticleFacade"
 *           jndi-name="ejb/ArticleFacade" type="Stateless" view-type="both"
 */
public class ArticleFacadeBean implements SessionBean {

	/** The session context */
	private SessionContext context;

	private ServiceLocator locator;
	private ActionEnchereFacadeLocal actionEnchereFacade;
	private ActionTransactionFacadeLocal actionTransactionFacade;
	private TimerFinVenteLocal timerFinVente = null;
	
	public ArticleFacadeBean() {
		super();
		try {
			locator = ServiceLocator.getInstance();
			ActionEnchereFacadeLocalHome actionEnchereFacadeLocalHome = (ActionEnchereFacadeLocalHome) locator.getLocalHome(ActionEnchereFacadeLocalHome.JNDI_NAME);
			actionEnchereFacade = (ActionEnchereFacadeLocal) actionEnchereFacadeLocalHome.create();
			ActionTransactionFacadeLocalHome actionTransactionFacadeLocalHome = (ActionTransactionFacadeLocalHome) locator.getLocalHome(ActionTransactionFacadeLocalHome.JNDI_NAME);
			actionTransactionFacade = (ActionTransactionFacadeLocal) actionTransactionFacadeLocalHome.create();
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage()); 
		} catch (CreateException e) {
			System.out.println(e.getMessage()); 
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
	 * @ejb.interface-method view-type = "local"
	 * cette methode est déclenché a la date limite de l'article 
	 * permet de finir la vente et creer une transaction si l article est encheri
	 * si non de remettre l article en attente
	 * @param articleId
	 */	 
	
	public void terminerVente(String articleId){
		System.out.println("FIN DE LA VENTE de l'article : id=" + articleId);
		try {
			ActionTransactionFacadeLocalHome actionTransactionFacadeLocalHome = (ActionTransactionFacadeLocalHome) locator.getLocalHome(ActionTransactionFacadeLocalHome.JNDI_NAME);
			ActionTransactionFacadeLocal actionTransactionFacade = (ActionTransactionFacadeLocal) actionTransactionFacadeLocalHome.create();
			//retirer l'article de la vente
			this.retirerArticle(articleId);
			ActionEnchereDTO derniereEnchereDTO = this.getDerniereEnchere(articleId);
			if( derniereEnchereDTO != null ){
				/* L'article possède des enchères */
				//créer la transaction
					//ActionTransactionDTO
				ActionTransactionDTO actionTransactionDTO = new ActionTransactionDTO();
				actionTransactionDTO.setMontant(derniereEnchereDTO.getMontant());
					//ClientLocal
				ClientDTO clientDTO = actionEnchereFacade.getEncherisseur(derniereEnchereDTO.getId());
				ClientLocal clientLocal = ClientFacadeBean.getEntity(clientDTO.getId());
					//ArticleLocal
				ArticleLocal articleLocal = getEntity(articleId);
					//transaction
				actionTransactionDTO = actionTransactionFacade.createActionTransaction(actionTransactionDTO, articleLocal, clientLocal);
				//supprimer la derniere enchere
				actionEnchereFacade.removeActionEnchere(derniereEnchereDTO.getId());
				//ArticleBean : info redondante
				MembreDTO membreDTO = clientLocal.getMembreLocal().getMembreDTO();
				articleLocal.setAcheteurPseudo(membreDTO.getPseudo());
				articleLocal.setAcheteurId(clientDTO.getId());
				articleLocal.setTransactionDate(actionTransactionDTO.getDate());
				articleLocal.setTransactionMontant(actionTransactionDTO.getMontant());
				articleLocal.setAcheteurMembreId(membreDTO.getId());
			}
		} catch (CreateException e) {
			System.out.println(e.getMessage()); 
		} catch (FinderException e) {
			System.out.println(e.getMessage()); 
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage()); 
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
		}
	}
	
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * cette methode permet de poser un avis a la fin de la transaction
	 * @param articleId
	 * @param avis
	 */	 
	
	public void deposerTransactionAvis(String articleId, String avis){
		try {
			ArticleLocal articleLocal = getEntity(articleId);
			ActionTransactionDTO transactionDTO = articleLocal.getActionTransactionLocal().getActionTransactionDTO();
			ActionTransactionFacadeLocalHome actionTransactionFacadeLocalHome = (ActionTransactionFacadeLocalHome) locator.getLocalHome(ActionTransactionFacadeLocalHome.JNDI_NAME);
			ActionTransactionFacadeLocal actionTransactionFacade = (ActionTransactionFacadeLocal) actionTransactionFacadeLocalHome.create();
			actionTransactionFacade.setAvis(transactionDTO.getId(), avis);
			//Info redondante
			articleLocal.setTransactionAvis(avis);
		} catch (FinderException e) {
			System.out.println(e.getMessage()); 
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage()); 
		} catch (CreateException e) {
			System.out.println(e.getMessage()); 
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * cette methode permet de creer au mettre a jour un article
	 * elle est appelé en local par une methode dans VendeurFacadeBean
	 * @param vendeurId
	 * @param articleDTO
	 * @param categorieId
	 * @return ArticleDTO
	 * @throws Exception
	 */
	
	public ArticleDTO saveArticle(String vendeurId, ArticleDTO articleDTO,
			String categorieId) throws Exception {
		ArticleDTO tRes = null;
		boolean exists = false;
		try {
			if (articleDTO.getId() == null) {
				exists = false;
			} else {
				ArticleLocal articleLocal = getEntity(articleDTO.getId()); // test de l'existence de l'article
				exists = true;
			}
		} catch (FinderException e) {
			exists = false;
		}
		if (exists) {
			// l'article existe : mise à jour.
			tRes = this.updateArticle(articleDTO, categorieId);
		} else {
			// l'article n'existe pas: création.
			tRes = this.createArticle(vendeurId, articleDTO, categorieId);
		}
		return tRes;
	}

	/**cette methode permet de creer un article en passant en parametre l identifiant du vendeur et l identifiant de la categorie
	 * @param vendeurId
	 * @param articleDTO
	 * @param categorieId
	 * @return ArticleDTO
	 * @throws Exception
	 */
	private ArticleDTO createArticle(String vendeurId, ArticleDTO articleDTO, String categorieId) throws Exception {
		ArticleDTO tRes = null;
		try {
			ArticleDTO tArticleDTO = articleDTO;
			tArticleDTO.setEnVente(new Boolean(false));
			ArticleLocalHome home = getEntityHome();
			VendeurLocal vendeurLocal = VendeurFacadeBean.getEntity(vendeurId);
			ArticleLocal articleLocal = home.create(articleDTO, vendeurLocal);
			// categorie
			CategorieLocal categorieLocal = CategorieFacadeBean.getEntity(categorieId);
			articleLocal.setCategorieLocal(categorieLocal);
			articleLocal.setVendeurId(vendeurId);
			//InfoRedondante
			MembreLocal membreLocal = vendeurLocal.getMembreLocal();
			articleLocal.setVendeurMembreId(membreLocal.getId());
			articleLocal.setVendeurPseudo(membreLocal.getPseudo());
			// return
			tRes = articleLocal.getArticleDTO();
		} catch (Exception e) {
			throw new Exception("Cannot create article", e);
		}
		return tRes;
	}

	/**cette methode permet de mettre a jour l article
	 * @param articleDTO
	 * @param categorieId
	 * @return ArticleDTO
	 * @throws Exception
	 */
	private ArticleDTO updateArticle(ArticleDTO articleDTO, String categorieId)
			throws Exception {
		ArticleDTO tRes = null;
		try {
			ArticleLocal articleLocal = getEntity(articleDTO.getId());
			String id = articleLocal.updateArticle(articleDTO);
			// categorie
			CategorieLocal categorieLocal = CategorieFacadeBean
					.getEntity(categorieId);
			articleLocal.setCategorieLocal(categorieLocal);
			// return
			tRes = getEntity(id).getArticleDTO();
		} catch (Exception e) {
			throw new Exception("Cannot update article", e);
		}
		return tRes;
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * cette methode permet de supprimer un article si il n'est pas encheri ou si il n'est pas vendu
	 * @param articleId
	 * @throws ArticleEnVenteException
	 * @throws ArticleVenduException
	 * @throws Exception
	 */	
	
	public void removeArticle(String articleId) throws ArticleEnVenteException, ArticleVenduException,Exception {		
		
		ArticleLocal articleLocal = getEntity(articleId);
		if( this.isArticleEnVente(articleLocal.getArticleDTO()) ){
			throw new ArticleEnVenteException();
		}
		else if( this.isArticleVendu(articleLocal.getArticleDTO()) ){
			throw new ArticleVenduException();
		}
		try {
			articleLocal.remove();
		} catch (Exception e) {
			throw new Exception("Cannot remove article", e);
		}
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * cette methode permet de retirer un article si il n'est pas encheri pour le remettre en attente
	 * cette mehode annule le timer qu on a mis au moment de la mise en vente de l article
	 * @param articleId
	 * @throws Exception
	 */
	
	
	public void retirerArticle(String articleId) throws Exception {		
		
		try {
			ArticleLocal articleLocal = getEntity(articleId);
			articleLocal.setEnVente(new Boolean(false));
			this.annulerTimer(articleId);//on annule le timer
		} catch (Exception e) {
			throw new Exception("Impossible de retirer l'article de la vente", e);
		}
	}
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * cette methode permet de mettre en vente un article
	 * @param articleId
	 * @throws ArticleVenduException
	 * @throws Exception
	 */
	
	public void mettreEnVenteArticle(String articleId) throws ArticleVenduException,Exception {		
		ArticleLocal articleLocal = getEntity(articleId);
		if( this.isArticleVendu(articleLocal.getArticleDTO()) ){
			throw new ArticleVenduException();
		}
		try {
			articleLocal.setEnVente(new Boolean(true));
			this.intialiserTimer(articleId);		
		} catch (Exception e) {
			throw new Exception("Impossible de mettre l'article en vente", e);
		}
	}
	
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * cette methode permet de retourner un article en passant en parametre son identifiant
	 * @param articleId
	 * @return ArticleDTO
	 * @throws FinderException
	 */
	
	public ArticleDTO getArticle(String articleId) throws FinderException {
		try {
			ArticleLocal articleLocal = getEntity(articleId);
			return articleLocal.getArticleDTO();
		} catch (FinderException e) {
			throw new FinderException("Cannot get article" + e.getMessage());
		}
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * cette methode permet de reourner le vendeur de l article en parametre l identifiant de ce dernier
	 * @param articleId
	 * @return VendeurDTO
	 * @throws Exception
	 */
	
	public VendeurDTO getVendeurDTO(String articleId) throws Exception {
		try {
			ArticleLocal articleLocal = getEntity(articleId);
			return articleLocal.getVendeurLocal().getVendeurDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get article", e);
		}
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * cette methode permet de reourner la categorie de l article en parametre l identifiant de ce dernier
	 * @param articleId
	 * @return CategorieDTO
	 * @throws Exception
	 */
	
	public CategorieDTO getCategorieDTO(String articleId) throws Exception {
		try {
			ArticleLocal articleLocal = getEntity(articleId);
			return articleLocal.getCategorieLocal().getCategorieDTO();
		} catch (Exception e) {
			throw new Exception("Cannot get categorie", e);
		}
	}

	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * cette methode permet une recherche multicritere des articles
	 * @param libcategorie
	 * @param libelle
	 * @param marque
	 * @param modele
	 * @param prixVenteMin
	 * @param prixVenteMax
	 * @param anneeFabrication
	 * @param dateLimite
	 * @return Collection
	 */
	
	public Collection rechercherArticlesEnVente(String libcategorie, String libelle, String marque, String modele, Double prixVenteMin,
			Double prixVenteMax, Integer anneeFabrication, Date dateLimite) {
		//retourne une collection d'ArticleDTO
		Collection tTemp = this.rechercherArticles(libcategorie, libelle, marque, modele, prixVenteMin, prixVenteMax, anneeFabrication, dateLimite);//retourne tous les aricles selon les parametres
		return this.getOnlyArticlesEnVente(tTemp);//retourne que les articles qui sont en vente
	}
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * cette methode permet de retourner la liste des articles en vente pour un vendeur
	 * @param vendeurId
	 * @return Collection
	 */
	
	public Collection getArticlesEnVenteByVendeur(String vendeurId) {
		//	retourne une collection d'ArticleDTO
		return this.getOnlyArticlesEnVente(this.getArticlesByVendeur(vendeurId));
	}
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * cette methode permet de retourner la liste des articles en attente pour un vendeur
	 * @param vendeurId
	 * @return Collection
	 */
	public Collection getArticlesEnAttenteByVendeur(String vendeurId) {
		//	retourne une collection d'ArticleDTO
		return this.getOnlyArticlesEnAttente(this.getArticlesByVendeur(vendeurId));
	}
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * cette methode permet de retourner la liste des articles en vendus pour un vendeur
	 * @param vendeurId
	 * @return Collection
	 */
	public Collection getArticlesVendusByVendeur(String vendeurId) {
		//	retourne une collection d'ArticleDTO
		return this.getOnlyArticlesVendus(this.getArticlesByVendeur(vendeurId));
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * cette methode permet de retourner la liste des articles en vente pour une categorie bien defini
	 * @param vendeurId
	 * @return Collection
	 */
	public Collection getArticlesEnVenteByCategorie(String categorieId) {
		//	retourne une collection d'ArticleDTO		
		return this.getOnlyArticlesEnVente(this.getArticlesByCategorie(categorieId));		
	
	}
	
	
	private Collection rechercherArticles(String libcategorie, String libelle, String marque, String modele, Double prixVenteMin,
			Double prixVenteMax, Integer anneeFabrication, Date dateLimite) {
		Collection articles = null;
		Collection tRes = new ArrayList();
		try {
			ArticleLocalHome home = getEntityHome();
			//on fait refernce a un finder pour la recherche multicritere
			articles = home.findByFields(libcategorie, "%" + libelle + "%", "%" + marque + "%", "%" + modele + "%", prixVenteMin,					prixVenteMax, anneeFabrication, dateLimite);
			for (Iterator it = articles.iterator(); it.hasNext();) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				ArticleDTO articleDTO = articleLocal.getArticleDTO();
				tRes.add(articleDTO);//on construit une collection d'articleDTO
			}
		} catch (FinderException e) {
			System.out.println(e.getMessage()); 
		}
		return tRes;
	}
	
	private Collection getArticlesByVendeur(String vendeurId) {
		Collection articles = null;
		Collection tRes = new ArrayList();
		try {
			VendeurLocalHome vendeurHome = VendeurFacadeBean.getEntityHome();
			VendeurLocal vendeur = vendeurHome.findByPrimaryKey(vendeurId);//on recupere le vendeur local
			articles = vendeur.getArticle();//on recupere la liste des articles pour ce vendeur
			for (Iterator it = articles.iterator(); it.hasNext();) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				ArticleDTO articleDTO = articleLocal.getArticleDTO();
				tRes.add(articleDTO);
			}
		} catch (FinderException e) {
			System.out.println(e.getMessage()); 
		}
		return tRes;//on retourne une collection d'articleDTO
	}

	
	private Collection getArticlesByCategorie(String categorieId) {
		Collection articles = null;
		Collection tRes = new ArrayList();
		try {
			CategorieLocal categorie = CategorieFacadeBean.getEntity(categorieId);//on recupere la categorie local
			articles = categorie.getArticleLocal();	//on recupere la liste d'articles pour cette categorie		
			for (Iterator it = articles.iterator(); it.hasNext();) {
				ArticleLocal articleLocal = (ArticleLocal) it.next();
				ArticleDTO articleDTO = articleLocal.getArticleDTO();
				tRes.add(articleDTO);
			}
		} catch (NamingException e) {
			System.out.println(e.getMessage()); 
		} catch (FinderException e) {
			System.out.println(e.getMessage()); 
		} catch (Exception e) {
			System.out.println(e.getMessage()); 
		}	
		return tRes;//on retourne une collection d'articleDTO
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * cette methode permet de retourner la derniere enchere passée sur un article en passant en parametre son identifiant
	 * @param articleId
	 * @return ActionEnchereDTO
	 */
	public ActionEnchereDTO getDerniereEnchere(String articleId){
		ActionEnchereDTO tRes = null;
		ArrayList encheres = this.getEncheres(articleId);
		if( encheres.size() > 0 ){
			tRes = (ActionEnchereDTO)encheres.get(0);//l enchere la plus recente
		}
		return tRes;
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 /**cette methode permet de retourner le nombre d'enchere passé sur un article
	 * @param articleId
	 * @return Integer
	 */
	
	public Integer getNbEncheres(String articleId){
		return new Integer(this.getEncheres(articleId).size());
	}
	
	private ArrayList getEncheres(String articleId) {
		ArrayList encheres = (ArrayList)actionEnchereFacade.getActionEncheresByArticle(articleId);
		return encheres;
	}
	
	/**
	 * @ejb.interface-method view-type = "both"
	 /**cette methode permet de retourner le client qui gagné l'enchere dur l article concerné
	 * @param articleId
	 * @return ClientDTO
	 */
	
	public ClientDTO getAcquereur(String articleId) {
		ClientDTO tRes = null;
		ActionTransactionDTO transactionDTO = this.getTransaction(articleId);
		if( transactionDTO != null ){
			tRes = actionTransactionFacade.getAcquereur(articleId);
		}
		return tRes;
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 /**cette methode permet de retourner la transaction passé sur un article
	 * @param articleId
	 * @return ActionTransactionDTO
	 */
	public ActionTransactionDTO getTransaction(String articleId) {
		return actionTransactionFacade.getActionTransactionByArticle(articleId);
	}
	
	
	/**
	 * @ejb.interface-method view-type = "both"
	 * cette methode permet de retourner le client qui a passé la plus recente enchere
	 * @param articleId
	 * @return ClientDTO
	 */
	
	public ClientDTO getDernierEncherisseur(String articleId){
		ClientDTO tRes = null;
		ActionEnchereDTO enchereDTO = this.getDerniereEnchere(articleId);
		if( enchereDTO != null ){
			String enchereId = enchereDTO.getId();
			tRes = actionEnchereFacade.getEncherisseur(enchereId);
		}
		return tRes;
	}
	
	/**
	 * @ejb.interface-method view-type = "local"
	 * cette methode permet de voir si l article est en enchere ou pas elle est appelé en local
	 * @param articleId
	 * @return boolean
	 */
	
	public boolean isArticleEnEnchere(String articleId) {
		boolean tRes = false;
		try{
			return ( getEntity(articleId).getActionEnchereLocal().size() > 0 );
		}
		catch (FinderException e) {
		System.out.println(e.getMessage()); 
		}
		return tRes;		
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * cette methode permet de voir si l article est vendu ou pas elle est appelé en local
	 * @param articleId
	 * @return boolean
	 */
	public boolean isArticleVendu(String articleId) {
		boolean tRes = false;
		try {
			ArticleDTO articleDTO = getEntity(articleId).getArticleDTO();
			ArticleLocal articleLocal = ArticleFacadeBean.getEntity(articleDTO.getId());
			tRes = (articleLocal.getActionTransactionLocal() != null);
		} catch (FinderException e) {
			System.out.println(e.getMessage()); 
		}
		return tRes;
	}

	
	private boolean isArticleEnAttente(ArticleDTO articleDTO) {
		return !(this.isArticleEnVente(articleDTO) || this.isArticleVendu(articleDTO));
	}

	private boolean isArticleEnVente(ArticleDTO articleDTO) {
		boolean tRes = false;
		Boolean enVente =  articleDTO.getEnVente();
		if( enVente != null ){
			tRes = enVente.booleanValue();
		}
		return tRes;
	}

	 
	private boolean isArticleEnEnchere(ArticleDTO articleDTO) {
		boolean tRes = false;
		try{
			return ( getEntity(articleDTO.getId()).getActionEnchereLocal().size() > 0 );
		}
		catch (FinderException e) {
		System.out.println(e.getMessage()); 
		}
		return tRes;		
	}
	
	
	
	private boolean isArticleVendu(ArticleDTO articleDTO) {
		boolean tRes = false;
		if (this.isArticleEnVente(articleDTO)) {
			tRes = false;
			return tRes;
		}
		try {
			ArticleLocal articleLocal = ArticleFacadeBean.getEntity(articleDTO.getId());
			tRes = (articleLocal.getActionTransactionLocal() != null);
		} catch (FinderException e) {
			System.out.println(e.getMessage()); 
		}
		return tRes;
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * cette methode permet de passer une enchere
	 * @param enchereDTO
	 * @param articleId
	 * @param clientId
	 * @return ActionEnchereDTO
	 * @throws ArticlePasEnVenteException
	 */
	
	public ActionEnchereDTO encherir(ActionEnchereDTO enchereDTO, String articleId, String clientId) throws ArticlePasEnVenteException, EnchereInsuffisanteException{
		ActionEnchereDTO tRes = null;
		try {
			ArticleLocal articleLocal = ArticleFacadeBean.getEntity(articleId);
			if( !this.isArticleEnVente(articleLocal.getArticleDTO()) ) {
				throw new ArticlePasEnVenteException();//on ne peut pas une enchere ur un article qui n'est pas en vente
			}
			else{
				ActionEnchereDTO derniereEnchere = this.getDerniereEnchere(articleId);
				if( (derniereEnchere != null) && (enchereDTO.getMontant().doubleValue() <= derniereEnchere.getMontant().doubleValue())){
					System.out.println("EnchereInsuffisanteException");					
					throw new EnchereInsuffisanteException();//on ne peut pas une enchere ur un article qui n'est pas en vente
				}
				ClientLocal clientLocal = ClientFacadeBean.getEntity(clientId);
				tRes = actionEnchereFacade.createActionEnchere(enchereDTO, articleLocal, clientLocal);//creation de l'enchere
				//informations redondante
				articleLocal.setDerniereEnchereDate(tRes.getDate());
				articleLocal.setDerniereEnchereMontant(tRes.getMontant());
				articleLocal.setEncherisseurClientId(clientId);
				articleLocal.setEncherisseurMembreId(clientLocal.getMembreLocal().getId());
				articleLocal.setEncherisseurPseudo(ClientFacadeBean.getEntity(clientId).getMembreLocal().getMembreDTO().getPseudo());
				articleLocal.setNbEncheres( new Integer(articleLocal.getNbEncheres().intValue() + 1) );
			}
		} catch (ArticlePasEnVenteException e) {
			throw e;
		} catch (FinderException e) {
		} catch (CreateException e) {
		} catch (Exception e) {
		}
		return tRes;
	}
	
	private Collection getOnlyArticlesEnVente(Collection allArticlesLocal){
		// retourne une Collection d'ArticlesDTO
		Collection tRes = new ArrayList();
		for (Iterator it = allArticlesLocal.iterator(); it.hasNext();) {
			ArticleDTO articleDTO = (ArticleDTO) it.next();
			if(this.isArticleEnVente(articleDTO)){
				tRes.add(articleDTO);
			}
		}
		return tRes;
	}
	
	private Collection getOnlyArticlesVendus(Collection allArticlesLocal){
		//retourne une Collection d'ArticlesDTO
		Collection tRes = new ArrayList();
		for (Iterator it = allArticlesLocal.iterator(); it.hasNext();) {
			ArticleDTO articleDTO = (ArticleDTO) it.next();
			if(this.isArticleVendu(articleDTO)){
				tRes.add(articleDTO);
			}
		}
		return tRes;
	}
	
	private Collection getOnlyArticlesEnAttente(Collection allArticlesLocal){
		//retourne une Collection d'ArticlesDTO
		Collection tRes = new ArrayList();
		for (Iterator it = allArticlesLocal.iterator(); it.hasNext();) {
			ArticleDTO articleDTO = (ArticleDTO) it.next();
			if(this.isArticleEnAttente(articleDTO)){
				tRes.add(articleDTO);
			}
		}
		return tRes;
	}
	
	/**cette methode retourne une instance de l'interface local du Article entity bean 
	 * @param id
	 * @return ArticleLocal
	 * @throws FinderException
	 */
	public static ArticleLocal getEntity(String id) throws FinderException {
		try {
			ArticleLocalHome home = getEntityHome();
			return home.findByPrimaryKey(id);
		} catch (FinderException e) {
			throw new FinderException("Cannot locate Article" + e.getMessage());
		}
	}

	/** cette methode retourne une instance de l'interface local Home du Article entity bean  */
	public static ArticleLocalHome getEntityHome() {
		ArticleLocalHome home = null;
		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			home = (ArticleLocalHome) locator
					.getLocalHome(ArticleLocalHome.JNDI_NAME);
		} catch (ServiceLocatorException e) {
			System.out.println(e.getMessage()); 
		}
		return home;
	}
	/** cette methode retourne une instance local du timer session bean
	 * utilisation du design pattern singleton
	 */
	private TimerFinVenteLocal getTimerInstance(){	
		if( timerFinVente != null){
			return timerFinVente;
		}
		else{
			try {
				TimerFinVenteLocalHome timerHome = (TimerFinVenteLocalHome) locator.getLocalHome(TimerFinVenteLocalHome.JNDI_NAME);
				timerFinVente = (TimerFinVenteLocal) timerHome.create();
				} catch (ServiceLocatorException e) {
					System.out.println(e.getMessage()); 
				} catch (CreateException e) {
					System.out.println(e.getMessage()); 
				}
				return timerFinVente;
		}
	}
	/** cette methode permet d'initialiser le timer 
	 * @param articleId
	 */
	private void intialiserTimer(String articleId){
		TimerFinVenteLocal timer=this.getTimerInstance();		
		try {
			long dateSystem=System.currentTimeMillis();
			long dateLimite;
			dateLimite = this.getArticle(articleId).getDateLimite().getTime();		
			long dateExpir=dateLimite-dateSystem;		
			timer.initializeTimer(dateExpir,articleId);
		} catch (Exception e) {			
			System.out.println(e.getMessage()); 
		}
	}
	/** cette methode permet d'annuler le timer 
	 * @param articleId
	 */
	private void annulerTimer(String articleId) {
		TimerFinVenteLocal timer = this.getTimerInstance();	
		timer.cancelTimer(articleId);		
	}

}

	