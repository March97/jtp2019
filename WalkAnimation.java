package jtp2019;

import java.awt.Image;

import javax.swing.ImageIcon;

public class WalkAnimation extends Sprite{

	protected String[] images;
	private int counter;
	
	public WalkAnimation(int x, int y, String a0, String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13, String a14, String a15) {
		super(x, y);
		counter = 0;
		images = new String[16];
		loadAnimation(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
	}
	
	/*private void loadImage(String imageName, Image image) {
		
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();	
	}*/

	public void loadAnimation(String a0, String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13, String a14, String a15) {
		
		images[0] = a0;
		images[1] = a1;
		images[2] = a2;
		images[3] = a3;
		images[4] = a4;
		images[5] = a5;
		images[6] = a6;
		images[7] = a7;
		images[8] = a8;
		images[9] = a9;
		images[10] = a10;
		images[11] = a11;
		images[12] = a12;
		images[13] = a13;
		images[14] = a14;
		images[15] = a15;
	}
	
	public void animation(int dx, int dy) {
		
		int toReturn = 0;
		if(dy > 0 && dx == 0) {
			if(counter == 0) toReturn = 0;
			if(counter == 1) toReturn = 1;
			if(counter == 2) toReturn = 2;
			if(counter == 3) toReturn = 3;
		}
		if(dy < 0 && dx == 0) {
			if(counter == 0) toReturn = 12;
			if(counter == 1) toReturn = 13;
			if(counter == 2) toReturn = 14;
			if(counter == 3) toReturn = 15;
		}
		if(dx > 0) {
			if(counter == 0) toReturn = 8;
			if(counter == 1) toReturn = 9;
			if(counter == 2) toReturn = 10;
			if(counter == 3) toReturn = 11;
		}
		if(dx < 0) {
			if(counter == 0) toReturn = 4;
			if(counter == 1) toReturn = 5;
			if(counter == 2) toReturn = 6;
			if(counter == 3) toReturn = 7;
		}
		counter++;
		if(counter > 3)	counter = 0;
		
		loadImage(images[toReturn]);
	}
}
