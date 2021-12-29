import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button to access the Greyscale effect.
 * 
 * @author Ryan Lin
 * @version 12.05
 */
public class Greyscale extends EffectItem
{
    /**
     * Creates a Greyscale button
     */
    public Greyscale()
    {
        super("Greyscale");
    } 
    
    /**
     * Respond to button clicks.
     */
    protected void respond()
    {
        Processor.greyscale(background.image.getBufferedImage());
    }
}
