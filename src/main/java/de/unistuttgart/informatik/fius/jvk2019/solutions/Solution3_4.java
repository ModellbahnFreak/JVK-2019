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
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Pill;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task3_4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author paulesn
 */
public class Solution3_4 extends Task3_4 {

    Queue<Pill> pills = new LinkedList<>();

    @Override
    public void solve() {
        boolean endOfField = false;
        while (!endOfField) {
            while (neo.canMove()) {
                Pill onField = neo.peakPill();
                //Logger.out.println(onField);
                pills.add(onField);
                if (onField != null) {
                    neo.collectPill();
                }
                neo.move();
            }
            Pill onField = neo.peakPill();
            //Logger.out.println(onField);
            pills.add(onField);
            if (onField != null) {
                neo.collectPill();
            }
            neo.turnClockWise();
            if (neo.canMove()) {
                neo.move();
                neo.turnClockWise();
                while (neo.canMove()) {
                    neo.move();
                }
                neo.turnClockWise();
                neo.turnClockWise();
            } else {
                endOfField = true;
            }
        }
        neo.turnClockWise();


        endOfField = false;
        while (!endOfField) {
            while (neo.canMove()) {
                Pill p = pills.remove();
                if (p != null) {
                    neo.drop(p);
                }
                neo.move();
            }
            Pill p = pills.remove();
            if (p != null) {
                neo.drop(p);
            }
            neo.turnClockWise();
            if (neo.canMove()) {
                neo.move();
                neo.turnClockWise();
                while (neo.canMove()) {
                    neo.move();
                }
                neo.turnClockWise();
                neo.turnClockWise();
            } else {
                endOfField = true;
            }
        }
    }

    public void printQueue() {
        for (Pill p : pills) {
            System.out.println(p);
        }
    }
}
