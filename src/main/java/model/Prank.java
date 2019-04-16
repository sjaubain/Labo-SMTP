package model;

public class Prank {

    private Person senderVictim;
    private Group receiverVictims;
    private Message fakeMessage;

    public Prank(Person senderVictim, Group receiverVictims, Message fakeMessage) {
        this.senderVictim = senderVictim;
        this.receiverVictims = receiverVictims;
        this.fakeMessage = fakeMessage;
    }

    public Person getSenderVictim() { return senderVictim; }

    public Group getReceiverVictims() { return receiverVictims; }

    public Message getFakeMessage() { return fakeMessage; }
}
