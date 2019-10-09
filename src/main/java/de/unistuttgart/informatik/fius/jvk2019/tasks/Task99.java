package de.unistuttgart.informatik.fius.jvk2019.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Portal;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;

import java.util.List;

public abstract class Task99 extends TaskWithHelperFunctions {

    protected MyNeo player;

    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        this.player = new MyNeo(10);
        this.spawnEntity(this.player, 0, 0);
        this.spawnEntity(new Portal(Color.BLUE), 2, 0);
        this.spawnEntity(new Portal(Color.BLUE), 2, 5);
        this.spawnEntity(new Portal(Color.RED), 4, 0);
        this.spawnEntity(new Portal(Color.RED), 4, 5);

        this.spawnEntity(new Wall(), 8, 0);
        this.spawnEntity(new Wall(), -1, 0);
    }

    /**
     * Operation to initialize the second part of the simulation. Removes the first part and creates the second:
     * Creates a cage with walls
     * Places a random amount of randomly colores portals inside the cage
     * Moves neo into the cage
     */
    protected void generateSecondPart() {
        final Playfield field = this.sim.getPlayfield();
        List<Entity> entities = field.getAllEntities();
        for (Entity e : entities) {
            field.removeEntity(e);
        }

        for (int x = 0; x <= 10; x++) {
            field.addEntity(new Position(x, 7), new Wall());
            field.addEntity(new Position(x, 15), new Wall());
        }
        Wall remW = null;
        for (int y = 8; y < 15; y++) {
            if (y == 9) {
                remW = new Wall();
                field.addEntity(new Position(0, y), remW);
            } else {
                field.addEntity(new Position(0, y), new Wall());
            }
            field.addEntity(new Position(10, y), new Wall());
        }

        for (int y = 8; y < 15; y++) {
            for (int x = 1; x < 10; x++) {
                if (Math.random() > 0.5) {
                    double col = Math.random();
                    if (col < 0.3333) {
                        field.addEntity(new Position(x,y), new Portal(Color.RED));
                    } else if (col < 0.6666) {
                        field.addEntity(new Position(x,y), new Portal(Color.GREEN));
                    } else {
                        field.addEntity(new Position(x,y), new Portal(Color.BLUE));
                    }
                }
            }
        }

        field.addEntity(new Position(-1, 9), new Wall());
        field.removeEntity(remW);

        field.addEntity(new Position(0, 9), player);
    }

    @Override
    public boolean verify() {
        return true;
    }
}
