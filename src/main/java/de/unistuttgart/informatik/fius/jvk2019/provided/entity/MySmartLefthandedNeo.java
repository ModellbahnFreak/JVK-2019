package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import de.unistuttgart.informatik.fius.icge.simulation.Direction;
import de.unistuttgart.informatik.fius.icge.simulation.Position;

public class MySmartLefthandedNeo extends SmartNeo {
    @Override
    public String toString() {
        return "MySmart (lefthanded) Neo @" + getPosition();
    }

    public void moveSmart() {
        if (!this.checkSideWall()) {
            turnCounterClockwise();
            move();
        } else if (canMove()) {
            move();
        } else {
            turnClockWise();
        }
    }

    public boolean checkSideWall() {
        Direction left = null;
        switch (this.getLookingDirection()) {
            case EAST:
                left = Direction.NORTH;
                break;
            case SOUTH:
                left = Direction.EAST;
                break;
            case WEST:
                left = Direction.SOUTH;
                break;
            case NORTH:
                left = Direction.WEST;
                break;
        }
        return this.getSimulation().getPlayfield().getEntitiesOfTypeAt(this.getPosition().adjacentPosition(left), Wall.class, true).size() > 0;
    }
}
