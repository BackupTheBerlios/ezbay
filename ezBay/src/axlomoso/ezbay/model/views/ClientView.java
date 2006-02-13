package axlomoso.ezbay.model.views;

import axlomoso.ezbay.model.interfaces.ClientDTO;

public class ClientView {
	ClientDTO clientDTO = new ClientDTO();

	public ClientDTO getClientDTO() {
		return clientDTO;
	}

	public void setClientDTO(ClientDTO clientDTO) {
		this.clientDTO = clientDTO;
	}

	public Object clone() throws CloneNotSupportedException {
		return clientDTO.clone();
	}

	public boolean equals(ClientDTO that) {
		return clientDTO.equals(that);
	}

	public boolean equals(Object other) {
		return clientDTO.equals(other);
	}

	public String getId() {
		return clientDTO.getId();
	}

	public String getPrimaryKey() {
		return clientDTO.getPrimaryKey();
	}

	public int hashCode() {
		return clientDTO.hashCode();
	}

	public boolean idHasBeenSet() {
		return clientDTO.idHasBeenSet();
	}

	public boolean isIdentical(Object other) {
		return clientDTO.isIdentical(other);
	}

	public void setId(String id) {
		clientDTO.setId(id);
	}

	public void setPrimaryKey(String primaryKey) {
		clientDTO.setPrimaryKey(primaryKey);
	}

	public String toString() {
		return clientDTO.toString();
	}

}
