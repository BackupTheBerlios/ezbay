package axlomoso.ezbay.exceptions;

public class VendeurInconnuException extends Exception {
	String errMessage = "";
	
	public VendeurInconnuException(){
		errMessage = "Le vendeur n'existe pas";
	}
	
	public VendeurInconnuException(String message){
		errMessage = message;
	}
	
	public String getMessage(){
		return errMessage;
	}
}
