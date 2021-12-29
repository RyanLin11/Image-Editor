import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
/**
 * <p> This image editor allows the user to open jpg and png files, 
 * apply rotations/flips/effects to it, and save it as a jpg or png file.</p>
 * 
 * @author Jordan Cohen, edited by Ryan Lin
 * @version November 2013, edited on December 2020
 */
public class Background extends World
{
    // Constants:
    private final String STARTING_FILE = "burano.png";

    // Objects and Variables:
    public ImageHolder image;
    public ArrayList<GreenfootImage>images;
    public int imageIndex = 0;

    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

        // Initialize buttons and the image
        image = new ImageHolder(STARTING_FILE);

        // Add objects to the screen
        addObject (image, 400, 316);
        
        //Create a toolbar at the top of the screen
        addObject(new Toolbar(), 400,  16);
        
        //Create the list of images and add the starting file to the list
        images = new ArrayList<>();
        images.add(new GreenfootImage(STARTING_FILE));
        
        //Make the background to be white
        GreenfootImage img = new GreenfootImage(800, 600);
        img.setColor(Color.WHITE);
        img.fill();
        setBackground(img);
    }
    
    /**
     * Adds an newest image to the list of saved images
     */
    public void updateChanges()
    {
        //while there are images in redo, remove them
        while(imageIndex < images.size()-1){
            images.remove(images.size()-1);
        }
        //add the current image to the list
        images.add(Processor.createGreenfootImageFromBi(image.getBufferedImage()));
        //increment the integer that represents the index of the current image being used in the list
        imageIndex++;
        //make a copy of the current image and set it to be the image holder's new image
        updateDisplay();
    }
    
    /**
     * Make a copy of the current image and set it to be the image holder's new image
     */
    public void updateDisplay()
    {
        image.setDisplayImage(Processor.deepCopy(images.get(imageIndex)));
    }
}

