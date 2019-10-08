package de.unistuttgart.informatik.fius.jvk2019.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Portal;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;

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

    @Override
    public boolean verify() {
        return true;
    }
}
