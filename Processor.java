import java.awt.image.BufferedImage;
import java.util.Queue;
import java.util.LinkedList;
import greenfoot.*;
import java.awt.Graphics2D;

/**
 * Starter code for Processor - the class that processes images.
 * <p>
 * This class manipulated Java BufferedImages, which are effectively 2d arrays
 * of pixels. Each pixel is a single integer packed with 4 values inside it.
 * <p>
 * I have included two useful methods for dealing with bit-shift operators so
 * you don't have to. These methods are unpackPixel() and packagePixel() and do
 * exactly what they say - extract red, green, blue and alpha values out of an
 * int, and put the same four integers back into a special packed integer. 
 * 
 * <h1>Ryan's Methods</h1>
 * <h2>I have included several new features:</h2>
 * <ul>
 * <li>Image flipping</li>
 * <li>Image rotation</li>
 * <li>Blurring image</li>
 * <li>Transparency wand</li>
 * <li>Drawing tool</li>
 * <li>Special effects</li>
 * </ul>
 * @author Jordan Cohen
 * @version November 2013
 */
public class Processor  
{
    /**
     * the four directions that specify the neighbour of a pixel
     */
    private static final int[][]dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    /**
     * Flips the image horizontally.
     * 
     * @param bi The BufferedImage (passed by reference) to change.
     */
    public static void flipHorizontal (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        for(int y = 0; y < ySize; y++){
            for(int x = 0; x < xSize; x++){
                newBi.setRGB(xSize-x-1, y, bi.getRGB(x, y));
            }
        }
        //write the pixels in the temp image into the original image
        for(int y = 0; y < ySize; y++){
            for(int x = 0; x < xSize; x++){
                bi.setRGB(x, y, newBi.getRGB(x, y));
            }
        }
    }
    
