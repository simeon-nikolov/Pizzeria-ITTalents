package servlets;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/send")
public class SendEmailServlet extends BaseHttpServlet {
	private static final String DEFAULT_ADMIN_EMAIL = "pizzabug1@gmail.com";
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = "pizzabug1@gmail.com";
		String pass = "pizza12345";
		String from = DEFAULT_ADMIN_EMAIL;
		String to = request.getParameter("email");
		String text = request.getParameter("message");

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, pass);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("A testing mail header !!!");
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		// Get system properties
		// Properties properties = System.getProperties();
		// String host = "localhost";
		//
		// // Setup mail server
		// properties.setProperty("mail.smtp.host", host);
		//// properties.setProperty("mail.smtp.port", "26");
		//// properties.setProperty("mail.smtp.auth", "true");
		//
		// // Get the default Session object.
		// // Session session = Session.getInstance(properties,
		// // new javax.mail.Authenticator() {
		// // protected PasswordAuthentication getPasswordAuthentication() {
		// // return new PasswordAuthentication(username, pass);
		// // }
		// // });
		// Session session = Session.getDefaultInstance(properties);
		// try {
		// // Create a default MimeMessage object.
		// Message message = new MimeMessage(session);
		// // Set From: header field of the header.
		// message.setFrom(new InternetAddress(from));
		// // Set To: header field of the header.
		// message.addRecipient(Message.RecipientType.TO, new
		// InternetAddress(to));
		// // Set Subject: header field
		// message.setSubject("Mail from site");
		// // Now set the actual message
		// message.setText(text);
		// // Send message
		// Transport.send(message);
		// } catch (MessagingException mex) {
		// mex.printStackTrace();
		// }
	}

}
