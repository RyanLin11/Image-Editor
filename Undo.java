import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A clickable icon that allows the user to undo changes.
 * 
 * @author Ryan Lin
 * @version 12.05
 */
public class Undo extends Icon
{
    /**
     * Creates an Undo button.
     */
    public Undo(int size)
    {
        super("Undo", "images/undo.png", size);
    }
    
    /**
     * Responds to button clicks.
     */
    protected void respond()
    {
        background.imageIndex = Math.max(0, background.imageIndex-1);
        background.updateDisplay();
    }
}
