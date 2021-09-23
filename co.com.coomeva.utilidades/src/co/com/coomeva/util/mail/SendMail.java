package co.com.coomeva.util.mail;
import javax.mail.*;
import javax.mail.internet.*;

import co.com.coomeva.util.literals.ManejoDeCadenas;
import co.com.coomeva.util.resources.LoaderResourceElements;


import java.io.UnsupportedEncodingException;
import java.util.*;

public class SendMail {
	private static String  SERVER_MAIL_CONFIGURATION = "resources.database.server_mail";

	public void postMail(
		String recipients[],
		String subject,
		String message,
		String from,
        String idCorreoParam)
		throws MessagingException {
		boolean debug = false;
        
        String idCorreo = "";
        if(idCorreoParam!=null && !idCorreoParam.equals("") && !ManejoDeCadenas.validaCadenaVacia(idCorreoParam))
            idCorreo = idCorreoParam;
    	LoaderResourceElements loaderReourceElements = LoaderResourceElements
    	.getInstance();
    	;
		//Configurando las propiedades del servidor
		Properties props = new Properties();
		//props.put("mail.smtp.host", "192.1.4.140");
		Properties propiedades = null;
        try {
			String host = loaderReourceElements
			.getKeyResourceValue(
					SERVER_MAIL_CONFIGURATION,
					"mail.smtp.host");   
			String login = loaderReourceElements
			.getKeyResourceValue(
					SERVER_MAIL_CONFIGURATION,
					"cuenta.correo.envio.login");
			 
			 String password = loaderReourceElements
				.getKeyResourceValue(
						SERVER_MAIL_CONFIGURATION,
						"cuenta.correo.envio.password");   

			props.put("mail.smtp.host", host);
	        propiedades = System.getProperties();
	        propiedades.put("mail.pop3.host",host);
	        propiedades.put("login",login);
	        propiedades.put("password",password);
	        
		} catch (Exception e1) {
			System.out.println("[Error levantando configuración del archivo de propiedades del servidor de correo]");
		}

        
		// create some properties and get the default Session
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(debug);
        

		// create a message
		Message msg = new MimeMessage(session);

		// set the from and to address
		InternetAddress addressFrom;
		try {
			addressFrom = new InternetAddress(from, idCorreo);
            msg.setFrom(addressFrom);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
            if(recipients[i]!=null)
			 addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);
		//Configurando el tipo de contenido
		msg.setSubject(subject);
        msg.setContent(message, "text/html");
        try{
            Transport.send(msg);
        }catch(Exception ex){
            System.out.println("Exception ex:"+ex.getMessage());
            if(ex.getMessage().indexOf("SMTP")!=-1){
            	String hostAlterno = "";
            	String login = "";
            	String password = "";
    			try {
    				hostAlterno = loaderReourceElements
    				.getKeyResourceValue(
    						SERVER_MAIL_CONFIGURATION,
    						"mail.smtp.host");   
    				 login = loaderReourceElements
    				.getKeyResourceValue(
    						SERVER_MAIL_CONFIGURATION,
    						"cuenta.correo.envio.login");
    				 
    				 password = loaderReourceElements
     				.getKeyResourceValue(
     						SERVER_MAIL_CONFIGURATION,
     						"cuenta.correo.envio.password");   
    				 

					props.put("mail.smtp.host",hostAlterno );
				} catch (Exception e1) {
					System.out.println("[Error levantando configuración del archivo de propiedades del servidor de correo alterno]");
				}

                propiedades.put("mail.pop3.host",hostAlterno);
                propiedades.put("login",login);
                propiedades.put("password",password);
                try{
                	Transport.send(msg);
                }catch(Exception e){
                	System.out.println("No se consiguío mandar el correo :"+e.getMessage());
                }
            }
            
        }
		
	}
}