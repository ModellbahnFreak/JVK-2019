/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 *
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided;

import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeoProgram;


/**
 *
 */
public class LogicProgram extends MyNeoProgram {
    
    @Override
    public void run(final MyNeo neo) {
        boolean[] val = new boolean[4];
        for (int i = 0; i < 4; i++) {
            neo.move();
            val[i] = neo.coinsOnField() > 0;
        }
        neo.move();
        neo.move();
        if ((val[0] && val[1]) || ((!val[3]) && val[3] || val[1])) {
            neo.dropCoin();
        }
        while ((neo.canMove())) {
            neo.move();
        }
    }
    
}
