import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A clickable icon that allows the user to rotate the image by 90 degrees.
 * 
 * @author Ryan Lin
 * @version 12.07
 */
public class Rotate90 extends Icon
{
    /**
     * Creates a Rotate90 icon.
     */
    public Rotate90(int size)
    {
        super("Rotate 90", "images/rotate90.png", size);
    }
    
    /**
     * Responds to button clicks.
     */
    protected void respond()
    {
        background.image.setDisplayImage(Processor.createGreenfootImageFromBi(Processor.rotate90(background.image.getBufferedImage())));
        background.updateChanges();
    }
}
