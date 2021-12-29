import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A clickable icon that allows the user to grab a transparency wand.
 * 
 * @author Ryan Lin 
 * @version 12.07
 */
public class Transparent extends Icon
{
    private MagicWand wand = new MagicWand();
    
    /**
     * Creates a Transparent icon.
     */
    public Transparent(int size)
    {
        super("Transparent", "images/transparent.png", size);
    }
    
    /**
     * Responds to button clicks.
     */
    protected void respond(){
        if(wand.getWorld() == null){
            background.addObject(wand, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
        }
    }
}
