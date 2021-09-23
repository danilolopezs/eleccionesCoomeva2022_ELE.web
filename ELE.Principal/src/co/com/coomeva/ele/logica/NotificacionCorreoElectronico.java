package co.com.coomeva.ele.logica;


import javax.mail.MessagingException;

import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.mail.SendMail;

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
			String mensaje, String... destinos) throws MessagingException {
		SendMail mailer = new SendMail();
		String mailSender = UtilAcceso.getParametroFuenteS("mensajes",
				"mailNotificacionesSender");
		mailer.postMail(destinos, asunto, mensaje, mailSender,null);
	}

}
