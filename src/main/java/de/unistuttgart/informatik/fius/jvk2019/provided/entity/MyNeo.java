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

/**
 * A child class of Neo. This class is to be implemented by the students.
 *
 * TODO: 2.2.b),c) add your author Tag and another one
 */
public class MyNeo extends Neo {

    private int age = 0;
    private int amountSteps = 0;
    
    /**
     * Default empty constructor
     */
    public MyNeo() {
        
    }
    
    /**
     * Creates a new MyNeo that has a certaint amount of coins.
     * 
     * @param starterCoins
     *     coin count that this neo starts with
     */
    public MyNeo(int starterCoins) {
        setCoinsInWallet(starterCoins);
        for (int i = 0; i < starterCoins; i++) {
            this.getInventory().add(new Coin());
        }
    }
    
    /**
     * Moves this Neo entity twice. Example implementation of a new operation.
     */
    public void moveTwice() {
        this.move();
        this.move();
    }

    /**
     * Command um Neo gegen den Uhrzeigersinn zu drehen.
     * Gelöst indem, sich Neo dreimal im Uhrzeigersinn dreht
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
     */
    @Override
    public void move() {
        super.move();
        amountSteps++;
        //Logger.simulation.append("Aktuelle Schrittzahl: " + steps);
    }

    /**
     * Moves Neo in any direction (relative to his current position) by the specified amount of Blocks. Teleports the player instantly and doesn't count towards the step count.
     * @param dx The Blocks to move in the x-Direction relative to his current position
     * @param dy The Blocks to move in the y-Direction relative to his current position
     */
    public void move(int dx, int dy) {
        this.getPlayfield().moveEntity(this, new Position(this.getPosition().getX()+dx, this.getPosition().getY()+dy));
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
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Der angegebene Adress-String hat nicht das Format '(x,y)'!");
            }
        }
    }

    /**
     * Sets the position of Neo on the playfield by his absolute x and y Coordinates. Teleports the Player instantly and doesn't count to the steps taken.
     * @param x The destination x-Position absolute to the origin
     * @param y The destination x-Position absolute to the origin
     */
    public void setAddress(int x, int y) {
        this.getPlayfield().moveEntity(this, new Position(x, y));
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
