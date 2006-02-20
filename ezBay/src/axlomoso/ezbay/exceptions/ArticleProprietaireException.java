package axlomoso.ezbay.exceptions;

public class ArticleProprietaireException extends Exception {
	String errMessage = "";
	
	public ArticleProprietaireException(){
		errMessage = "L'article n'appartient pas au propri�taire !";
	}
	
	public ArticleProprietaireException(String message){
		errMessage = message;
	}
	
	public String getMessage(){
		return errMessage;
	}
}
