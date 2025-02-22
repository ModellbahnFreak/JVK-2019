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

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task2_2;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;


/**
 * The solution for Task 2.2d)
 * 
 * @author Lion Wagner
 */
public class Solution2_2 extends Task2_2 {
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        
        //create myNeo with 6 coins
        this.myNeo = new MyNeo(6);
        
        //add myNeo to the playing field
        sim.getPlayfield().addEntity(new Position(0, 0), this.myNeo);
    }
    
    /**
     * Solve Task 2.2d here
     */
    @Override
    public void solve() {
        this.myNeo.dropCoin();
        for (int i = 0; i < 5; i++) {
            this.myNeo.move();
            this.myNeo.dropCoin();
        }
    }
}
