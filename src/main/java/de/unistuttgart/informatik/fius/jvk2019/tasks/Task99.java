package de.unistuttgart.informatik.fius.jvk2019.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;

public abstract class Task99 extends TaskWithHelperFunctions {

    protected MyNeo player;

    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
    }

    @Override
    public boolean verify() {
        return true;
    }
}
