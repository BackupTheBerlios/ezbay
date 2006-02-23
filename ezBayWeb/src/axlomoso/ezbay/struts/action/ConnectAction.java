//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package axlomoso.ezbay.struts.action;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import axlomoso.ezbay.struts.form.ConnectForm;
import axlomoso.ezbay.struts.form.MyEzBayForm;
import axlomoso.ezbay.delegate.MembreFacadeDelegate;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.ArticleFacade;
import axlomoso.ezbay.model.interfaces.ArticleFacadeHome;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.MembreFacade;
import axlomoso.ezbay.model.interfaces.MembreFacadeHome;
/** 
 * MyEclipse Struts
 * Creation date: 02-12-2006
 * 
 * XDoclet definition:
 * @struts.action path="/connect" name="connectForm" input="/jsp/connect.jsp" parameter="do" scope="request" validate="true"
 */
public class ConnectAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	
	public ActionForward validateConnect(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("ConnectAction.validateConnect()");
		ConnectForm connectForm = (ConnectForm) form;
		ActionErrors erreurs = new ActionErrors();
		String target = "";
			try {
				MembreFacadeDelegate membreDelegate = MembreFacadeDelegate.getInstance();
				MembreDTO membreDTO = membreDelegate.getMembre(connectForm.getLogin(), connectForm.getPassword());
				if( membreDTO == null){
					erreurs.add(ActionErrors.GLOBAL_ERROR, new ActionError("myEzBay.erreur.connexion"));
					target = "echec";
				}
				else{					
					request.getSession().setAttribute("membre",membreDTO);
					request.getSession().setAttribute("vendeurId",membreDelegate.getVendeurDTO(membreDTO.getId()).getId());
					target = "succes";
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (FinderException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}			
			if (!erreurs.isEmpty()) {
				saveErrors(request, erreurs);
			}
			return mapping.findForward(target);
		}
	
	
	

}

