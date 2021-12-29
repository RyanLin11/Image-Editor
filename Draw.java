import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A clickable icon that allows users to access the Draw tool.
 * 
 * @author Ryan Lin
 * @version 12.07
 */
public class Draw extends Icon
{
    private Crayon crayon;
    
    /**
     * Creates a Draw icon.
     * @param size The width and height of the Draw icon.
     */
    public Draw(int size)
    {
        super("Draw", "images/edit.png", size);
        crayon = new Crayon();
    }
    
    /**
     * Responds to button clicks.
     */
    protected void respond()
    {
        //if the crayon is not in the world, add it to the world
        if(crayon.getWorld() == null){
            background.addObject(crayon, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
        }
    }
}
