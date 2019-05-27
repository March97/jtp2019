package jtp2019;

import java.awt.*;
import javax.swing.*;

public class Sprite {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean visible;
	protected Image image;
	
	public Sprite(int x, int y) {
		
		this.x = x;
		this.y = y;
		visible = true;
	}
	
	protected void loadImage(String imageName) {
		
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();	
	}
	
	protected void getImageDimensions() {
		
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}	
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}