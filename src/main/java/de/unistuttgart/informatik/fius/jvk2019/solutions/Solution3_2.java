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
import de.unistuttgart.informatik.fius.icge.simulation.Direction;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task3_2;


/** 
 * @author Lion Wagner
 */
public class Solution3_2 extends Task3_2 {
    
    @Override
    protected void turnRight() {
        Direction dir = neo.getLookingDirection();
        switch (dir) {
            case EAST:
                neo.setLookingDirectionByString("SOUTH");
                break;
            case SOUTH:
                neo.setLookingDirectionByString("WEST");
                break;
            case WEST:
                neo.setLookingDirectionByString("NOTRH");
                break;
            case NORTH:
                neo.setLookingDirectionByString("EAST");
                break;
        }
    }

    @Override
    public void solve() {
        while(!neo.isOnPhoneBooth()) {
            neo.move();
        }
        Logger.simulation.println("Solve end");
        // TODO 3.2.b) ,c) and d) write your code for exercise 
    }

    @Override
    protected void changeToD() {        
        this.boothsDestroyed = false; //TODO change this line for 3.2.d)
    }
}