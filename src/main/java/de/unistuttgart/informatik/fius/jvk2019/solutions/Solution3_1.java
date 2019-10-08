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

import de.unistuttgart.informatik.fius.jvk2019.tasks.Task3_1;

/**
 *
 * @author paulesn
 */
public class Solution3_1 extends Task3_1 {
    
    @Override
    public void solve() {
        //There is no need to do anything here
    }
    
    /**
     * The solution to exercise 1 (i) on worksheet 3
     * @param value
     * @return true if the value was in the given parameters
     */
    @Override
    public Boolean exI(int value) {
        if(value == 12345) {
            return true;
        }
        return false;
    }
    
    /**
     * The solution to exercise 1 (ii) on worksheet 3
     * @param value
     * @return true if the value was in the given parameters
     */
    @Override
    public Boolean exII(int value) {
        if(value <= 15) {//TODO exercise 1 (i)
            return true;
        }
        return false;
    }
    
    /**
     * The solution to exercise 1 (iii) on worksheet 3
     * @param value
     * @return true if the value was in the given parameters
     */
    @Override
    public Boolean exIII(int value) {
        if(value != 22) {//TODO exercise 1 (i)
            return true;
        }
        return false;
    }
    
    /**
     * The solution to exercise 1 (iv) on worksheet 3
     * @param value
     * @return true if the value was in the given parameters
     */
    @Override
    public Boolean exIV(int value) {
        if(value >= 1 && value <= 9) {//TODO exercise 1 (i)
            return true;
        }
        return false;
    }
    
    /**
     * The solution to exercise 1 (v) on worksheet 3
     * @param value
     * @return true if the value was in the given parameters
     */
    @Override
    public Boolean exV(int value) {
        if(value == 0) {//TODO exercise 1 (i)
            return true;
        }
        return false;
    }
    /**
     * The solution to exercise 1 (vi) on worksheet 3
     * @param value
     * @return true if the value was in the given parameters
     */
    @Override
    public Boolean exVI(int value) {
        if(value == 0) {//TODO exercise 1 (i)
            return true;
        }
        return false;
    }

    
}
