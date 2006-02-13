package axlomoso.ezbay.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;

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
}
