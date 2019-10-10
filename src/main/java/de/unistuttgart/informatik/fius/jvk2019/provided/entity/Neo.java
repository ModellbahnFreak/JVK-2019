/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 *
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.entity.CollectableEntity;
import de.unistuttgart.informatik.fius.icge.simulation.inspection.InspectionAttribute;
import de.unistuttgart.informatik.fius.icge.simulation.inspection.InspectionMethod;
import de.unistuttgart.informatik.fius.jvk2019.Texture;
import de.unistuttgart.informatik.fius.jvk2019.provided.exceptions.NeoIsBrokeException;
import de.unistuttgart.informatik.fius.jvk2019.provided.exceptions.NoCoinException;
import de.unistuttgart.informatik.fius.jvk2019.provided.exceptions.NoPillException;

import java.io.PrintStream;
import java.util.List;


/**
 * The Neo entity
 * 
 * @author Tim Neumann
 */
public class Neo extends Human {
    
    @Override
    protected String getTextureHandle() {
        return Texture.NEO.getHandle(this.getLookingDirection());
    }
    
    /**
     * collects a coin from the actual field
     * 
     * @throws NoCoinException
     *     when there is no coin
     */
    @InspectionMethod()
    public void collectCoin() {
        if (!this.canCollectCoin()) throw new NoCoinException();
        this.collect(this.getCurrentlyCollectableEntities(Coin.class, true).get(0));
    }

    /**
     * Collects all entities which are on the field Neo is currently standing on
     */
    public void collectOnField() {
        List<CollectableEntity> currentlyCollectableEntities = getCurrentlyCollectableEntities(CollectableEntity.class, true);
        for (CollectableEntity c : currentlyCollectableEntities) {
            collect(c);
        }
    }
    
    /**
     * drops a coin from Neo's inventory to the actual field
     * 
     * @throws NeoIsBrokeException
     *     when Neo is broken
     */
    @InspectionMethod()
    public void dropCoin() {
        if (!this.canDropCoin()) throw new NeoIsBrokeException();
        Coin dropped = this.getCurrentlyDroppableEntities(Coin.class, true).get(0);
        this.drop(dropped);
        sendCoinThroughPortal(dropped);
    }

    /**
     * Checks if there is a portal and if so, sends the coin through
     * @param dropped The coin which should be dropped through the portal
     */
    private void sendCoinThroughPortal(Coin dropped) {
        List<Portal> portals = this.getSimulation().getPlayfield().getEntitiesOfTypeAt(this.getPosition(), Portal.class, true);
        if (portals.size() > 0) {
            Portal first = portals.get(0);
            Portal destination = null;
            List<Portal> allPortals = this.getSimulation().getPlayfield().getAllEntitiesOfType(Portal.class, true);
            for (Portal p : allPortals) {
                if (first.isOppositePortal(p)) {
                    destination = p;
                }
            }
            if (destination != null) {
                this.getPlayfield().moveEntity(dropped, new Position(destination.getPosition().getX(), destination.getPosition().getY()));
            }
        }
    }

    /**
     * 
     * @return whether Neo can drop a coin
     */
    @InspectionAttribute
    public boolean canDropCoin() {
        return this.getCurrentlyDroppableEntities(Coin.class, true).size() > 0;
    }
    
    /**
     * 
     * @return whether there is a coin to collect
     */
    @InspectionAttribute
    public boolean canCollectCoin() {
        return this.getCurrentlyCollectableEntities(Coin.class, true).size() > 0;
    }
    
    /**
     * Add the amount of coins to the inventory
     * 
     * @param coins
     *     the amount of coins to set
     */
    protected void setCoins(int coins) {
        if (coins < 0) throw new IllegalArgumentException("Cannot set negative coin count!");
        int existing = this.getCoinsInWallet();
        if (existing < coins) {
            for (int i = existing; i < coins; i++) {
                this.getInventory().add(new Coin());
            }
        }
        if (existing > coins) {
            for (int i = existing; i > coins; i--) {
                this.getInventory().remove(this.getInventory().get(Coin.class, true).get(0));
            }
        }
    }
    
    /**
     * checks if neo is currently standing on a field that also contains a phone booth
     * 
     * @return true if neo stands on a field with a phone booth
     */
    @InspectionAttribute
    public Boolean isOnPhoneBooth() {
        if (this.getPlayfield().getEntitiesOfTypeAt(this.getPosition(), PhoneBooth.class, true).size() > 0) {
            return true;
        }
        return false;
    }
    
    /**
     * checks if neo is currently standing on a field that also contains a pill
     * 
     * @return if true it returns the pill, if false it return null
     */
    @InspectionMethod()
    public Pill peakPill() {
        List<Pill> pills = this.getCurrentlyCollectableEntities(Pill.class, true);
        if(pills.isEmpty()) return null;
        return pills.get(0);
    }
    
    /**
     * collects a pill from the actual field
     * 
     * @throws NoPillException
     *     when there is no pill
     */
    @InspectionMethod()
    public void collectPill() {
        if (this.peakPill() == null) throw new NoPillException();
        this.collect(this.getCurrentlyCollectableEntities(Pill.class, true).get(0));
    }
    
    /**
     * Gets the Balance of the current Neo. * Operation is to be implmented in MyNeo in Task 2.1.c) .
     * 
     * @return the balance
     */
    @SuppressWarnings("static-method")
    public int getBalance() {
        throw new UnsupportedOperationException("This Method is to be implemented in the MyNeo class.");
    }
    
    /**
     * Adds an amount of coins to the wallet of Neo. Operation is to be implmented in MyNeo in Task 2.1.d) .
     * 
     * @param amountOfCoins
     *     The amount to gain
     */
    @SuppressWarnings("static-method")
    public void gainCoins(int amountOfCoins) {
        throw new UnsupportedOperationException("This Method is to be implemented in the MyNeo class.");
    }
    
    /**
     * Helper Method that sets a fixed amount of coins for easier verification. Used in Verification of Task 2.1
     * 
     * @param amountOfCoins
     *     the new amount of coins
     */
    public void setCoinsInWallet(int amountOfCoins) {
        this.setCoins(amountOfCoins); //forwarding to real implementation
    }
    
    /**
     * @return the number of coins in neos wallet
     */
    @InspectionAttribute
    public int getCoinsInWallet() {
        return this.getInventory().get(Coin.class, true).size();
    }

    @Override
    public String toString() {
        return "Neo @" + getPosition();
    }

    @Override
    public void serialize(PrintStream ps) {
        ps.print("Neo;" + this.getLookingDirectionString());
    }
}
