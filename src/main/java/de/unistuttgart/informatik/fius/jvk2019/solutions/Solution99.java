package de.unistuttgart.informatik.fius.jvk2019.solutions;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Portal;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task99;

public class Solution99 extends Task99 {

    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        this.player = new MyNeo();
        this.spawnEntity(this.player, 0, 0);
        this.spawnEntity(new Portal(Color.BLUE), 2, 0);
        this.spawnEntity(new Portal(Color.BLUE), 2, 5);
    }

    @Override
    public void solve() {

    }
}
