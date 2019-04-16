package model;

import java.util.LinkedList;

/**
 * Class that implements a group
 * of Person
 */
public class Group {

    private LinkedList<Person> group;

    public Group() {
        group = new LinkedList<Person>();
    }

    public void add(Person person) {
        group.add(person);
    }

    public Person get(int index) { return group.get(index); }

    public int size() { return group.size(); }
}
