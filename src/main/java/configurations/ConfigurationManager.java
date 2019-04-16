package configurations;

import model.Message;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationManager {

    private final static Logger LOG = Logger.getLogger(ConfigurationManager.class.getName());

    private static final String CRLF = "\r\n";
    private static final String MESSAGES_SEPARATOR = "==";
    private static final String PROPERTIES_DELIMITER = "=";
    private static final String CONFIG_PATH = "../config/";

    private String smtpServerAddress, witnessToCC;
    private Integer smtpServerPort, numberOfGroups;

    private List<String> victims;
    private List<Message> messages;

    public ConfigurationManager() {

        try {

            loadProperties();
            loadVictims();
            loadMessages();

            LOG.log(Level.INFO, "Configurations loaded from file");
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Unable to load configurations");
        }

    }

    public void loadProperties() throws IOException {

        FileReader fileReader = new FileReader(CONFIG_PATH + "configs.utf8");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String Line;
        while((Line = bufferedReader.readLine()) != null) {

            String Properties[] = Line.split(PROPERTIES_DELIMITER);

            if (Properties[0].equals("smtpServerAddress"))
                smtpServerAddress = Properties[1];
            else if (Properties[0].equals("smtpServerPort"))
                smtpServerPort = Integer.valueOf(Properties[1]);
            else if (Properties[0].equals("numberOfGroups"))
                numberOfGroups = Integer.valueOf(Properties[1]);
            else if (Properties[0].equals("witnessToCC"))
                witnessToCC = Properties[1];
        }

        bufferedReader.close();
        fileReader.close();
    }

    public void loadVictims() throws IOException {

        victims = new LinkedList<String>();
        FileReader fileReader = new FileReader(CONFIG_PATH + "victims.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String Line;
        while((Line = bufferedReader.readLine()) != null) {
            victims.add(Line);
        }

        bufferedReader.close();
        fileReader.close();
    }

    public void loadMessages() throws IOException {

        messages = new LinkedList<Message>();
        FileReader fileReader = new FileReader(CONFIG_PATH + "messages.utf8");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String Line;
        while((Line = bufferedReader.readLine()) != null) {
            String subject = "";
            String body = "";
            if(Line.startsWith("Subject:")) {
                subject = Line.substring(9);
            }

            while(!(Line = bufferedReader.readLine()).equals(MESSAGES_SEPARATOR)) {
                body += CRLF + Line;
            }

            messages.add(new Message(subject, body));
        }

        bufferedReader.close();
        fileReader.close();
    }

    public List<String> getVictims() { return victims; }
    public List<Message> getMessages() { return messages; }
    public String getSmtpServerAddress() { return smtpServerAddress; }
    public Integer getSmtpServerPort() { return smtpServerPort; }
    public Integer getNumberOfGroups() { return numberOfGroups; }
}
