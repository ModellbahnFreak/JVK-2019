package de.unistuttgart.informatik.fius.jvk2019.solutions;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.Main;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MySmartLefthandedNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MySmartNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.SmartNeo;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task4_2;

import java.util.concurrent.CompletableFuture;

public class Solution4_2 extends Task4_2 {
    private int task = 0;

    @Override
    public void prepare(Simulation arg0) {
        super.prepare(arg0);

        if (Main.host.getTaskRegistry().getTask("Task4_1") == this) {
            this.neo = new MySmartNeo();
            task = 1;
        } else {
            this.neo = new MySmartLefthandedNeo();
            task = 2;
        }
        spawnEntity(neo, 0, 0);
    }

    @Override
    public void solve() {
        while (!neo.isOnPhoneBooth()) {
            neo.moveSmart();
        }
    }
}
