import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A clickable icon that allows the user to flip images vertically.
 * 
 * @author Ryan Lin
 * @version 12.07
 */
public class FlipVertical extends Icon
{
    /**
     * Creates a FlipVertical icon.
     * @param size The width and height of the icon.
     */
    public FlipVertical(int size)
    {
        super("Vertical Flip", "images/flip-vertical.png", size);
    }
    
    /**
     * Responds to button clicks.
     */
    protected void respond()
    {
        Processor.flipVertical(background.image.getBufferedImage());
        background.updateChanges();
    }
}
