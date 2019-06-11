package jtp2019;

import java.awt.Graphics2D;

public class Missile extends Sprite {
	
	private final int MISSILE_SPEED = 5;
	private int dx;
	private int dy;
	
	
	public Missile(int x, int y, int dx, int dy, String image) {
		super(x, y);
		
		initMissile(dx, dy, image);
	}
	
	private void initMissile(int dx, int dy, String image) {
		
		this.dx = dx;
		this.dy = dy;
		loadImage(image);
		getImageDimensions();
	}
	
	public void draw(Graphics2D g2d) {
		if(visible)
			g2d.drawImage(getImage(), getX(), getY(), null);
	}

	public void move(int b_width, int b_height) {
		
		if(dx > 0) {
			x += MISSILE_SPEED;
		}
		if(dy > 0) {
			y += MISSILE_SPEED;
		}
		if(dx < 0) {
			x -= MISSILE_SPEED;
		}
		if(dy < 0) {
			y -= MISSILE_SPEED;
		}
		if(dy == 0 && dx == 0) {
			visible = false;
		}
		
		if ( x > b_width || y > b_height || x < 0 || y < 0) {
			visible = false;
		}
	}
}