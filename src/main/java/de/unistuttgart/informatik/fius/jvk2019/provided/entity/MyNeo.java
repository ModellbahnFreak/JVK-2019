/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Inventory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A child class of Neo. This class is to be implemented by the students.
 *
 * @author GR
 * @version b00.12.34
 */
public class MyNeo extends Neo {

    private int age = 0;
    private int amountSteps = 0;
    private List<Portal> passedPortals = new ArrayList<Portal>();
    
    /**
     * Default empty constructor
     */
    public MyNeo() {
        
    }
    
    /**
     * Creates a new MyNeo with a certain amount of coins in his wallet.
     * 
     * @param starterCoins
     *     coin count that this neo starts with
     */
    public MyNeo(int starterCoins) {
        setCoinsInWallet(starterCoins);
    }
    
    /**
     * Moves this Neo entity twice. Example implementation of a new operation.
     */
    public void moveTwice() {
        this.move();
        this.move();
    }

    /**
     * Turns Neo counter clockwise. Operation is to be implemented in MyNeo in Task 2.1.a)
     */
    public void turnCounterClockwise() {
        this.turnClockWise();
        this.turnClockWise();
        this.turnClockWise();
    }

    /**
     * Command um Neo um 180° zu drehen.
     * Erreicht indem Neo zweimal im Uhrzeigersinn gedreht wird. Nutzt die turnClockWise-Methode von Neo
     */
    public void turnAround() {
        this.turnClockWise();
        this.turnClockWise();
    }

    /**
     * Returns the amount of money Neo currently has in his inventory
     * @return The amount of money in his inventory; 2Money/Coin
     */
    @Override
    public int getBalance() {
        return 2*this.getInventory().get(Coin.class, true).size();
    }

    /**
     * Adds the given amount of coins to thqe inventory of Neo. Gets the Inventory Object of the Greedy Entity and adds the given amount of new coins to it.
     * @param amountOfCoins The amount of coins which shoud be added to Neos inventory
     */
    @Override
    public void gainCoins(int amountOfCoins) {
        Inventory inventory = this.getInventory();
        for(int i = 0; i < amountOfCoins; i++) {
            inventory.add(new Coin());
        }
    }

    /**
     * Moves Neo none field into his looking direction. Counts the number of steps, which neo takes
     * If there is a portal on the current spot, the Player is ported through
     */
    @Override
    public void move() {
        super.move();
        amountSteps++;
        goThroughPortalIfAvailable();
        //Logger.simulation.append("Aktuelle Schrittzahl: " + steps);
    }

    /**
     * Moves Neo in any direction (relative to his current position) by the specified amount of Blocks. Teleports the player instantly and doesn't count towards the step count.
     * If there is a portal on the current spot, the Player is ported through
     * @param dx The Blocks to move in the x-Direction relative to his current position
     * @param dy The Blocks to move in the y-Direction relative to his current position
     */
    public void move(int dx, int dy) {
        this.getPlayfield().moveEntity(this, new Position(this.getPosition().getX()+dx, this.getPosition().getY()+dy));
        goThroughPortalIfAvailable();
    }

    /**
     * Looks if tere is a portal on the currend Field. If there is, a Portal of the corresponding color is searched. If found, Neo is teleported through to the other side
     */
    public void goThroughPortalIfAvailable() {
        List<Portal> portals = this.getSimulation().getPlayfield().getEntitiesOfTypeAt(this.getPosition(), Portal.class, true);
        if (portals.size() > 0) {
            Portal first = portals.get(0);
            Portal destination = null;
            Portal[] properPortals = getSimulation().getPlayfield().getAllEntitiesOfType(Portal.class, true).stream()
                    .filter(portal -> portal.isOppositePortal(first)).toArray(Portal[]::new);
            destination = properPortals[new Random().nextInt(properPortals.length)];
            if (destination != null) {
                this.getPlayfield().moveEntity(this, new Position(destination.getPosition().getX(), destination.getPosition().getY()));
            }
        }
    }

    /**
     * Returns the number of steps Neo has already taken forward. Backward staps don't count
     * @return The number of steps Neo has taken
     */
    public int getSteps() {
        return amountSteps;
    }

    /**
     * Sets the step counter to the specified amount.
     * @param steps The number of steps the step counter should be set to
     */
    public void setSteps(int steps) {
        this.amountSteps = steps;
    }

    /**
     * Returns the number of coins Neo currently has in his inventory. Equivalent to the method "geCoinsInWallet"
     * @return The number of coins which are currently in Neo's inventory
     */
    public int getAmountCoins() {
        return this.getCoinsInWallet();
    }

    /**
     * Set the amount of Coins Neo has in his inventory. Equivalent to the Mehthod "setCoins"
     * @param coins The Amount of Coins Neo should have in his inventory
     */
    public void setAmountCoins(int coins) {
        this.setCoins(coins);
    }

    /**
     * Returns the current address of Neo on the Playfield. Unit: coordinates
     * @return Coordinates of neo on the Playfield as String in the form: (x,y)
     */
    public String getAddress() {
        return "(" + this.getPosition().getX() + "," + this.getPosition().getY() + ")";
    }

    /**
     * Sets the Address/Position of Neo on the Playfield with a String in the form (x,y). Teleports Neo instantly to this spot and doesn't count to the step counter
     * If there is a portal on the current spot, the Player is ported through
     * @param addr The destination Coordinates of neo on the Playfield absolute to the origin in the form "(x,y)"
     */
    public void setAddress(String addr) {
        String[] coord = addr.substring(1,addr.length()-1).split(",");
        if (coord.length == 2) {
            int[] coordI = new int[2];
            try {
                coordI[0] = Integer.parseInt(coord[0]);
                coordI[1] = Integer.parseInt(coord[1]);
                this.getPlayfield().moveEntity(this, new Position(coordI[0], coordI[1]));
                goThroughPortalIfAvailable();
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Der angegebene Adress-String hat nicht das Format '(x,y)'!");
            }
        }
    }

    /**
     * Sets the position of Neo on the playfield by his absolute x and y Coordinates. Teleports the Player instantly and doesn't count to the steps taken.
     * If there is a portal on the current spot, the Player is ported through
     * @param x The destination x-Position absolute to the origin
     * @param y The destination x-Position absolute to the origin
     */
    public void setAddress(int x, int y) {
        this.getPlayfield().moveEntity(this, new Position(x, y));
        goThroughPortalIfAvailable();
    }

    /**
     * Returns the age of Neo in ticks. Always returns 0 at the moment due to inaccessability of timing code
     * @return Always 0; soon to be: Age of player in ticks
     */
    @Deprecated
    public int getAge() {
        return age;
    }
    
}
