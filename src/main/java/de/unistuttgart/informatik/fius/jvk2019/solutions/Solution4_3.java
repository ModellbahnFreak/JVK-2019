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

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Pill;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task4_3;

import java.util.ArrayList;
import java.util.Collections;


/**
 * The solution for Task4_3
 *
 * @author Fabian Bühler
 */
public class Solution4_3 extends Task4_3 {
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        this.player = new MyNeo();
        this.spawnEntity(this.player, 0, 0);
    }
    
    @Override
    public void solve() {
        // Help neo escape the matrix. First neo needs to collect the pill.
        // If the pill is blue he has to sort the coin stacks from lowest (left)
        // to highest (right).
        // If he found a red pill he needs to reverse the order of the coin stacks.
        // When he finishes this task the phone will ring and he can escape the 
        // matrix by using it (beeing on the same tile).
        MyNeo neo = this.player;
        //check pill
        Pill pill = neo.peakPill();
        neo.collectPill();
        boolean reversed = pill.getColor() == Color.BLUE;
        ArrayList<Integer> amountCoins = new ArrayList<>();
        while (neo.canMove()) {
            neo.move();
            int amount = 0;
            while (neo.canCollectCoin()) {
                amount++;
                neo.collectCoin();
            }
            amountCoins.add(amount);
        }
        //sort with the reversed order
        amountCoins.sort(Integer::compareTo);
        if (reversed) Collections.reverse(amountCoins);
        //turn neo
        neo.turnAround();
        for (int amount : amountCoins) {
            for (int x = 0; x < amount; x++) {
                neo.dropCoin();
            }
            neo.move();
        }
        neo.turnAround();
        while (neo.canMove()) {
            neo.move();
        }

        //System.out.println("return");
    }
    
}
