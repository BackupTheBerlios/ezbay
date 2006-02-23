package axlomoso.ezbay.exceptions;

public class ArticleEnEnchereException extends Exception {
	String errMessage = "";
	
	public ArticleEnEnchereException(){
		errMessage = "L'article est actuellement enchéri !";
	}
	
	public ArticleEnEnchereException(String message){
		errMessage = message;
	}
	
	public String getMessage(){
		return errMessage;
	}
}
