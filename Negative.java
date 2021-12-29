import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button to access the Negative effect.
 * 
 * @author Ryan Lin
 * @version 12.11
 */
public class Negative extends EffectItem
{
    /**
     * Creates a Negative button.
     */
    public Negative()
    {
        super("Negative");
    }
    
    /**
     * Responds to button clicks
     */
    public void respond()
    {
        Processor.negative(background.image.getBufferedImage());
    }
}
