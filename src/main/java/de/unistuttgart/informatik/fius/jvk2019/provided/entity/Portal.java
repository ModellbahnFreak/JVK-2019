package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import de.unistuttgart.informatik.fius.icge.simulation.entity.BasicEntity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.CollectableEntity;
import de.unistuttgart.informatik.fius.jvk2019.Texture;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;

import java.io.PrintStream;

public class Portal  extends BasicEntity implements CollectableEntity {
    private Color color;

    /**
     * constructor for a new Pill
     * @param color the color of the Pill
     */
    public Portal(Color color) {
        this.color = color;
    }


    /**
     * @return the color of this pill
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    protected int getZPosition() {
        return 0;
    }


    @Override
    protected String getTextureHandle() {
        if(color == Color.BLUE) {
            return Texture.BLUEPORTAL.getHandle();
        }
        if(color == Color.RED) {
            return Texture.REDPORTAL.getHandle();
        }
        if(color == Color.GREEN) {
            return Texture.GREENPORTAL.getHandle();
        }
        return Texture.REDPILL.getHandle();
    }

    public boolean isOppositePortal(Portal p) {
        return p.color == this.color && p != this;
    }

    @Override
    public void serialize(PrintStream printStream) {
        switch (color) {
            case BLUE:
                printStream.print("Pill;BLUE");
                break;
            case GREEN:
                printStream.print("Pill;GREEN");
                break;
            case RED:
            default:
                printStream.print("Pill;RED");
                break;
        }
    }
}
