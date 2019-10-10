package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

public class SmartNeo extends MyNeo {

    public void moveSmart() {
        // TODO task 4.2 here
    }

    @Override
    public String toString() {
        return "Smart (righthanded) Neo @" + getPosition();
    }

    public boolean checkSideWall() {
        // TODO task 4.2 here
        return false;
    }

}
