package jtp2019;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Witch extends WalkAnimation {

	private int health;
	private final int attack = 30;
	private final int exp = 4000;
	private final int speed = 1;
	private int dx;
	private int lastFire;
	private List<Missile> missiles;
	private boolean toFire;
	
	public Witch (int x, int y, String a0, String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13, String a14, String a15) {
		super(x, y, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
		
		initWitch();
	}
	
	private void initWitch() {
		
		health = 4000;
		loadImage("src/resources/witch/witch-0-0.png");
		getImageDimensions();
		missiles = new ArrayList<>();
		toFire = false;
		lastFire = 520;
	}
	
	public void draw (Graphics2D g2d) {
		if(visible) {
			g2d.drawImage(getImage(), getX(), getY(), null);
			g2d.drawImage(getImage(), getX(), getY(), null);
			g2d.setColor(Color.RED);
			g2d.fillRect(getX(), getY() + 50, 33, 5);
			g2d.setColor(Color.CYAN);
			g2d.fillRect(getX(), getY() + 50, (int) (getHealth() / 120), 5);
		}
	}
	
	public void move() {
		
		if(x < 320)
			dx = 1;
		if(x > 725)
			dx = -1;

		x += dx * speed;
		animation(0, 1);
		updateToFire();
		if(toFire)
		{
			fire();
			lastFire = x;
			toFire = false;
		}
	}
	
	public void updateToFire() {
		
		if(Math.abs(lastFire - x) > 23)
			toFire = true;
	}
	
	public void fire() {
		missiles.add(new Missile(x, y, 0, 1, "src/resources/fireball/blueball.png"));
		missiles.add(new Missile(x, y, 1, 0, "src/resources/fireball/blueball.png"));
		missiles.add(new Missile(x, y, -1, 0, "src/resources/fireball/blueball.png"));
		missiles.add(new Missile(x, y, 1, 1, "src/resources/fireball/blueball.png"));
		missiles.add(new Missile(x, y, -1, 1, "src/resources/fireball/blueball.png"));
	}
	
	public List<Missile> getMissiles() {
		return missiles;
	}

	public int getHealth() {
		return health;
	}

	public int getAttack() {
		return attack;
	}

	public int getExp() {
		return exp;
	}
	
	public void getDamage(int damage) {
		health -= damage;
		if(health < 0)
			setVisible(false);
	}
}
