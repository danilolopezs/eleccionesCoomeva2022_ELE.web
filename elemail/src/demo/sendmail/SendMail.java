package demo.sendmail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	String soName = System.getProperty("os.name").toLowerCase();

    public void enviar() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.coomeva.com.co");
        props.put("mail.smtp.port", "25");
        
        System.out.println(soName);
/*
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("carlosortegaq@gmail.com","ezlspgtnhplzaywh");
                    }
                });
*/
        Session session = Session.getInstance(props);
        
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-repli@coomeva.com.co"));
            
            InternetAddress[] addressTo = new InternetAddress[1];
			addressTo[0] = new InternetAddress("carlose_ortega_contratista@coomeva.com.co");            
            message.setRecipients(Message.RecipientType.TO,addressTo);
            StringBuffer contenido = new StringBuffer();
            contenido.append("<html><head><meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1'></head><body><table>");
            contenido.append("<tr><td colspan='2'>Se solicita el trámite de cambio de ips <b>Número"+ "1"+"</b>, con los siguientes datos: </td></tr><tr><td colspan='2'>&nbsp;</td></tr>");
            contenido.append("</body></html>");
            StringBuffer mensaje= new StringBuffer();
            mensaje.append(contenido.toString().replace("ó", "&oacute;").replace("é", "&eacute;").replace("í", "&iacute;").replace("á", "&aacute;").replace("ú", "&uacute;").replace("ñ", "&ntilde;").replace("Ñ", "&Ntilde;"));
            
            message.setContent(contenido.toString(), "text/html");
            message.setSubject("Asunto de envío");
            //message.setText("Mensaje de envío");//También funciona como cuerpo del correo            
            Transport.send(message);
        } catch (MessagingException e) {     
        	
            throw new RuntimeException(e);
        }
    }

    private boolean isWindows() {
        return (soName.indexOf("win") >= 0);
    }
 
    private boolean isMac() {
        return (soName.indexOf("mac") >= 0);
    }
 
    private boolean isUnix() {
        return (soName.indexOf("nix") >= 0 || soName.indexOf("nux") >= 0 || soName.indexOf("aix") > 0 );
    }
 
    private boolean isSolaris() {
        return (soName.indexOf("sunos") >= 0);
    }
}
