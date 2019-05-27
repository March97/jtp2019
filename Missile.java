package jtp2019;

public class Missile extends Sprite {
	
	
	private final int MISSILE_SPEED = 10;
	private int dx;
	private int dy;
	
	
	public Missile(int x, int y, int dx, int dy) {
		super(x, y);
		
		initMissile(dx, dy);
	}
	
	private void initMissile(int dx, int dy) {
		
		this.dx = dx;
		this.dy = dy;
		loadImage("src/resources/paladyn/fireball.png");
		getImageDimensions();
	}

	public void move(int b_width, int b_height) {
		
		if(dx > 0) {
			x += MISSILE_SPEED;
		}
		if(dy > 0) {
			y += MISSILE_SPEED;
		}
		if(dx < 0) {
			x -=MISSILE_SPEED;
		}
		if(dy < 0) {
			y -=MISSILE_SPEED;
		}
		if(dy == 0 && dx == 0) {
			visible = false;
		}
		
		if ( x > b_width || y > b_height || x < 0 || y < 0) {
			visible = false;
		}
	}
}