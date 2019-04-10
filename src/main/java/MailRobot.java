import model.Mail;
import smtp.SMTPClient;

import java.io.IOException;

public class MailRobot {

    public static void main(String[] args) throws IOException {

        String address = "localhost";
        int port = 25;

        SMTPClient client = new SMTPClient(address, port);

        Mail mail = new Mail("simon.jobin@heig-vd.ch",
                             "simon.jobin@heig-vd.ch",
                             "fake", "Bonjour");

        client.sendMail(mail);

        client.closeConnection();
    }
}
