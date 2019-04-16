package model;

import configurations.ConfigurationManager;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class that generate pranks
 */
public class PrankGenerator {

    private ConfigurationManager configurationManager;
    private int nbVictims, nbGroups, peoplePerGroup;
    private List<String> victimsMailAdresses;
    private List<Message> messages;
    private static int MIN_GROUP_SIZE = 3;

    /* Needed to form groups splitting the victims list */
    private int nbGeneratedPranks;

    public PrankGenerator(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;

        victimsMailAdresses = configurationManager.getVictims();
        nbVictims = victimsMailAdresses.size();
        nbGroups = configurationManager.getNumberOfGroups();
        peoplePerGroup = nbVictims / nbGroups;
        messages = configurationManager.getMessages();

        Collections.shuffle(victimsMailAdresses);
        nbGeneratedPranks = 0;
    }

    public Prank generatePrank() {

        try {

            Random random = new Random();

            if (peoplePerGroup < MIN_GROUP_SIZE) {
                throw new Exception("not enough victims or too much groups");
            }

            Group victims = new Group();
            for(int i = 1; i < peoplePerGroup; ++i) {
                victims.add(new Person(victimsMailAdresses.get(
                        (nbGeneratedPranks * peoplePerGroup) + i)));
            }

            this.nbGeneratedPranks++;

            return new Prank(victims.get(0), victims, messages.get(Math.abs(random.nextInt() % messages.size())));

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
