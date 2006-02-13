package axlomoso.ezbay.model.views;

import java.util.Date;

import axlomoso.ezbay.model.interfaces.MembreDTO;

public class MembreView {
	private MembreDTO membreDTO = new MembreDTO();
	private VendeurView vendeurView = new VendeurView();
	private ClientView clientView = new ClientView();
	
	public ClientView getClientView() {
		return clientView;
	}

	public void setClientView(ClientView clientView) {
		this.clientView = clientView;
	}

	public VendeurView getVendeurView() {
		return vendeurView;
	}

	public void setVendeurView(VendeurView vendeurView) {
		this.vendeurView = vendeurView;
	}

	public MembreDTO getMembreDTO() {
		return membreDTO;
	}

	public void setMembreDTO(MembreDTO membreDTO) {
		this.membreDTO = membreDTO;
	}

	
	
	public boolean adresseHasBeenSet() {
		return membreDTO.adresseHasBeenSet();
	}

	public Object clone() throws CloneNotSupportedException {
		return membreDTO.clone();
	}

	public boolean codePostalHasBeenSet() {
		return membreDTO.codePostalHasBeenSet();
	}

	public boolean dateNaissanceHasBeenSet() {
		return membreDTO.dateNaissanceHasBeenSet();
	}

	public boolean emailHasBeenSet() {
		return membreDTO.emailHasBeenSet();
	}

	public boolean equals(MembreDTO that) {
		return membreDTO.equals(that);
	}

	public boolean equals(Object other) {
		return membreDTO.equals(other);
	}

	public String getAdresse() {
		return membreDTO.getAdresse();
	}

	public String getCodePostal() {
		return membreDTO.getCodePostal();
	}

	public Date getDateNaissance() {
		return membreDTO.getDateNaissance();
	}

	public String getEmail() {
		return membreDTO.getEmail();
	}

	public String getId() {
		return membreDTO.getId();
	}

	public String getNom() {
		return membreDTO.getNom();
	}

	public String getPassword() {
		return membreDTO.getPassword();
	}

	public String getPays() {
		return membreDTO.getPays();
	}

	public String getPrenom() {
		return membreDTO.getPrenom();
	}

	public String getPrimaryKey() {
		return membreDTO.getPrimaryKey();
	}

	public String getPseudo() {
		return membreDTO.getPseudo();
	}

	public String getTelephoneFixe() {
		return membreDTO.getTelephoneFixe();
	}

	public String getTelephonePortable() {
		return membreDTO.getTelephonePortable();
	}

	public String getVille() {
		return membreDTO.getVille();
	}

	public int hashCode() {
		return membreDTO.hashCode();
	}

	public boolean idHasBeenSet() {
		return membreDTO.idHasBeenSet();
	}

	public boolean isIdentical(Object other) {
		return membreDTO.isIdentical(other);
	}

	public boolean nomHasBeenSet() {
		return membreDTO.nomHasBeenSet();
	}

	public boolean passwordHasBeenSet() {
		return membreDTO.passwordHasBeenSet();
	}

	public boolean paysHasBeenSet() {
		return membreDTO.paysHasBeenSet();
	}

	public boolean prenomHasBeenSet() {
		return membreDTO.prenomHasBeenSet();
	}

	public boolean pseudoHasBeenSet() {
		return membreDTO.pseudoHasBeenSet();
	}

	public void setAdresse(String adresse) {
		membreDTO.setAdresse(adresse);
	}

	public void setCodePostal(String codePostal) {
		membreDTO.setCodePostal(codePostal);
	}

	public void setDateNaissance(Date dateNaissance) {
		membreDTO.setDateNaissance(dateNaissance);
	}

	public void setEmail(String email) {
		membreDTO.setEmail(email);
	}

	public void setId(String id) {
		membreDTO.setId(id);
	}

	public void setNom(String nom) {
		membreDTO.setNom(nom);
	}

	public void setPassword(String password) {
		membreDTO.setPassword(password);
	}

	public void setPays(String pays) {
		membreDTO.setPays(pays);
	}

	public void setPrenom(String prenom) {
		membreDTO.setPrenom(prenom);
	}

	public void setPrimaryKey(String primaryKey) {
		membreDTO.setPrimaryKey(primaryKey);
	}

	public void setPseudo(String pseudo) {
		membreDTO.setPseudo(pseudo);
	}

	public void setTelephoneFixe(String telephoneFixe) {
		membreDTO.setTelephoneFixe(telephoneFixe);
	}

	public void setTelephonePortable(String telephonePortable) {
		membreDTO.setTelephonePortable(telephonePortable);
	}

	public void setVille(String ville) {
		membreDTO.setVille(ville);
	}

	public boolean telephoneFixeHasBeenSet() {
		return membreDTO.telephoneFixeHasBeenSet();
	}

	public boolean telephonePortableHasBeenSet() {
		return membreDTO.telephonePortableHasBeenSet();
	}

	public String toString() {
		return membreDTO.toString();
	}

	public boolean villeHasBeenSet() {
		return membreDTO.villeHasBeenSet();
	}
	
	
	
}
