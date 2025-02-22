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

import de.unistuttgart.informatik.fius.jvk2019.provided.Color;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Pill;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task3_3b;

/**
 *
 * @author Sebastian "Zottel92" Grund
 */
public class Solution3_3b extends Task3_3b {
    
    @Override
    public void solve() {
        while (!neo.isOnPhoneBooth()) {
            while (neo.peakPill() == null && !neo.isOnPhoneBooth()) {
                neo.move();
            }
            if (!neo.isOnPhoneBooth()) {
                if (neo.peakPill().getColor() == Color.RED) {
                    neo.turnClockWise();
                } else {
                    neo.turnCounterClockwise();
                }
                neo.collectOnField();
            }
        }
    }
    
}
