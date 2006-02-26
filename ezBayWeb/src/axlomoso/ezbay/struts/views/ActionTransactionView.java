package axlomoso.ezbay.struts.views;

import java.util.Date;

import axlomoso.ezbay.model.interfaces.ActionTransactionDTO;
import axlomoso.ezbay.model.interfaces.MembreDTO;
import axlomoso.ezbay.utils.Util;

public class ActionTransactionView {
	private ActionTransactionDTO transactionDTO = null;
	private MembreDTO membreDTO = null;
	private String clientId = null;
	private Util util = new Util();
	
	public ActionTransactionView(ActionTransactionDTO transactionDTO){
		this.transactionDTO = transactionDTO;
	}
	
	public Date getDate() {
		return transactionDTO.getDate();
	}
	public Double getMontant() {
		return transactionDTO.getMontant();
	}
	public ActionTransactionDTO getTransactionDTO() {
		return transactionDTO;
	}
	public void setTransactionDTO(ActionTransactionDTO transactionDTO) {
		this.transactionDTO = transactionDTO;
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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
