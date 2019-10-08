/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.solutions;

import de.unistuttgart.informatik.fius.icge.log.Logger;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task2_4;

import java.util.List;


/**
 * The solution for @see Task2_1, Sheet 2 Task 1
 * 
 * @author Stefan Zindl
 */
public class Solution2_4 extends Task2_4 {
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        this.playerRich = new MyNeo();
        this.playerPoor = new MyNeo();
        Logger.out.println("Poor" + playerPoor);
        Logger.out.println("Rich: " + playerRich);
        this.player3 = new MyNeo();
        this.player4 = new MyNeo();
        this.spawnEntity(this.playerRich, 2, 0);
        this.spawnEntity(this.playerPoor, 0, 0);
        this.spawnEntity(this.player3, 0, 3);
        this.spawnEntity(this.player4, 1, 3);
        this.playerRich.setCoinsInWallet(1000);
    }
    
    /**
     * 
     */
    public void solve() {
        Logger.out.println("Rich neo has " + playerRich.getCoinsInWallet());
        Logger.out.println("Poor neo has " + playerPoor.getCoinsInWallet());

        this.playerPoor.turnAround();
        this.playerRich.turnAround();
        this.playerPoor.turnAround();
        this.playerRich.turnAround();
        this.playerRich.dropCoin();
        this.playerRich.dropCoin();

        this.playerRich.move();
        this.playerRich.move();

        this.playerPoor.move();
        this.playerPoor.move();

        List<Coin> currentlyCollectableEntities = playerPoor.getCurrentlyCollectableEntities(Coin.class, true);
        for (Coin c : currentlyCollectableEntities) {
            playerPoor.collect(c);
        }

        Logger.out.println("Rich neo has " + playerRich.getCoinsInWallet());
        Logger.out.println("Poor neo has " + playerPoor.getCoinsInWallet());

        this.playerRich.setAddress("(5,5)");

        this.playerRich.move();

        this.playerRich.move(5,0);
    }
}
