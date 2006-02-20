package axlomoso.ezbay.exceptions;

public class ArticleVenduException extends Exception {
	String errMessage = "";
	
	public ArticleVenduException(){
		errMessage = "L'article est encore en vente !";
	}
	
	public ArticleVenduException(String message){
		errMessage = message;
	}
	
	public String getMessage(){
		return errMessage;
	}
}
