package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import de.unistuttgart.informatik.fius.icge.simulation.Direction;
import de.unistuttgart.informatik.fius.icge.simulation.Position;

public class MySmartNeo extends SmartNeo {

    public void moveSmart() {
        // TODO task 4.1 b) here
        if(checkSideWall()) {
            if (canMove()) {
                move();
            }
            else {
                turnCounterClockwise();
            }
        }
        else {
            turnClockWise();
            move();
        }
    }

    @Override
    public String toString() {
        return "MySmart (righthanded) Neo @" + getPosition();
    }

    public boolean checkSideWall() {
        // TODO task 4.1 a) here
        Position pos = this.getPosition();
        Position rightPos = pos.adjacentPosition(getLookingDirection().clockWiseNext());
        return getSimulation().getPlayfield().getEntitiesOfTypeAt(rightPos, Wall.class, true).size() > 0;
    }

}
