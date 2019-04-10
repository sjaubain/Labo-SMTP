package smtp;

import model.Mail;
import model.Prank;

import java.io.*;
import java.net.Socket;

public class SMTPClient {

    private String smtpServerAddress;
    private int smtpServerPort;

    private Socket clientSocket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public SMTPClient(String smtpServerAddress, int smtpServerPort) throws IOException {
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerPort;

        clientSocket = new Socket(smtpServerAddress, smtpServerPort);
        this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.printWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    }

    public void sendMail(Mail mail) {

        /* initiate smtp connection */
        printWriter.write(SMTPProtocol.INITIATE + "localhost" + SMTPProtocol.CRLF);
        printWriter.flush();

        /* specifies sender and receiver */
        printWriter.write(SMTPProtocol.SENDER + mail.getSenderMailAddress() + SMTPProtocol.CRLF);
        printWriter.flush();
        printWriter.write(SMTPProtocol.RECEIVER + mail.getReceiverMailAddress() + SMTPProtocol.CRLF);
        printWriter.flush();

        /* specifies subject and body of the mail */
        printWriter.write(SMTPProtocol.DATA);
        printWriter.flush();
        printWriter.write(SMTPProtocol.SUBJECT + mail.getSubject() + SMTPProtocol.CRLF);
        printWriter.flush();
        printWriter.write(mail.getBody() + SMTPProtocol.CRLF);
        printWriter.flush();

        /* finish */
        printWriter.write(SMTPProtocol.SEND);
        printWriter.flush();
    }

    public void handlePrank(Prank prank) {

    }

    public void closeConnection() {
        printWriter.write(SMTPProtocol.CLOSE_CONNECTION);
    }
}
