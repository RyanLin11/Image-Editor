import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * A clickable icon that allows the user to open a file.
 * 
 * @author Ryan Lin
 * @version 12.05
 */
public class Open extends Icon
{
    /**
     * Creates an Open icon.
     */
    public Open(int size)
    {
        super("Open File", "images/open.png", size);
    }
    
    /**
     * Act - do whatever the Open wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)) respond();
    }    
    
    /**
     * Responds to button clicks.
     * @author Oracle https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
     */
    protected void respond()
    {
        //creates a file chooser window
        JFileChooser fc = new JFileChooser();
        //sets the default open location to be the folder that holds this project
        fc.setCurrentDirectory(new File("."));
        //sets the title of the window to be "Open"
        fc.setDialogTitle("Open");
        //set filters for extension
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "jpg","jpeg");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter("PNG", "png");
        fc.setFileFilter(filter);
        fc.setFileFilter(filter2);
        fc.setAcceptAllFileFilterUsed(false);
        //if the file is succesfully selected, open the file
        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            background.image.openFile(file.getAbsolutePath());
        }
    }
}
