import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A clickable icon that allows the user to save the current image in a directory.
 * 
 * @author Ryan Lin
 * @version 12.05
 */
public class Save extends Icon
{
    /**
     * Creates a button to save the current image.
     */
    public Save(int size)
    {
        super("Save As", "images/save.png", size);
    }
    
    /**
     * Respond to button clicks.
     * @author Oracle https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
     * @author Mr.Cohen code snippet from Image Manipulation Assignment Handout
     */
    protected void respond()
    {
        //creates a file chooser
        JFileChooser fc = new JFileChooser();
        //set default directory to the directory holding this project
        fc.setCurrentDirectory(new File("."));
        //set the title to be "Save As"
        fc.setDialogTitle("Save As");
        
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG", "png");
        FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("JPG", "jpg");
        fc.setFileFilter(pngFilter);
        fc.setFileFilter(jpgFilter);
        fc.setAcceptAllFileFilterUsed(false);
        
        //if the user presses save in the window
       if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            try{
                //try to write the image to the selected file
                BufferedImage bi = background.image.getBufferedImage();
                if(jpgFilter.accept(file)){
                    //make an image without the alpha value
                    BufferedImage jpgbi = Processor.deepCopy(bi, BufferedImage.TYPE_INT_RGB);
                    //write that image into the correct file
                    ImageIO.write(jpgbi, "jpg", file);
                } else if(pngFilter.accept(file)){
                    //write 
                    ImageIO.write(bi, "png", file);
                } else {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Error: Invalid format or no file extension found. Remember to include .jpg or .png after the name of the file.");
                }
            } catch(IOException e){
                System.out.println("Error: Image writing failed.");
            }
        }
    }
}
