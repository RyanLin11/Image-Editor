import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.image.BufferedImage;
/**
 * Used to make nearby pixels with similar colours transparent upon clicking.
 * 
 * @author Ryan Lin
 * @version 12.11
 */
public class MagicWand extends Cursor
{
    /**
     * Creates a MagicWand.
     */
    public MagicWand()
    {
        super("images/transparent.png");
    }
    
    /**
     * Act - do whatever the MagicWand wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        if(Greenfoot.mouseClicked(null)){
            if(location[0] >= 0 && location[1] >= 0){
                //if the user clicked on the image, make the surrounding pixels with similar colour transparent
                Processor.switchColor(background.image.getBufferedImage(), location[0], location[1], 0); 
                //save the current image and set the imageholder's image to be a copy of the current image
                background.updateChanges();
            }
        }
    }    
}
