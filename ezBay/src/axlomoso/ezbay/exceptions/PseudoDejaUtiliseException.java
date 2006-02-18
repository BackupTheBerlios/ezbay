package axlomoso.ezbay.exceptions;

public class PseudoDejaUtiliseException extends Exception {
	String errMessage = "";
	
	public PseudoDejaUtiliseException(String message){
		errMessage = message;
	}
	
	public String getMessage(){
		return errMessage;
	}
}
