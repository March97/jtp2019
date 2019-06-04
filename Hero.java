package jtp2019;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Hero extends WalkAnimation{

	private int dx;
	private int dy;
	private int lastDirectionX;
	private int lastDirectionY;
	private final int speed = 2;
	private List<Missile> missiles;
	private int potions;
	private int health;
	private int attack;

	public Hero(int x, int y, String a0, String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13, String a14, String a15) {
		super(x, y, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
		
		initHero();
	}
	
	private void initHero() {
		
		missiles = new ArrayList<>();
		potions = 0;
		health = 100;
		attack = 40;
		loadImage("src/resources/paladyn/pal1-0-0.png");
		getImageDimensions();
	}
	
	public void move(int b_width, int b_height) {
		
		if(dx != 0 || dy != 0) {
			lastDirectionX = dx;
			lastDirectionY = dy;
		}
		x += speed * dx;
		y += speed * dy;
		animation(dx, dy);
		
		if(x < 1) {
			x = 1;
		}
		if(x > b_width - width) {
			x = b_width - width;
		}
		if(y < 1) {
			y = 1;
		}
		if(y > b_height - 1.5 * height) {
			y = b_height - (int) (1.5 * height);
		}
		
	}
	
	public void stop() {
		
		if(dx > 0) {
    		x -= speed;
    	}
    	if(dx < 0) {
    		x += speed;
    	}
    	if(dy > 0) {
    		y -= speed;
    	}
    	if(dy < 0) {
    		y += speed;
    	}
	}

	public int getHealth() {
		return health;
	}

	public int getAttack() {
		return attack;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}
	
	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public void getDamage(int damage) {
		health -= damage;
	}

	public List<Missile> getMissiles() {
		return missiles;
	}
	
	public void addPotions() {
		potions++;
	}
	
	public void usePotions() {
		potions--;
		health += 50;
		if(health >= 100)
			health = 100;
	}
	
	public int getPotions() {
		return potions;
	}

	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE) {
			fire();
		}
		if(key == KeyEvent.VK_LEFT) {
			dx = -1;
		}
		if(key == KeyEvent.VK_RIGHT) {
			dx = 1;
		}
		if(key == KeyEvent.VK_UP) {
			dy = -1;
		}
		if(key == KeyEvent.VK_DOWN) {
			dy = 1;
		}
	}
	
	public void fire() {
		missiles.add(new Missile(x, y, lastDirectionX, lastDirectionY));
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if(key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		if(key == KeyEvent.VK_UP) {
			dy = 0;
		}
		if(key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}
		
}