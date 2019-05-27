package jtp2019;

public class Monster extends Sprite {
	
	private int health;
	
	public Monster (int x, int y) {
		super(x, y);
		
		initMonster();
	}

	private void initMonster() {
		
		health = 100;
		loadImage("src/resources/paladyn/monster1.png");
		getImageDimensions();
	}
	
	public void move(int dx, int dy, int b_width, int b_height) {
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
		}
		if(dx < x) {
			x -= 1;
		}
		if(dy > y) {
			y += 1;
		}
		if(dy < y) {
			y -= 1;
		}
	}
	
	public void getDamage(int dmg) {
		health -= dmg;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
