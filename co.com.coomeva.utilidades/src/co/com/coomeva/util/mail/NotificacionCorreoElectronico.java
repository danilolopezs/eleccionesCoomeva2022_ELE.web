package co.com.coomeva.util.mail;

import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.mail.SendMail;
import javax.mail.*;

public class NotificacionCorreoElectronico {

	private static NotificacionCorreoElectronico instance;

	private NotificacionCorreoElectronico() {

	}

	public static NotificacionCorreoElectronico getInstance() {
		if (instance == null) {
			instance = new NotificacionCorreoElectronico();
		}
		return instance;
	}

	public void enviarNotificacionCorreoElectronico(String asunto,
			String mensaje, String... destinos) throws Exception {
		SendMail mailer = new SendMail();
		String mailSender = UtilAcceso.getParametroFuenteS("mensajes",
				"mailNotificacionesSender");

		try {
			mailer.postMail(destinos, asunto, mensaje, mailSender, null);
		} catch (MessagingException e) {
			throw new Exception(e.getMessage());
		}
	}

}
