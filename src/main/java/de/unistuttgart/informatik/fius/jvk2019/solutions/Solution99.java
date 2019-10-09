package de.unistuttgart.informatik.fius.jvk2019.solutions;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Portal;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task99;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class Solution99 extends Task99 {

    private int counter = 0;

    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
    }

    @Override
    public void solve() {
        /*while(this.player.canMove()) {
            this.player.move();
        }*/
        player.move();
        player.move();
        List<Portal> currentlyCollectableEntities = player.getCurrentlyCollectableEntities(Portal.class, true);
        for (Portal p : currentlyCollectableEntities) {
            player.collect(p);
        }
        player.move();
        player.move();
        player.dropCoin();
        player.move();
        for (Portal p : currentlyCollectableEntities) {
            player.drop(p);
        }
        player.move();
        player.turnAround();
        player.move();
        currentlyCollectableEntities = player.getCurrentlyCollectableEntities(Portal.class, true);
        for (Portal p : currentlyCollectableEntities) {
            player.collect(p);
        }
        player.move();
        player.move();
        player.turnAround();
        player.move();
        player.move();
        player.move();
        player.move();
        player.collectCoin();
        /*while (player.canMove()) {
            player.move();
        }*/

        generateSecondPart();

        /*while (!player.isOnPhoneBooth()) {
            while (player.canMove()) {
                player.move();
            }
            player.turnClockWise();
        }*/

        System.out.println("Run: " + this.counter);
        counter++;
        //new Exception("Stacktrace").printStackTrace();
    }
}
