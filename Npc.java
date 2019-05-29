package jtp2019;

public class Npc extends Sprite{
	
	public Npc(int x, int y) {
		super(x, y);
		
		initNpc();
	}
	
	public void initNpc() {
		
		loadImage("src/resources/npc_szary/npc1-0-0.png");
		getImageDimensions();
	}
}
