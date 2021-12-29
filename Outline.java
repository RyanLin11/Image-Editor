import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a button that allows access to the outline effect.
 * 
 * @author Ryan Lin
 * @version 12.11
 */
public class Outline extends EffectItem
{
    /**
     * Creates an Outline button.
     */
    public Outline()
    {
        super("Outline");
    }
    
    /**
     * Respond to button clicks.
     */
    protected void respond()
    {
        Processor.outline(background.image.getBufferedImage());
    }
}
