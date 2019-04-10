package model;

import java.util.LinkedList;

/**
 * Class that implements a group
 * of Person
 */
public class Group {

    private LinkedList<Person> group;

    public Group() {
        //group = new LinkedList<>();
    }

    public void addPerson(Person person) {
        group.add(person);
    }
}
