import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A button that allows access to the Warm effect.
 * 
 * @author Ryan Lin
 * @version 12.11
 */
public class Warm extends EffectItem
{
    /**
     * Creates a Warm button.
     */
    public Warm()
    {
        super("Warmer");
    }
    
    /**
     * Respond to button clicks.
     */
    protected void respond()
    {
        Processor.warm(background.image.getBufferedImage());
    }
}
