import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A button that allows access to the Pixelate effect.
 * 
 * @author Ryan Lin
 * @version 12.11
 */
public class Pixelate extends EffectItem
{
    /**
     * Creates a Pixelate button.
     */
    public Pixelate()
    {
        super("Pixelate");
    }
    
    /**
     * Respond to button clicks.
     */
    protected void respond()
    {
        Processor.pixelate(background.image.getBufferedImage(), 5);//5
    }
}