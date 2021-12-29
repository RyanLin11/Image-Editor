import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A clickable icon that allows users to access the Blur effect.
 * 
 * @author Ryan Lin
 * @version 12.07
 */
public class Blur extends Icon
{
    /**
     * Creates a Blur icon.
     * @param size the width and height of the Blur icon.
     */
    public Blur(int size)
    {
        super("Blur", "images/blur.png", size);
    }
    
    /**
     * Responds to button clicks.
     */
    protected void respond()
    {
        Processor.blur(background.image.getBufferedImage(), 2);
        background.updateChanges();
    }
}
