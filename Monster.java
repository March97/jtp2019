package jtp2019;

public class Monster extends WalkAnimation {
	
	private int health;
	private final int attack = 60;
	private final int exp = 50;
	
	public Monster (int x, int y, String a0, String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13, String a14, String a15) {
		super(x, y, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
		
		initMonster();
	}

	private void initMonster() {
		
		health = 100;
		loadImage("src/resources/monster/monster-0-0.png");
		getImageDimensions();
	}
	
	public void move(int dx, int dy, int b_width, int b_height) {
		
		int animationX = 0;
		int animationY = 0;
		
		if(x < 0) {
			x = 0;
		}
		if(x > b_width - width * 1.35) {
			x = b_width - (int) (width * 1.35);
		}
		if(y < height / 3) {
			y = height / 3;
		}
		if(y > b_height - height * 1.9) {
			y = b_height - (int) (height * 1.9);
		}
		
		if(dx > x) {
			x += 1;
			animationX = 1;
		}
		if(dx < x) {
			x -= 1;
			animationX = -1;
		}
		if(dy > y) {
			y += 1;
			animationY = 1;
		}
		if(dy < y) {
			y -= 1;
			animationY = -1;
		}
		animation(animationX, animationY);
	}
	
	public void getDamage(int dmg) {
		health -= dmg;
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

	public void setHealth(int health) {
		this.health = health;
	}
}
