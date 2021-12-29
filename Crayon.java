import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A tool that draws on the image.
 * 
 * @author Ryan Lin
 * @version 12.11
 */
public class Crayon extends Cursor
{
    /**
     * Creates a Crayon cursor.
     */
    public Crayon()
    {
        super("images/edit.png");
    }
    
    /**
     * Act - do whatever the Crayon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        if(Greenfoot.mouseDragged(this) && location[0] >= 0 && location[1] >= 0){
            //if the mouse is dragged on the image, draw a square of pixels at the mouse's current location
            Processor.draw(background.image.getBufferedImage(),location[0], location[1], 2, Processor.packagePixel(0,0,0,255));
        } else if(Greenfoot.mouseDragEnded(this)){
            //if the mouse dragging has ended, save the image and set the imageholder's image to be a copy of the current image
            background.updateChanges();
        }
    }    
}