    /**
     * Flips the image vertically.
     * 
     * @param bi The BufferedImage (passed by reference) to change.
     */
    public static void flipVertical(BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);
        //write image from left to right in bi and right to left in newBi
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                newBi.setRGB(x, ySize-y-1, bi.getRGB(x, y));
            }
        }
        //copy the pixels from newBi into bi
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                bi.setRGB(x, y, newBi.getRGB(x, y));
            }
        }
    }
    
    /**
     * Turns the image into greyscale.
     * @author TutorialsPoint for providing the formula
     * 
     * @param bi The BufferedImage (passed by reference) to change.
     */
    public static void greyscale(BufferedImage bi)
    {
        for(int x = 0; x < bi.getWidth(); x++){
            for(int y = 0; y < bi.getHeight(); y++){
                //unpack the rgba values using helper function
                int[]rgbValues = unpackPixel(bi.getRGB(x, y));
                //apply formula to determine the brightness of the rgba colour
                int value = (int)Math.round(0.3*rgbValues[1] + 0.59*rgbValues[2] + 0.11*rgbValues[3]);
                //change RGB values to shades of gray, and leave the alpha value the same
                bi.setRGB(x, y, packagePixel(value,value,value, rgbValues[0]));
            }
        }
    }
    
    /**
     * Turns every RGB value into its inverse (255 - value).
     * 
     * @param bi The BufferedImage (passed by reference) to change.
     */
    public static void negative(BufferedImage bi)
    {
        for(int x = 0; x < bi.getWidth(); x++){
            for(int y = 0; y < bi.getHeight(); y++){
                //unpack the pixel at (x, y)
                int[]rgbValues = unpackPixel(bi.getRGB(x, y));
                //turn every red, green, and blue component into its inverse
                for(int i = 1; i<rgbValues.length; i++){
                    rgbValues[i] = 255 - rgbValues[i];
                }
                //set the color at (x, y) to the new color
                bi.setRGB(x, y, packagePixel(rgbValues[1],rgbValues[2],rgbValues[3],rgbValues[0]));
            }
        }
    }
    
    /**
     * Rotates the image by 180 degrees.
     * 
     * @param bi The BufferedImage (passed by reference) to change.
     */
    public static void rotate180(BufferedImage bi){
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        //create a temp image to hold rotated rgba values
        BufferedImage newBi = new BufferedImage(xSize, ySize, 3);
        //transform the location of pixels from bi to newBi
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                newBi.setRGB(xSize-x-1,ySize-y-1,bi.getRGB(x,y));
            }
        }
        //write the pixels from newBi back into bi
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                bi.setRGB(x, y, newBi.getRGB(x, y));
            }
        }
    }
    
    /**
     * Rotates the image by 90 degrees.
     * 
     * @param bi The BufferedImage (passed by reference) to change.
     * @return BufferedImage The rotated BufferedImage.
     */
    public static BufferedImage rotate90(BufferedImage bi){
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        //create a temp image to hold rotated rgba values
        BufferedImage newBi = new BufferedImage(ySize, xSize, 3);
        //transform the location of pixels from bi to newBi
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                newBi.setRGB(ySize - y - 1, x, bi.getRGB(x,y));
            }
        }
        //return the new image
        return newBi;
    }
    
    /**
     * Blurs the image by replacing each pixel's rgba value with the average of its neighbours' rgba values.
     * 
     * @param bi The BufferedImage (passed by reference) to change.
     * @param degree The dimensions of the averaging box.
     */
    public static void blur(BufferedImage bi, int degree){
        //blur using averaging method of neighbouring cells
        //Using Prefix Sum Arrays for O(1) 2D sum query
        int[][][]psa = new int[bi.getWidth()][bi.getHeight()][4];
        for(int x = 0; x<psa.length; x++){
            for(int y = 0; y<psa[x].length; y++){
                int[]rgba = unpackPixel(bi.getRGB(x, y));
                for(int z = 0; z<psa[x][y].length; z++){
                    psa[x][y][z] = rgba[z];
                }
            }
        }
        //sum numbers up in the y direction
        for(int x = 0; x<psa.length; x++){
            for(int y = 1; y<psa[x].length; y++){
                for(int z = 0; z<psa[x][y].length; z++){
                    psa[x][y][z] += psa[x][y-1][z];
                }
            }
        }
        //sum numbers up in the x direction
        for(int x = 1; x<psa.length; x++){
            for(int y = 0; y<psa[x].length; y++){
                for(int z = 0; z<psa[x][y].length; z++){
                    psa[x][y][z] += psa[x-1][y][z];
                }
            }
        }
        //query to construct new image
        for(int x = 0; x<psa.length; x++){
            for(int y = 0; y<psa[x].length; y++){
                int[]rgba = new int[psa[x][y].length];
                for(int z = 0; z<psa[x][y].length; z++){
                    int x1 = Math.max(0,x-degree-1), y1 = Math.max(0,y-degree-1);
                    int x2 = Math.min(psa.length-1, x+degree), y2 = Math.min(psa[x].length-1, y+degree);
                    // psa[x1][y1][z] stores the sum of the values of color z in the rectangular area from (0, 0) to (x1, y1), the top left corner
                    // psa[x1][y2][z] stores the sum of the values of color z in the rectangular area from (0, 0) to (x1, y2), the bottom left corner
                    // psa[x2][y1][z] stores the sum of the values of color z in the rectangular area from (0, 0) to (x2, y1), the top right corner
                    // psa[x2][y2][z] stores the sum of the values of color z in the rectangular area from (0, 0) to (x2, y2), the bottom right corner
                    // this formula can be used to calculate the sum of the values of color z in the rectangular area from (x1, y1) to (x2, y2)
                    // and divide it by the number of pixels in that rectangular area, to yield the average
                    rgba[z] = (psa[x2][y2][z]-psa[x1][y2][z]-psa[x2][y1][z]+psa[x1][y1][z])/((x2-x1)*(y2-y1));
                }
                bi.setRGB(x, y, packagePixel(rgba[1], rgba[2], rgba[3], rgba[0]));
            }
        }
    }
    
    /**
     * Changes the color of pixels with similar rgba values in a localized region to a desired color.
     * 
     * @param bi The BufferedImage (passed by reference) to change.
     * @param x1 The x-coordinate of the origin pixel.
     * @param y1 The y-coordinate of the origin pixel.
     * @param desired The rgba value of the replacement color.
     */
    public static void switchColor(BufferedImage bi, int x1, int y1, int desired)
    {
        //Use Breadth-First Search to locate adjancent pixels to be converted to the desired color
        //Use queue to store pixels to be considered
        Queue<Integer[]>queue = new LinkedList<>();
        //track pixels that are already visited to prevent cycles
        boolean[][]vis = new boolean[bi.getWidth()][bi.getHeight()];
        //offer the current (starting) coordinate
        queue.offer(new Integer[]{x1, y1});
        //the starting coordinate is visited by default
        vis[x1][y1] = true;
        //while there are still pixels to be considered
        while(!queue.isEmpty()){
            Integer[]coord = queue.poll();
            //check all four directions (up, down, left, right) to see if they are similar enough to the color at the starting coordinate
            for(int i = 0; i<dir.length; i++){
                int x2 = coord[0] + dir[i][0], y2 = coord[1] + dir[i][1];
                //if the neighbouring coordinate is within the bounds of the image and are similar to the color at the starting coordinate
                if(x2 >= 0 && x2 < bi.getWidth() && y2 >= 0 && y2 < bi.getHeight() && !vis[x2][y2] && similar(bi.getRGB(x2, y2), bi.getRGB(x1, y1))){
                    //mark it as visited
                    vis[x2][y2] = true;
                    //set the color to the desired color
                    bi.setRGB(x2, y2, desired);
                    //consider this pixel's neighbouring pixels in future iterations
                    queue.offer(new Integer[]{x2, y2});
                }
            }
        }
    }
    
    /**
     * Detects if two colors are similar by comparing its rgb values using the distance formula.
     * @param rgb1 The rgb value of the first color.
     * @param rgb2 The rgb value of the second color.
     */
    private static boolean similar(int rgb1, int rgb2){
        int diff = 0;
        int[]unpack1 = unpackPixel(rgb1);
        int[]unpack2 = unpackPixel(rgb2);
        //add the squares of the differences between the two color's rgb values
        for(int i = 1; i<unpack1.length; i++){
            diff += (int)Math.pow(unpack1[i] - unpack2[i], 2);
        }
        //if that number is less than or equal to 3000, they are similar
        return diff <= 3000;
    }
    
    /**
     * Exaggerates pixels with the greatest contrast with its surroundings.
     * Credits to https://ai.stanford.edu/~syyeung/cvweb/tutorial1.html for giving me the idea to subtract blurred colors from original to find well-defined outlines.
     * 
     * @param bi The BufferedImage (passed by reference) to change.
     */
    public static void outline(BufferedImage bi){
        BufferedImage copy = deepCopy(bi, 3);
        blur(copy, 18);
        //subtract the blurred colors from the original colors to yield well-defined edges
        for(int x = 0; x<bi.getWidth(); x++){
            for(int y = 0; y<bi.getHeight(); y++){
                int[]rgba1 = unpackPixel(bi.getRGB(x, y));
                int[]rgba2 = unpackPixel(copy.getRGB(x, y));
                int[]rgba = new int[rgba1.length];
                for(int z = 0; z<rgba1.length; z++){
                    rgba[z] = Math.min(255,Math.abs(rgba1[z] - rgba2[z])*10);
                }
                bi.setRGB(x, y,packagePixel(rgba[1], rgba[2], rgba[3], 100));
            }
        }
        //convert brighter pixels to black outlines
        for(int x = 0; x<bi.getWidth(); x++){
            for(int y = 0; y<bi.getHeight(); y++){
                int sum = 0;
                int[]rgba = unpackPixel(bi.getRGB(x, y));
                //sum up the rgb values
                for(int z = 1; z<4; z++){
                    sum += rgba[z];
                }
                //if the pixel is bright enough, display it
                //if the pixel is not bright enough, make it transparent
                if(sum >= 400){
                    int avg = (sum / 3) * 2;
                    bi.setRGB(x, y, packagePixel(avg, avg, avg, 255));
                } else {
                    bi.setRGB(x, y, 0);
                }
            }
        }
    }
    
    /**
     * Draws a filled square on the image.
     * 
     * @param bi The BufferedImage (passed by reference) to change.
     * @param x1 The x-coordinate of the square's center.
     * @param y1 The y-coordinate of the square's center.
     * @param degree Half of the square's size.
     * @param rgba The rgba value of the draw color.
     */
    public static void draw(BufferedImage bi, int x1, int y1, int degree, int rgba){
        //fills a square from (x1-degree, y1-degree) to (x1+degree, y1+degree) with the color specified by rgba.
        for(int x=Math.max(0,x1-degree);x<=Math.min(bi.getWidth()-1,x1+degree);x++){
            for(int y=Math.max(0,y1-degree);y<=Math.min(bi.getHeight()-1,y1+degree);y++){
                bi.setRGB(x, y, rgba);
            }
        }
    }
    
    /**
     * Takes a rectangular subsection of an image and saves it to a new image.
     * 
     * @param bi The original BufferedImage (passed by reference).
     * @param x1 The x-coordinate of one of the subsection's corners.
     * @param y1 The y-coordinate of one of the subsection's corners.
     * @param x2 The x-coordinate of one of the subsection's corners.
     * @param y2 The y-coordinate of one of the subsection's corners.
     * @return BufferedImage The new cropped image.
     */
    public static BufferedImage crop(BufferedImage bi, int x1, int y1, int x2, int y2){
        int xmin = Math.min(x1, x2), xmax = Math.max(x1, x2);
        int ymin = Math.min(y1, y2), ymax = Math.max(y1, y2);
        //create a new image that is the size of the rectangular subsection cropped by the user
        BufferedImage newBi = new BufferedImage(xmax-xmin+1, ymax-ymin+1, 3);
        //adds the pixels from bi into newBi
        for(int x = xmin; x<=xmax; x++){
            for(int y = ymin; y<=ymax; y++){
                newBi.setRGB(x-xmin, y-ymin, bi.getRGB(x, y));
            }
        }
        //return the new image
        return newBi;
    }
    
    /**
     * Lowers the resolution of the image by combining neighbouring pixels into a larger pixel.
     * @param bi The BufferedImage (passed by reference) to change.
     * @param degree The width of the transformed pixel, in pixels.
     */
    public static void pixelate(BufferedImage bi, int degree){
        for(int x = 0; x<bi.getWidth(); x++){
            for(int y = 0; y<bi.getHeight(); y++){
                //if (x, y) is the top left corner of the larger, transformed pixel
                if(x%degree == 0 && y%degree == 0){
                    //find the sum of all the pixels in the larger, transformed pixel
                    int[]rgba = new int[4];
                    for(int i = x; i<Math.min(bi.getWidth(),x+degree); i++){
                        for(int j = y; j<Math.min(bi.getHeight(),y+degree); j++){
                            int[]rgba1 = unpackPixel(bi.getRGB(i, j));
                            for(int k = 0; k<rgba1.length; k++){
                                rgba[k] += rgba1[k];
                            }
                        }
                    }
                    //divide it by the size of the larger, transformed pixel to find the average rgba values
                    for(int k = 0; k<rgba.length; k++){
                        rgba[k] = rgba[k] / ((Math.min(bi.getWidth(),x+degree)-x)*(Math.min(bi.getHeight(),y+degree)-y));
                    }
                    //change the bi's values
                    bi.setRGB(x, y, packagePixel(rgba[1], rgba[2], rgba[3], rgba[0]));
                } else {
                    //if it is not in the top left corner of the larger, transformed pixel, take the value of the top left corner
                    bi.setRGB(x, y, bi.getRGB((x/degree)*degree, (y/degree)*degree));
                }
            }
        }
    }
    
    /**
     * Makes the image warmer by emphasizing warmer colours.
     * @param bi The BufferedImage (passed by reference) to change.
     */
    public static void warm(BufferedImage bi){
        for(int x = 0; x<bi.getWidth(); x++){
            for(int y = 0; y<bi.getHeight(); y++){
                //unpack the pixel at (x, y)
                int[]rgba = unpackPixel(bi.getRGB(x, y));
                //increase red and green components to a maximum of 250, and decrease blue component to a minimum of 10
                rgba[1] = Math.min(250, rgba[1]+2);
                rgba[2] = Math.min(250, rgba[2]+2);
                rgba[3] = Math.max(10, rgba[3]-2);
                //set the pixel color at (x, y) to the new color
                bi.setRGB(x, y, packagePixel(rgba[1], rgba[2], rgba[3], rgba[0]));
            }
        }
    }
    
    /**
     * Copies a BufferedImage's rgba values into a new image and returns it.
     * @param bi The BufferedImage (passed by reference) to be copied.
     * @param type The type of the BufferedImage to be returned
     * @param BufferedImage The copy of the original BufferedImage.
     */
    public static BufferedImage deepCopy(BufferedImage bi, int type){
        BufferedImage newBi = new BufferedImage(bi.getWidth(), bi.getHeight(), type);
        //loops through the colors of the original BufferedImage and copies it into a new image
        for(int x = 0; x<bi.getWidth(); x++){
            for(int y = 0; y<bi.getHeight(); y++){
                newBi.setRGB(x, y, bi.getRGB(x, y));
            }
        }
        //return the new image
        return newBi;
    }
    
    /**
     * Takes in a GreenfootImage and returns a deep copy of the GreenfootImage
     * 
     * @param gi The GreenfootImage to be copied.
     * @return GreenfootImage A copy of the GreenfootImage.
     */
    public static GreenfootImage deepCopy(GreenfootImage gi){
        //create a new BufferedImage to be written on
        BufferedImage newBi = new BufferedImage(gi.getWidth(), gi.getHeight(), 3);
        //Get gi's BufferedImage
        BufferedImage oldBi = gi.getAwtImage();
        //Copy the RGB values from oldBi to newBi
        for(int x = 0; x<gi.getWidth(); x++){
            for(int y = 0; y<gi.getHeight(); y++){
                newBi.setRGB(x, y, oldBi.getRGB(x, y));
            }
        }
        //return the new BufferedImage as a GreenfootImage
        return createGreenfootImageFromBi(newBi);
    }
    
    /**
     * Takes in a BufferedImage and returns a GreenfootImage
     * @author Jordan Cohen
     * 
     * @param newBi The BufferedImage to convert.
     * @return GreenfootImage A GreenfootImage built from the Buffered provided.
     */
    public static GreenfootImage createGreenfootImageFromBi(BufferedImage newBi)
    {
        GreenfootImage returnImage = new GreenfootImage(newBi.getWidth(), newBi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(newBi, null, 0, 0);
        return returnImage;
    }
    
    /**
     * Takes in an rgb value - the kind that is returned from BufferedImage's
     * getRGB() method - and returns 4 integers for easy manipulation.
     * 
     * By Jordan Cohen
     * Version 0.2
     * 
     * @param rgbaValue The value of a single pixel as an integer, representing<br>
     *                  8 bits for red, green and blue and 8 bits for alpha:<br>
     *                  <pre>alpha   red     green   blue</pre>
     *                  <pre>00000000000000000000000000000000</pre>
     * @return int[4]   Array containing 4 shorter ints<br>
     *                  <pre>0       1       2       3</pre>
     *                  <pre>alpha   red     green   blue</pre>
     */
    public static int[] unpackPixel (int rgbaValue)
    {
        int[] unpackedValues = new int[4];
        // alpha
        unpackedValues[0] = (rgbaValue >> 24) & 0xFF;
        // red
        unpackedValues[1] = (rgbaValue >> 16) & 0xFF;
        // green
        unpackedValues[2] = (rgbaValue >>  8) & 0xFF;
        // blue
        unpackedValues[3] = (rgbaValue) & 0xFF;

        return unpackedValues;
    }

    /**
     * Takes in a red, green, blue and alpha integer and uses bit-shifting
     * to package all of the data into a single integer.
     * 
     * @param   int red value (0-255)
     * @param   int green value (0-255)
     * @param   int blue value (0-255)
     * @param   int alpha value (0-255)
     * 
     * @return int  Integer representing 32 bit integer pixel ready
     *              for BufferedImage
     */
    public static int packagePixel (int r, int g, int b, int a)
    {
        int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
        return newRGB;
    }
}
