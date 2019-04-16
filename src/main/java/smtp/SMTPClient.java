package smtp;

import model.Group;
import model.Mail;
import model.Prank;

import java.util.*;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMTPClient {

    private final static Logger LOG = Logger.getLogger(SMTPClient.class.getName());

    private String smtpServerAddress;
    private int smtpServerPort;

    private Socket clientSocket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public SMTPClient(String smtpServerAddress, int smtpServerPort) throws IOException {

        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerPort;

        try {
            this.clientSocket = new Socket(smtpServerAddress, smtpServerPort);
            LOG.log(Level.INFO, "Successfully connected");
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Unable to connect to the server " + smtpServerAddress + ":" + smtpServerPort);
            System.exit(-1);
        }

        this.bufferedReader = new BufferedReader(new InputStreamReader(
                clientSocket.getInputStream(), "UTF-8"));
        this.printWriter = new PrintWriter(new OutputStreamWriter(
                clientSocket.getOutputStream(), "UTF-8"));
    }

    public void sendMail(Mail mail) throws IOException {

        /* initiate smtp connection */
        sendCommand(SMTPProtocol.INITIATE + "localhost");

        /* specifies sender and receiver */
        sendCommand(SMTPProtocol.SENDER + mail.getSenderMailAddress());
        sendCommand(SMTPProtocol.RECEIVER + mail.getReceiverMailAddress());

        /* DATA */
        sendCommand(SMTPProtocol.DATA);
        sendCommand("From : " + mail.getSenderMailAddress());
        sendCommand("To : " + mail.getReceiverMailAddress());
        /* specify utf-8 encoding for subject */
        sendCommand(SMTPProtocol.SUBJECT + "=?utf-8?B?" +
                Base64.getEncoder().encodeToString(mail.getMessage().getSubject().getBytes()) + "?=");

        /* specify utf-8 encoding for body of mail */
        sendCommand("Content-Type: text/plain; charset=utf-8");

        sendCommand(mail.getMessage().getBody());

        /* finish */
        sendCommand(SMTPProtocol.SEND);

        LOG.log(Level.INFO, "Mail sent from <" + mail.getSenderMailAddress() +
                "> to <" + mail.getReceiverMailAddress() + ">");
    }

    public void sendCommand(String command) throws IOException {
        printWriter.write(command + SMTPProtocol.CRLF);
        printWriter.flush();
    }

    public void handlePrank(Prank prank) throws IOException {

        Group receiverVictims = prank.getReceiverVictims();
        for(int i = 1; i < receiverVictims.size(); ++i) {
            sendMail(new Mail(prank.getSenderVictim().getMailAddress(),
                              receiverVictims.get(i).getMailAddress(),
                              prank.getFakeMessage()));
        }
    }

    public void closeConnection() throws IOException {
        printWriter.write(SMTPProtocol.QUIT);
        clientSocket.close();
        bufferedReader.close();
        printWriter.close();

        LOG.log(Level.INFO, "Disconnected from server, bye");
    }
}
