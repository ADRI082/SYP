package utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	
	private static final String username = "finalbosspsp@gmail.com";
	private static final String password = "cosillas";
	
	
	public Email() {
		
	}
	
	public void sendEmail(String producto) {
		
		

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("finalbosspsp@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("finalbosspsp@gmail.com"));
			message.setSubject("Pedido a proveedor");
			message.setText("Hola proveedor, necesitamos 50 unidades del producto " + producto);

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
