package axlomoso.ezbay.exceptions;

public class EnchereInsuffisanteException extends Exception {
	String errMessage = "";
	
	public EnchereInsuffisanteException(){
		errMessage = "L'enchère est insuffisante !";
	}
	
	public EnchereInsuffisanteException(String message){
		errMessage = message;
	}
	
	public String getMessage(){
		return errMessage;
	}
}
