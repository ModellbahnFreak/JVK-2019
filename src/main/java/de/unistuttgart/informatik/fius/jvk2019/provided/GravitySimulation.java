package de.unistuttgart.informatik.fius.jvk2019.provided;

import de.unistuttgart.informatik.fius.icge.simulation.*;
import de.unistuttgart.informatik.fius.icge.simulation.entity.BasicEntity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.icge.ui.AnimatedDrawable;
import de.unistuttgart.informatik.fius.icge.ui.Drawable;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implements a simple gravity system for Simulation
 *
 * @author Niklas Krieger
 */
public class GravitySimulation {

    /**
     * an entity which supports parallel movement
     * this is a wrapper for a normal Entity which is necessary for the simulation
     */
    private class MultiMovableEntity extends BasicEntity {

        private Entity entity;
        private boolean remove;
        private Drawable drawable;
        private Position startPos;
        private Position endPos;

        /**
         * default constructor
         * @param entity the Entity it represents
         * @param tickStart when does the animation begin?
         * @param fields how many fields should it move?
         * @param remove should entity be removed after animation?
         */
        private MultiMovableEntity(final Entity entity, final long tickStart, final int fields, final boolean remove) {
            this.entity = entity;
            this.remove = remove;
            this.startPos = entity.getPosition();
            this.endPos = new Position(startPos.getX(), startPos.getY() + fields);
            final boolean tilable = entity.getDrawInformation().isTilable();
            //set the drawable to a new AnimatedDrawable
            this.drawable = new AnimatedDrawable(tickStart,
                    startPos.getX(), startPos.getY(), SimulationClock.RENDER_TICKS_PER_SIMULATION_TICK * 2 * fields,
                    endPos.getX(), endPos.getY(), getZPosition(), entity.getDrawInformation().getTextureHandle()) {
                @Override
                public boolean isTilable() {
                    return tilable;
                }
            };
        }

        /**
         * starts the movement and removes entity to playfield
         * also adds itself to playfield
         */
        private void startMove() {
            simulation.getPlayfield().removeEntity(entity);
            simulation.getPlayfield().addEntity(startPos, this);
        }

        /**
         * starts the movement and re-adds entity to playfield
         * also removes itself from playfield
         */
        private void endMove() {
            simulation.getPlayfield().removeEntity(this);
            if(!remove) simulation.getPlayfield().addEntity(endPos, entity);
        }

        /**
         * get the Drawable
         * @return the Drawable
         */
        @Override
        public Drawable getDrawInformation() {
            return drawable;
        }

        @Override
        protected String getTextureHandle() {
            throw new UnsupportedOperationException();
        }

        @Override
        protected int getZPosition() {
            return 0;
        }
    }

    /**the Simulation on which the gravity actions are performed on*/
    private final Simulation simulation;

    /**the max amount of fields an Entity falls before it is removed*/
    private int maxFallLength = 20;

    public int getMaxFallLength() {
        return this.maxFallLength;
    }

    public void setMaxFallLength(int maxFallLength) {
        this.maxFallLength = maxFallLength;
    }

    /**
     * default constructor
     * @param simulation the Simulation on which the gravity actions are performed on
     */
    public GravitySimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     * simulates gravity
     */
    public void simulate() {
        final Playfield playfield = simulation.getPlayfield();
        final SimulationClock simulationClock = simulation.getSimulationClock();
        //final list with all the entities on the playfield
        //this should normally return a new list, so there is no need to olone the list
        final List<Entity> entities = playfield.getAllEntities();
        //list with all the movable entities
        final ArrayList<MultiMovableEntity> multiMovableEntities = new ArrayList<>(entities.size());
        if (entities.size() > 0) {
            //sort the entities
            entities.sort(new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    int r1 = Integer.compare(o1.getPosition().getX(), o2.getPosition().getX());
                    if (r1 != 0) return r1;
                    else return Integer.compare(o1.getPosition().getY(), o2.getPosition().getY());
                }


            });
            //add dummy entity
            entities.add(new BasicEntity() {
                @Override
                protected String getTextureHandle() {
                    return null;
                }

                @Override
                protected int getZPosition() {
                    return 0;
                }

                @Override
                public Position getPosition() {
                    return new Position(Integer.MAX_VALUE, 0);
                }
            });
            //debug
            entities.stream().map(Entity::getPosition).forEachOrdered(System.out::println);
            int maxFields = 0;
            int columnStartIndex = 0;
            int column = entities.get(0).getPosition().getX();
            int i = 0;
            final long tickStart = simulationClock.getLastRenderTickNumber();
            while (i < entities.size()) {
                final Entity entity = entities.get(i);
                if (entity.getPosition().getX() == column) {
                    //nothing to do here
                }
                else {
                    //handle column
                    int lastSolidY = Integer.MAX_VALUE;
                    int placedOnField = 0;
                    Class type = null;
                    for (int i2 = i - 1; i2 >= columnStartIndex; i2--) {
                        final Entity e = entities.get(i2);
                        if (e instanceof Wall) {
                            //do NOT move
                            lastSolidY = e.getPosition().getY();
                        }
                        else {
                            //move
                            int dif = lastSolidY - e.getPosition().getY() - 1;
                            if (dif <= getMaxFallLength()) {
                                //check if it can position on the same field
                                if (type == null) {
                                    type = e.getClass();
                                    placedOnField = 1;
                                }
                                else if (type == e.getClass() && placedOnField < 9) {
                                    placedOnField++;
                                }
                                else {
                                    dif--;
                                    lastSolidY--;
                                    type = e.getClass();
                                    placedOnField = 1;
                                }
                                multiMovableEntities.add(new MultiMovableEntity(e, tickStart, dif, false));
                            }
                            else {
                                dif = getMaxFallLength();
                                multiMovableEntities.add(new MultiMovableEntity(e, tickStart, dif, true));
                            }
                            maxFields = Math.max(dif, maxFields);
                        }
                    }
                    //handle next
                    columnStartIndex = i;
                    column = entity.getPosition().getX();
                }
                i++;
            }
            System.out.println("maxfields: " + maxFields);
            //start simulation
            multiMovableEntities.forEach(MultiMovableEntity::startMove);
            CompletableFuture endOfOperation1 = new CompletableFuture();

            try {
                simulationClock.scheduleOperationInTicks(maxFields , endOfOperation1);

            } finally {
                endOfOperation1.complete((Object)null);
            }

            CompletableFuture endOfOperation2 = new CompletableFuture();

            try {
                simulationClock.scheduleOperationInTicks(maxFields, endOfOperation2);

                multiMovableEntities.forEach(MultiMovableEntity::endMove);
            } finally {
                endOfOperation2.complete((Object)null);
            }
        }

    }
}
