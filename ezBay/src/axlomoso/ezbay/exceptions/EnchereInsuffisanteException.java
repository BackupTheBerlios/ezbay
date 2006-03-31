package axlomoso.ezbay.exceptions;

public class EnchereInsuffisanteException extends Exception {
	String errMessage = "";
	
	public EnchereInsuffisanteException(){
		errMessage = "L'ench�re est insuffisante !";
	}
	
	public EnchereInsuffisanteException(String message){
		errMessage = message;
	}
	
	public String getMessage(){
		return errMessage;
	}
}
