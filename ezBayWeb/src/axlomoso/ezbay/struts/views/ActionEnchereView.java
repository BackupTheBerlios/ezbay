package axlomoso.ezbay.struts.views;

import java.util.Date;

import axlomoso.ezbay.model.interfaces.ActionEnchereDTO;
import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.model.interfaces.VendeurDTO;
import axlomoso.ezbay.utils.Util;

public class ActionEnchereView {
	private ActionEnchereDTO enchereDTO = null;
	private MembreDTO membreDTO = null;
	private Util util = new Util();
	
	public Date getDate() {
		return enchereDTO.getDate();
	}
	public Double getMontant() {
		return enchereDTO.getMontant();
	}
	public String getPseudo() {
		return membreDTO.getPseudo();
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
		return util.getDateToString(this.getDate());
	}

}
