/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Direction;
import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.actions.EntityCollectAction;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.PhoneBooth;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Pill;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;


/**
 * The Task for sheet 3.3
 * 
 * @author Tim-Julian Ehret
 */
public abstract class Task3_3 extends TaskWithHelperFunctions {

    protected final boolean fiusVariante = true;

    /**
     * The spinning neo
     */
    protected Neo neo;
    
    private boolean flag1;

    private List<Pill> pillsToCollect = new ArrayList<>();
    
    private Position goal;
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        
        this.neo = new Neo();
        
        sim.getPlayfield().addEntity(new Position(5, 5), this.neo);
        generatePath(sim.getPlayfield());
    }
    
    /**
     * generates a random path containing red pills the path will follow a "circle", e.g. neo should turn right 5 times
     * 
     * @param playfield
     *     the playfield on which the path is created
     */
    private final void generatePath(final Playfield playfield) {

        if (!fiusVariante) {
            //generate all instances of Pills which have to be collected and store them in the list
            for (int i = 0; i < 4; i++) {
                pillsToCollect.add(new Pill(Color.RED));
            }
            //neo will start at (5,5)
            //coord for placement
            int x = 5;
            int y = 5;
            //first steps
            x += getRandom();
            playfield.addEntity(new Position(x, y), pillsToCollect.get(0));
            //second step
            y += getRandom();
            playfield.addEntity(new Position(x, y), pillsToCollect.get(1));
            //third step
            x -= getRandom();
            playfield.addEntity(new Position(x, y), pillsToCollect.get(2));
            //last step
            y -= getRandom();
            playfield.addEntity(new Position(x, y), pillsToCollect.get(3));
            x += getRandom();
            playfield.addEntity(new Position(x, y), new PhoneBooth());
        } else {
            //first steps
            playfield.addEntity(new Position(8, 5), new Pill(Color.RED));
            //second step
            playfield.addEntity(new Position(8, 8), new Pill(Color.RED));
            //third step
            playfield.addEntity(new Position(5, 8), new Pill(Color.RED));
            playfield.addEntity(new Position(5, 7), new PhoneBooth());
            this.spawnEntity(new Wall(), 4, 7);
            this.spawnEntity(new Wall(), 4, 6);
            this.spawnEntity(new Wall(), 5, 6);
            this.spawnEntity(new Wall(), 6, 6);
            this.spawnEntity(new Wall(), 6, 7);
        }
    }
    
    private int getRandom() {
        return (int) (Math.random()*10)+2;
    }


    private Pill peakPill() {
        java.util.List<Pill> pills= sim.getPlayfield().getEntitiesOfTypeAt(neo.getPosition(), Pill.class, true);
        if(pills.size()>0) {
            return pills.get(0);
        }
        return null;
    }
    
    public abstract void solve();
    
    @Override
    public boolean verify() {
        if (!fiusVariante) {
            if (!neo.isOnPhoneBooth()) return false;
            if (neo.getInventory().get(Pill.class, false).size() != pillsToCollect.size()) return false;

            List<EntityCollectAction> collects = this.sim.getActionLog().getActionsOfType(EntityCollectAction.class, false);
            if (collects.size() != pillsToCollect.size()) return false;

            for (EntityCollectAction c : collects) {
                int pos = pillsToCollect.indexOf(c.getCollectedEntity());
                if (pos < 0) {
                    return false;
                } else {
                    pillsToCollect.remove(pos);
                }
            }

            return true;
        } else {
            return this.neo.getPosition().equals(new Position(5, 7));
        }
    }
    
}
