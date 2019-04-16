package model;

public class Message {

    private String subject, body;

    public Message(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() { return subject; }

    public String getBody() { return body; }

    public String toString() {
        return subject + "\r\n\r\n" + body;
    }
}
