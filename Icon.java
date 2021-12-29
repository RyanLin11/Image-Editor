import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A clickable image that responds to button clicks.
 * 
 * @author Ryan Lin
 * @version 12.05
 */
public abstract class Icon extends Actor
{
    protected Background background;
    private Tooltip tip;
    
    /**
     * Creates an Icon that responds to button clicks.
     * @param text The name of the Icon.
     * @param path The file path to the Icon's image.
     * @param size The width and height of the Icon.
     */
    public Icon(String text, String path, int size)
    {
        GreenfootImage image = new GreenfootImage(path);
        image.scale(size, size);
        setImage(image);
        tip = new Tooltip(this, text);
    }
    
    /**
     * Store a reference to the Icon's world.
     */
    public void addedToWorld(World world)
    {
        background = (Background) world;
    }
    
    /**
     * Act - do whatever the Icon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //if the mouse clicked on this Icon, make the Icon do its special actions
        if(Greenfoot.mouseClicked(this)){
            respond();
        }
        //If a custom cursor is displayed and the user clicks on an Icon, make the cursor disappear
        Cursor cursor = (Cursor)getOneIntersectingObject(Cursor.class);
        if(Greenfoot.mouseClicked(null) && cursor != null && cursor.millisElapsed() >= 100){
            background.removeObject(cursor);
        }
        //get information about the mouse's actions and location
        MouseInfo info = Greenfoot.getMouseInfo();
        //get the boundaries of the image
        int l = getX() - getImage().getWidth()/2;
        int r = getX() + getImage().getWidth()/2;
        int u = getY() - getImage().getHeight()/2;
        int d = getY() + getImage().getHeight()/2;
        if(info != null){
            //if the mouse is currently hovering above an Icon
            if(info.getX() >= l && info.getX() <= r && info.getY() >= u && info.getY() <=d ){
                //if the object's tooltip is not yet in world add it, update its location if it is already in world 
                if(tip.getWorld() == null) getWorld().addObject(tip, info.getX()+30, info.getY()+30);
                else tip.setLocation(info.getX(), info.getY()+30);
            } else if(tip.getWorld() != null){
                //if the tooltip is in the world but the mouse is not hovering above its Icon, remove it from the world
                getWorld().removeObject(tip);
            }
        } else {
            getWorld().removeObject(tip);
        }
    } 
    
    /**
     * Respond to button clicks.
     */
    protected abstract void respond();
}
