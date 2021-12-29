import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A clickable icon that allows the user to rotate the image by 180 degrees.
 * 
 * @author Ryan Lin
 * @version 12.07
 */
public class Rotate180 extends Icon
{
    /**
     * Creates a Rotate180 icon.
     */
    public Rotate180(int size)
    {
        super("Rotate 180", "images/rotate180.png", size);
    }
    
    /**
     * Responds to button clicks.
     */
    protected void respond()
    {
        Processor.rotate180(background.image.getBufferedImage());
        background.updateChanges();
    }
}
