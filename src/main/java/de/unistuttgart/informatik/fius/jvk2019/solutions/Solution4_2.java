package de.unistuttgart.informatik.fius.jvk2019.solutions;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MySmartNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.SmartNeo;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task4_2;

import java.util.concurrent.CompletableFuture;

public class Solution4_2 extends Task4_2 {
    @Override
    public void prepare(Simulation arg0) {
        super.prepare(arg0);

        // TODO solve 4.1 c) here
        // Use the 'this.neo' attribute to solve this task
        this.neo = new MySmartNeo();
        spawnEntity(neo, 0, 0);
    }

    @Override
    public void solve() {
        // TODO solve 4.1 c) here
        while (!neo.isOnPhoneBooth()) {
            neo.moveSmart();
        }
    }
}
