import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A clickable icon that allows the user to flip the image horizontally.
 * 
 * @author Ryan Lin
 * @version 12.07
 */
public class FlipHorizontal extends Icon
{
    /**
     * Creates a FlipHorizontal icon.
     * @param size The width and height of the icon.
     */
    public FlipHorizontal(int size)
    {
        super("Horizontal Flip", "images/flip-horizontal.png", size);
    }
    
    /**
     * Responds to button clicks.
     */
    protected void respond()
    {
        Processor.flipHorizontal(background.image.getBufferedImage());
        background.updateChanges();
    }
}
