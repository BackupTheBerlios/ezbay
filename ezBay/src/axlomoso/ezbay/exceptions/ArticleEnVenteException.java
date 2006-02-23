package axlomoso.ezbay.exceptions;

public class ArticleEnVenteException extends Exception {
	String errMessage = "";
	
	public ArticleEnVenteException(){
		errMessage = "L'article est en vente !";
	}
	
	public ArticleEnVenteException(String message){
		errMessage = message;
	}
	
	public String getMessage(){
		return errMessage;
	}
}
