import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A clickable icon that allows the user to reverse changes.
 * 
 * @author Ryan Lin
 * @version 12.05
 */
public class Redo extends Icon
{
    /**
     * Creates a Redo icon.
     */
    public Redo(int size)
    {
        super("Redo", "images/redo.png", size);
    }  
    
    /**
     * Responds to button clicks.
     */
    protected void respond(){
        background.imageIndex = Math.min(background.images.size()-1, background.imageIndex+1);
        background.updateDisplay();
    }
}
