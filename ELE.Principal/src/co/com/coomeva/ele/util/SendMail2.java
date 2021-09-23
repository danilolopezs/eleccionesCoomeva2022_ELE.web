package co.com.coomeva.ele.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail2 {
	private static String SERVER_MAIL_CONFIGURATION = "server_mail";

	public void postMail(String correosDestino[], String asunto, String mensaje)
			throws Exception {

		//EnvioCorreoLogic.enviarEmail();
		
		boolean debug = false;

		LoaderResourceElements loaderReourceElements = LoaderResourceElements
				.getInstance();
		
		// Configurando las propiedades del servidor
		Properties props = new Properties();
		try {
			String host = loaderReourceElements.getKeyResourceValue(
					SERVER_MAIL_CONFIGURATION, "mail.smtp.host");
			String correoOrigen = loaderReourceElements.getKeyResourceValue(
					SERVER_MAIL_CONFIGURATION, "cuenta.correo.origen");

			String puerto = loaderReourceElements.getKeyResourceValue(
					SERVER_MAIL_CONFIGURATION, "cuenta.correo.port");

			props.put("mail.smtp.auth", "false");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", puerto);
			// Modificado por Juan Diego Montoya
			// Se modifica para que se visualicen los caracteres especiales
//			String charsetCorreo = "UTF-8";
//			props.put("mail.smtp.timeout", 600);	
//			props.setProperty("file.encoding", charsetCorreo);
//			props.put("file.encoding", charsetCorreo);
//			props.setProperty("mail.mime.charset",charsetCorreo);
			
			
			
			// Create a Session from the Properties and the Authenticator
			Session session = Session.getInstance(props);

			//session.setDebug(debug);

			// Create a MimeMessage
			Message message = new MimeMessage(session);
			message.setContent(mensaje,"text/html");

			// Set the from and to address
			//InternetAddress addressFrom = new InternetAddress(correoOrigen.trim());
			//message.setFrom(addressFrom);

			InternetAddress[] addressTo = new InternetAddress[correosDestino.length];
			for (int i = 0; i < correosDestino.length; i++) {
	            if(correosDestino[i]!=null)
				 addressTo[i] = new InternetAddress(correosDestino[i]);
			}
			message.setRecipients(Message.RecipientType.TO, addressTo);	
			message.setSubject(asunto);
			
			
//			MimeBodyPart messageBodyPart = new MimeBodyPart();
//			messageBodyPart.setContent(mensaje, "text/html");
//			 
//			Multipart multipart = new MimeMultipart("related");
//	        
//	        multipart.addBodyPart(messageBodyPart);
	 
//	        message.setContent(multipart);
	        message.setFrom(new InternetAddress(correoOrigen));
	        Transport transport = session.getTransport("smtp");
	        transport.connect(host, "", "");
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			//Transport.send(message);
		} catch (Exception e1) {
			System.out
					.println("[No se logró enviar el correo]");
			throw new MessagingException(
					"No se logró enviar el correo: "
							+ e1.getMessage());
		}
	}
}