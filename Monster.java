package jtp2019;

public class Monster extends Sprite {
	
	public Monster (int x, int y) {
		super(x, y);
		
		initMonster();
	}

	private void initMonster() {
		
		loadImage("src/resources/paladyn/monster1.png");
		getImageDimensions();
	}
	
	public void move() {
		if(x < 0) {
			x = 1280;
		}
		x -= 1;
	}
}
