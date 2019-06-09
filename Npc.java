package jtp2019;

public class Npc extends Sprite{
	
	public Npc(int x, int y, String string) {
		super(x, y);
		
		initNpc(string);
	}
	
	public void initNpc(String string) {
		
		loadImage(string);
		getImageDimensions();
	}
}
