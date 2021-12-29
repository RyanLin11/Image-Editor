import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A text message that is displayed when the user hovers on top of the corresponding icon.
 * 
 * @author Ryan Lin
 * @version 12.07
 */
public class Tooltip extends Actor
{
    private Icon icon;
    
    /**
     * Creates a Tooltip.
     * @param icon The icon that the Tooltip corresponds to.
     * @param text The text for the Tooltip to display.
     */
    public Tooltip(Icon icon, String text)
    {
        this.icon = icon;
        //set the background as black
        GreenfootImage image = new GreenfootImage(100, 20);
        image.setColor(Color.BLACK);
        image.fill();
        //set the foreground as white
        GreenfootImage inner = new GreenfootImage(98, 18);
        inner.setColor(Color.WHITE);
        inner.fill();
        //draw the text onto the button
        inner.setColor(Color.BLACK);
        inner.drawString(text, 1, 13);
        image.drawImage(inner, 1, 1);
        //set the image
        setImage(image);
    }   
}
