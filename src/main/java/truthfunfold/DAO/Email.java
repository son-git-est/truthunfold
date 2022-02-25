package truthfunfold.DAO;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import truthunfold.Authentication.*;
import truthunfold.Constant.EmailTemplate;

//import java.util.Scanner;

public class Email {

	public void sendRecoveryEmail(String mailTo, String token) {

		// Scanner input = new Scanner(System.in);
		// String username = input.nextLine();
		// String password = input.nextLine();

		// change accordingly
		String to = mailTo;

		// change accordingly
		String from = "truthUnfold";

		String subject = "Password Reset for Truth Unfold";
		String body = EmailTemplate.recoveryTemplate
				+ "<h3 style='text-align: center;'>Recovery Token: "
				+ token + "</h3>";

		// or IP address
		// String host = "localhost";

		// mail id
		final String username = Authentication.SMTP_USERNAME;

		// correct password for gmail id
		final String password = Authentication.SMTP_PASSWORD;

		System.out.println("TLSEmail Start");
		// Get the session object

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", Authentication.SMTP_HOST);

		// SSL Port
		properties.put("mail.smtp.port", Authentication.SMTP_PORT);

		// enable authentication
		properties.put("mail.smtp.auth", "true");

		// SSL Factory
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// creating Session instance referenced to
		// Authenticator object to pass in
		// Session.getInstance argument
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {

			// override the getPasswordAuthentication
			// method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// compose the message
		try {
			// javax.mail.internet.MimeMessage class is mostly
			// used for abstraction.
			MimeMessage message = new MimeMessage(session);

			// header field of the header.
			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(body, "text/html");

			// Send message
			Transport.send(message);
			// System.out.println("Yo it has been sent..");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public void sendContactUsEmail(String name, String mailFrom, String inquiry) {

		// Scanner input = new Scanner(System.in);
		// String username = input.nextLine();
		// String password = input.nextLine();

		// change accordingly
		String to = Authentication.CONTACT_US_EMAIL;

		// change accordingly
		String from = mailFrom;

		String subject = "An email enquiry from reader: " + name;
		String body = inquiry;

		// or IP address
		// String host = "localhost";

		// mail id
		final String username = Authentication.SMTP_USERNAME;

		// correct password for gmail id
		final String password = Authentication.SMTP_PASSWORD;

		System.out.println("TLSEmail Start");
		// Get the session object

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", Authentication.SMTP_HOST);

		// SSL Port
		properties.put("mail.smtp.port", Authentication.SMTP_PORT);

		// enable authentication
		properties.put("mail.smtp.auth", "true");

		// SSL Factory
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// creating Session instance referenced to
		// Authenticator object to pass in
		// Session.getInstance argument
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {

			// override the getPasswordAuthentication
			// method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// compose the message
		try {
			// javax.mail.internet.MimeMessage class is mostly
			// used for abstraction.
			MimeMessage message = new MimeMessage(session);

			// header field of the header.
			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(body, "text/html");

			// Send message
			Transport.send(message);
			// System.out.println("Yo it has been sent..");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
