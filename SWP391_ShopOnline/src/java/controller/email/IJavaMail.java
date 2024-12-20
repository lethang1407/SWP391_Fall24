package controller.email;

public interface IJavaMail {

    boolean sendResetPassword(String sendTo, String topic, String tokenReset);
    
    boolean sendAccountVerification (String sendTo, String topic, String codeVerify );
}
