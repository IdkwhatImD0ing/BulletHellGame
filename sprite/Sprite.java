package sprite;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import game.Board;

/**Main Sprite class
 * @author Bill Zhang
 *
 */
public class Sprite {

    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;
    protected Board board;

    /**Constructor 
     * @param x position
     * @param y position
     * @param board board this is on
     */
    public Sprite(double x, double y, Board board) {

        this.x = x;
        this.y = y;
        this.board = board;
        visible = true;
    }

    /**Intializes the image
     * @param imageName the src of image
     */
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    
    /**Sets the image of this class to an image
     * @param image image to be set
     */
    protected void setImage(Image image) {
    	this.image = image;
    	getImageDimensions();
    }
    
    /**Sets the image
     * @param image image tos et
     */
    protected void loadImage(Image image) {
    	this.image = image;
    }
    
    /**Gets the image dimensions
     * 
     */
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }    

    /**Gets the image
     * @return image
     */
    public Image getImage() {
        return image;
    }

    /**Gets x position
     * @return x position
     */
    public double getX() {
        return x;
    }

    /**Gets y postion
     * @return y position
     */
    public double getY() {
        return y;
    }

    /**Checks if visible or not
     * @return true or false
     */
    public boolean isVisible() {
        return visible;
    }
    
    /**Gest the width
     * @return width
     */
    public int getWidth() {
		return width;
	}
    
    /**Gets the height
     * @return height
     */
    public int getHeight() {
		return height;
	}

    /**Sets visible or not
     * @param visible yes or no
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    /**Gets bounds
     * @return bounds
     */
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
    
    /**GEts board
     * @return board
     */
    public Board getBoard() {
    	return board;
    }
    
    /**Uses pot in player for pot classes
     * @param player player
     */
    public void usePot(Player player) {
    	return;
    }
    
    /**Equips the item
     * 
     */
    public void equipItem() {
    	return;
    }
    
    /**Gets type of item
     * @return type
     */
    public String getType() {
    	return "";
    }
    
    /**Gets the description
     * @return description
     */
    public String getInfo() {
    	return "";
    }
    
}
