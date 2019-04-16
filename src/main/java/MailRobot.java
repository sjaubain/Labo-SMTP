import configurations.ConfigurationManager;
import model.Mail;
import model.PrankGenerator;
import smtp.SMTPClient;

import java.io.IOException;

public class MailRobot {

    public static void main(String[] args) throws IOException {

        ConfigurationManager cm = new ConfigurationManager();
        PrankGenerator pg = new PrankGenerator(cm);
        SMTPClient client = new SMTPClient(cm.getSmtpServerAddress(), cm.getSmtpServerPort());

        for(int i = 0; i < cm.getNumberOfGroups(); ++i) {
            client.handlePrank(pg.generatePrank());
        }

        client.closeConnection();
    }
}
