package smtp;

/**
 * This class define the base of SMTP protocol commands
 * we need for this lab (non exhaustive list)
 */
public class SMTPProtocol {

    public final static String CRLF = "\r\n";
    public final static String INITIATE = "EHLO ";
    public final static String SENDER = "MAIL FROM: ";
    public final static String RECEIVER = "RCPT TO: ";
    public final static String DATA = "DATA" + CRLF;
    public final static String SUBJECT = "Subject: ";
    public final static String CLOSE_CONNECTION = "quit";
    public final static String SEND = "." + CRLF;
}
