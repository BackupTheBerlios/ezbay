package axlomoso.ezbay.model.views;

import axlomoso.ezbay.model.interfaces.VendeurDTO;

public class VendeurView {
	
	VendeurDTO vendeurDTO = new VendeurDTO();

	public VendeurDTO getVendeurDTO() {
		return vendeurDTO;
	}

	public void setVendeurDTO(VendeurDTO vendeurDTO) {
		this.vendeurDTO = vendeurDTO;
	}

	public Object clone() throws CloneNotSupportedException {
		return vendeurDTO.clone();
	}

	public boolean equals(Object other) {
		return vendeurDTO.equals(other);
	}

	public boolean equals(VendeurDTO that) {
		return vendeurDTO.equals(that);
	}

	public String getId() {
		return vendeurDTO.getId();
	}

	public String getPrimaryKey() {
		return vendeurDTO.getPrimaryKey();
	}

	public int hashCode() {
		return vendeurDTO.hashCode();
	}

	public boolean idHasBeenSet() {
		return vendeurDTO.idHasBeenSet();
	}

	public boolean isIdentical(Object other) {
		return vendeurDTO.isIdentical(other);
	}

	public void setId(String id) {
		vendeurDTO.setId(id);
	}

	public void setPrimaryKey(String primaryKey) {
		vendeurDTO.setPrimaryKey(primaryKey);
	}

	public String toString() {
		return vendeurDTO.toString();
	}
	
	
}
