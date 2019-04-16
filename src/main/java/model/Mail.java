package model;

public class Mail {

    private String senderMailAddress;
    private String receiverMailAddress;
    private Message message;

    public Mail(String senderMailAddress, String receiverMailAddress, Message message) {
        this.senderMailAddress = senderMailAddress;
        this.receiverMailAddress = receiverMailAddress;
        this.message = message;
    }

    public String getSenderMailAddress() {
        return senderMailAddress;
    }

    public String getReceiverMailAddress() {
        return receiverMailAddress;
    }

    public Message getMessage() { return message; }}
