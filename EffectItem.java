import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A clickable button on a dropdown list.
 * 
 * @author Ryan Lin 
 * @version 12.11
 */
public abstract class EffectItem extends Actor
{
    /**
     * The margin
     */
    private int margin = 2;
    /**
     * The width and height of the EffectItem
     */
    private int width = 100, height = 20;
    /**
     * The Background world that the EffectItem belongs to
     */
    protected Background background;
    
    /**
     * Creates an item on a dropdown list.
     */
    public EffectItem(String text){
        //create a black background
        GreenfootImage back = new GreenfootImage(width + 2*margin, height + 2*margin);
        back.setColor(Color.BLACK);
        back.fill();
        //create a white foreground to make the black background appear as a border
        GreenfootImage front = new GreenfootImage(width, height);
        front.setColor(Color.WHITE);
        front.fill();
        //draw the EffectItem's name onto the button
        front.setColor(Color.BLACK);
        front.drawString(text, 0, height-6);
        back.drawImage(front, margin, margin);
        //set the actor's image
        setImage(back);
    }
    
    /**
     * Set the background world to be the current world.
     */
    public void addedToWorld(World world){
        background = (Background) world;
    }
    
     /**
     * Act - do whatever the EffectItem wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //if the mouse clicked on this effect button
        if(Greenfoot.mouseClicked(this)){
            respond(); //apply the effect
            background.updateChanges(); //save the current image on the stack and create a new image to work on
        }
    }   
    
    /**
     * Respond to button clicks
     */
    protected abstract void respond();
}
