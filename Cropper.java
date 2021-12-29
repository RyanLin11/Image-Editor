import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Crops the image from one corner to another corner of a rectangular section.
 * 
 * @author Ryan Lin 
 * @version 12.11
 */
public class Cropper extends Cursor
{
    private int x0, y0;
    /**
     * Creates a Cropper.
     */
    public Cropper()
    {
        super("images/cross.png");
    }
    
    /**
     * Action when the cropper is added to the world.
     */
    public void addedToWorld(World world)
    {
        super.addedToWorld(world);
        x0 = -1; y0 = -1;
    }
    
    /**
     * Act - do whatever the Cropper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        if(Greenfoot.mouseDragged(this)){
            if(x0 < 0 && y0 < 0){
                //if the mouse is dragged and there is no valid starting coordinate yet, initialize the current starting coordinate
                x0 = location[0]; y0 = location[1];
            }
        } else if(Greenfoot.mouseDragEnded(this)){
            //if the starting coordinates and ending coordinates are valid, crop the image and update changes
            if(x0 >= 0 && y0 >= 0 && location[0] >= 0 && location[1] >= 0){
                background.image.setDisplayImage(Processor.createGreenfootImageFromBi(Processor.crop(background.image.getBufferedImage(), x0, y0, location[0], location[1])));
                background.updateChanges();
                x0 = -1; y0 = -1;
            }
        }
    }    
}
