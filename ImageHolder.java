import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

/**
 * Simple class that serves to be an Actor to display the image.
 * 
 * (Revised 11/14 to avoid crashing if user cancels import operation).
 * 
 * @author Jordan Cohen
 * @version November 2013, revised
 */
public class ImageHolder extends Actor
{
    private GreenfootImage imageToDisplay; 
    private int x = 0, y = 0;

    /**
     * Construct an ImageHolder with a file name. If there is an error, 
     * show a blank GreenfootImage.
     * 
     * @param fileName  Name of image file to be displayed.
     */
    public ImageHolder (String fileName)
    {
        openFile (fileName);
    }

    /**
     * Attempt to open a file and assign it as this Actor's image
     * 
     * @param fileName  Name of the image file to open (must be in this directory)
     * @return boolean  True if operation successful, otherwise false
     */
    public boolean openFile (String fileName)
    {
        try {
            if (fileName != null)
            {
                imageToDisplay = new GreenfootImage (fileName);
                setImage(imageToDisplay);
            }
            else
                return false;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
        return true;
    }

    /**
     * Allows access to my awtImage - the backing data underneath the GreenfootImage class.
     * 
     * @return BufferedImage returns the backing image for this Actor as an AwtImage
     */
    public BufferedImage getBufferedImage ()
    {
        return this.getImage().getAwtImage();
    }
    
    /**
     * Sets the display image of the ImageHolder
     */
    public void setDisplayImage(GreenfootImage image)
    {
        if(image != null){
            this.imageToDisplay = image;
            setImage(imageToDisplay);
        }
    }
    
    /**
     * Updates the coordinate of the mouse's position relative to the top left corner of the image.
     */
    public void act()
    {
        MouseInfo info = Greenfoot.getMouseInfo();
        int l = getX() - getImage().getWidth()/2;
        int r = getX() + getImage().getWidth()/2;
        int u = getY() - getImage().getHeight()/2;
        int d = getY() + getImage().getHeight()/2;
        if(info != null && l <= info.getX() && info.getX() < r && u <= info.getY() && info.getY() < d){
            this.x = info.getX() - l;
            this.y = info.getY() - u;
        } else {
            this.x = -1;
            this.y = -1;
        }
    }
    
    /**
     * Gets the mouse's position, relative to the top left of the image.
     * @return int[] The zeroth index stores the x-coordinate, the first index stores the y-coordinate.
     */
    public int[] getRelativeMouseLocation()
    {
        return new int[]{this.x, this.y};
    }
}
