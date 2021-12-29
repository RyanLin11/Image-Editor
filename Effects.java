import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * A clickable icon and dropdown list that allows the user to choose a special effect.
 * 
 * @author Ryan Lin
 * @version 12.05
 */
public class Effects extends Icon
{
    private ArrayList<EffectItem>effects;
    private boolean on = false;
    
    /**
     * Creates an Effects icon.
     * @param size The width and height of the Effects icon.
     */
    public Effects(int size)
    {
        super("Filters", "images/filter.png", size);
        effects = new ArrayList<>();
        effects.add(new Greyscale());
        effects.add(new Negative());
        effects.add(new Outline());
        effects.add(new Pixelate());
        effects.add(new Warm());
    }
    
    /**
     * Responds to button clicks.
     */
    protected void respond()
    {
        on = !on;
        if(on){
            int x = getX() - getImage().getWidth()/2, y = getY()+getImage().getHeight()/2;
            for(EffectItem effect: effects){
                y += effect.getImage().getHeight()/2;
                background.addObject(effect, x + effect.getImage().getWidth()/2, y);
                y += effect.getImage().getHeight()/2;
            }
        } else {
            for(EffectItem effect: effects){
                background.removeObject(effect);
            }
        }
    }
}
