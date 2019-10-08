package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import de.unistuttgart.informatik.fius.jvk2019.Texture;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;

public class Portal {
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
}
