package model;

public class Mail {

    private String senderMailAddress;
    private String receiverMailAddress;
    private String subject, body;

    public Mail(String senderMailAddress, String receiverMailAddress, String subject, String body) {
        this.senderMailAddress = senderMailAddress;
        this.receiverMailAddress = receiverMailAddress;
        this.subject = subject;
        this.body = body;
    }

    public String getSenderMailAddress() {
        return senderMailAddress;
    }

    public String getReceiverMailAddress() {
        return receiverMailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
