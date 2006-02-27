package axlomoso.ezbay.struts.views;

import java.util.Date;

import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.utils.Util;

public class ActionEnchereView {
	private ActionEnchereDTO enchereDTO = null;
	private MembreDTO membreDTO = null;
	private String clientId = null;
	private Util util = new Util();
	
	public ActionEnchereView(ActionEnchereDTO enchereDTO){
		this.enchereDTO = enchereDTO;
	}
	
	public Date getDate() {
		return enchereDTO.getDate();
	}
	public Double getMontant() {
		return enchereDTO.getMontant();
	}
	public ActionEnchereDTO getEnchereDTO() {
		return enchereDTO;
	}
	public void setEnchereDTO(ActionEnchereDTO enchereDTO) {
		this.enchereDTO = enchereDTO;
	}
	public MembreDTO getMembreDTO() {
		return membreDTO;
	}
	public void setMembreDTO(MembreDTO membreDTO) {
		this.membreDTO = membreDTO;
	}
	
	public String getFormattedDate(){
		return util.getDateToString(this.getDate(), "dd/MM/yyyy - kk:mm:ss");
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
