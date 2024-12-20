package controller.email;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService implements IJavaMail {

    /**
     *
     * @param sendTo
     * @param topic
     * @param tokenReset
     * @return
     */
    @Override
    public boolean sendResetPassword(String sendTo, String topic, String tokenReset) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", EmailProperty.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", EmailProperty.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", EmailProperty.SSL_PORT);

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailProperty.APP_EMAIL, EmailProperty.APP_PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
            message.setSubject(topic, "UTF-8");

            String htmlContent = "<html>"
                    + "<body style=\"font-family:Arial, sans-serif; background-color:#f9f9f9; padding:20px;\">"
                    + "<div style=\"max-width:600px; margin:0 auto; background:white; padding:20px; border-radius:8px; box-shadow:0 0 10px rgba(0,0,0,0.1);\">"
                    + "<h2 style=\"color:#333; text-align:center;\">Thông báo từ DT Shop</h2>"
                    + "<p style=\"font-size:16px; color:#555;\">"
                    + "Mật khẩu <b>DT Shop</b> của bạn có thể được đặt lại bằng nút bên dưới. "
                    + "Nếu bạn không yêu cầu mật khẩu mới, hãy bỏ qua email này."
                    + "</p>"
                    + "<div style=\"text-align:center; margin-top:20px;\">"
                    + "<a href=\"" + tokenReset + "\" style=\"display:inline-block; padding:10px 20px; font-size:16px; color:white; background-color:#545454; text-decoration:none; border-radius:5px;\">Đặt lại mật khẩu</a>"
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            message.setContent(htmlContent, "text/html; charset=UTF-8");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean sendAccountVerification(String sendTo, String topic, String codeVerify) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", EmailProperty.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", EmailProperty.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", EmailProperty.SSL_PORT);

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailProperty.APP_EMAIL, EmailProperty.APP_PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
            message.setSubject(topic, "UTF-8");

            String htmlContent = "<html>"
                    + "<body style=\"font-family:Arial, sans-serif; background-color:#f9f9f9; padding:20px;\">"
                    + "<div style=\"max-width:600px; margin:0 auto; background:white; padding:20px; border-radius:8px; box-shadow:0 0 10px rgba(0,0,0,0.1);\">"
                    + "<h2 style=\"color:#333; text-align:center;\">Chào mừng đến với DT Shop!</h2>"
                    + "<p style=\"font-size:16px; color:#555;\">"
                    + "Cảm ơn bạn đã đăng ký tài khoản tại <b>DT Shop</b>. "
                    + "Vui lòng nhấn vào nút bên dưới để xác minh tài khoản của bạn và bắt đầu trải nghiệm dịch vụ của chúng tôi."
                    + "</p>"
                    + "<div style=\"text-align:center; margin-top:20px;\">"
                    + "<a href=\"" + codeVerify + "\" style=\"display:inline-block; padding:10px 20px; font-size:16px; color:white; background-color:#545454; text-decoration:none; border-radius:5px;\">Xác minh tài khoản</a>"
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            message.setContent(htmlContent, "text/html; charset=UTF-8");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String sentTo = "dellcosteen147@gmail.com";
        String topic = "Test Gmail";
        String tokenReset = "https://goctruyentranhvui7.com/truyen/theo-doi";

        EmailService emailService = new EmailService();
        boolean isSent = emailService.sendAccountVerification(sentTo, topic, tokenReset);

        if (isSent) {
            System.out.println("Email đã được gửi thành công!");
        } else {
            System.out.println("Gửi email thất bại!");
        }
    }
}
