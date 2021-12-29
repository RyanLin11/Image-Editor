import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * A bar that allows the user to select icons.
 * 
 * @author Ryan Lin 
 * @version 12.05
 */
public class Toolbar extends Actor
{
    private Background background;
    private ArrayList<Icon>icons;
    private int size = 32;
    private int margin = 10;
    
    /**
     * Creates a Toolbar
     */
    public Toolbar(){
        //Make the Toolbar white in colour
        GreenfootImage image = new GreenfootImage(800, size);
        image.setColor(Color.WHITE);
        image.fill();
        setImage(image);
        //Add icons into the ArrayList of icons
        icons = new ArrayList<>();
        icons.add(new Open(size));
        icons.add(new Save(size));
        icons.add(new Undo(size));
        icons.add(new Redo(size));
        icons.add(new FlipHorizontal(size));
        icons.add(new FlipVertical(size));
        icons.add(new Rotate90(size));
        icons.add(new Rotate180(size));
        icons.add(new Effects(size));
        icons.add(new Blur(size));
        icons.add(new Transparent(size));
        icons.add(new Draw(size));
        icons.add(new Crop(size));
    }
    
    /**
     * Display icons when the Toolbar is added to the world.
     * @param world The world to which the Toolbar is added to.
     */
    public void addedToWorld(World world)
    {
        background = (Background) world;
        int x = getX() - getImage().getWidth()/2, y = getY();
        //Add the icons into the world
        for(Icon icon: icons){
            x += icon.getImage().getWidth()/2;
            background.addObject(icon, x, y);
            x += icon.getImage().getWidth()/2;
            x += margin;
        }
    }  
}
