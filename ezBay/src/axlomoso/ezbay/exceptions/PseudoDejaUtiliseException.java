package axlomoso.ezbay.exceptions;

public class PseudoDejaUtiliseException extends Exception {
	String errMessage = "";
	
	public PseudoDejaUtiliseException(){
		errMessage = "Le pseudo est déjà utilisé !";
	}
	
	public PseudoDejaUtiliseException(String message){
		errMessage = message;
	}
	
	public String getMessage(){
		return errMessage;
	}
}
