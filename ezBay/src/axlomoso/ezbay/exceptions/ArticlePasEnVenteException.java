package axlomoso.ezbay.exceptions;

public class ArticlePasEnVenteException extends Exception {
	String errMessage = "";
	
	public ArticlePasEnVenteException(){
		errMessage = "L'article n'est pas en vente !";
	}
	
	public ArticlePasEnVenteException(String message){
		errMessage = message;
	}
	
	public String getMessage(){
		return errMessage;
	}
}
