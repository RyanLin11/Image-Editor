import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An image that follows a user's mouse.
 * 
 * @author Ryan Lin
 * @version 12.11
 */
public class Cursor extends Actor
{
    /**
     * The background that the cursor belongs to.
     */
    protected Background background;
    /**
     * Keeps track of time since the cursor was added to world.
     */
    private SimpleTimer timer;
    /**
     * Information about the mouse.
     */
    protected MouseInfo info;
    /**
     * The location of the mouse, relative to the image's coordinates.
     */
    protected int[]location;
    
    /**
     * Creates an image that follows the user's mouse location.
     * @param path The file path to an image's location.
     */
    public Cursor(String path)
    {
        GreenfootImage image = new GreenfootImage(path);
        timer = new SimpleTimer();
        image.scale(32, 32);
        setImage(image);
    }
    
    /**
     * Store reference to the world and mark the timer.
     */
    public void addedToWorld(World world)
    {
        background = (Background) world;
        timer.mark();
    }
    
    /**
     * Act - do whatever the Cursor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        info = Greenfoot.getMouseInfo();
        location = background.image.getRelativeMouseLocation();
        //if the mouse is in the world update the cursor's location to match the mouse's location
        if(info != null && info.getX() >= 0 && info.getX() < background.getWidth() && info.getY() >=0 && info.getY()<background.getHeight()){
            setLocation(info.getX(), info.getY());
        }
    }    
    
    /**
     * Gets the number of milliseconds since the Cursor was added into the world.
     * @return int milliseconds since the Cursor was added to the world.
     */
    public int millisElapsed()
    {
        return timer.millisElapsed();
    }
}
