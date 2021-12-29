import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A clickable icon that allows users to access the Crop tool.
 * 
 * @author Ryan Lin
 * @version 12.07
 */
public class Crop extends Icon
{
    private Cropper cropper;
    
    /**
     * Creates a Crop icon.
     * @param size The width and height of the Crop icon.
     */
    public Crop(int size)
    {
        super("Crop", "images/crop.png", size);
        cropper = new Cropper();
    }
    
    /**
     * Responds to button clicks.
     */
    public void respond()
    {
        if(cropper.getWorld() == null){
            background.addObject(cropper, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
        }
    }
}
