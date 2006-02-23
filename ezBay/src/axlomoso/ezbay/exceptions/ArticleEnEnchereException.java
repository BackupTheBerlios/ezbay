package axlomoso.ezbay.exceptions;

public class ArticleEnEnchereException extends Exception {
	String errMessage = "";
	
	public ArticleEnEnchereException(){
		errMessage = "L'article est actuellement ench�ri !";
	}
	
	public ArticleEnEnchereException(String message){
		errMessage = message;
	}
	
	public String getMessage(){
		return errMessage;
	}
}
