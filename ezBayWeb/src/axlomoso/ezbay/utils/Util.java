package axlomoso.ezbay.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.util.MessageResources;

import axlomoso.ezbay.model.interfaces.ArticleDTO;
import axlomoso.ezbay.struts.views.ArticleView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	/** 
     * Return the message resources for localization 
     */ 
    private static MessageResources getMessageResources(HttpServletRequest request) { 
        return (MessageResources) request.getAttribute(Globals.MESSAGES_KEY); 
    } 

    /** 
     * Get the localized message 
     */ 
    public static String getLocalizedMessage(HttpServletRequest request, String messageKey) { 
        MessageResources messageResources = getMessageResources(request); 
        String localizedMessage = null; 
        if(messageResources != null) { 
            localizedMessage = messageResources.getMessage(request.getLocale(), messageKey); 
        } 
        if(localizedMessage == null) { 
            localizedMessage = "N/A"; 
        } 
        return localizedMessage; 
    }
    
    public boolean checkMailAddress(String mail){
    	String strPattern ="^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
            + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
    	Pattern pattern = Pattern.compile(strPattern);
    	Matcher matcher = pattern.matcher(mail);
        return matcher.find();
    }
    
    public Date getStringToDate(String strDate) throws ParseException{
    	Date tRes = null;
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	tRes = df.parse(strDate);
    	return tRes;
    }


    
    public String getDateToString(Date date){
    	String tRes = "";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		tRes = df.format(date); 
    	return tRes;
    }
    
	/* transforme une liste d'ArticleDTO en une liste d'articleView */
    public Collection getArticlesDtoToView(Collection articles) {
		Collection tRes = new ArrayList();
		for (Iterator it = articles.iterator(); it.hasNext(); ) {
			ArticleDTO articleDTO = (ArticleDTO) it.next();
			ArticleView articleView = new ArticleView();
			articleView.setArticleDTO(articleDTO);
			tRes.add(articleView);
	    }		
		return tRes;
	}
    
}
